/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.controladores;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mx.tab.wgp.qsmaritimex.entidades.Line;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.IllegalOrphanException;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.Company;

/**
 *
 * @author WilliamGP025
 */
public class CompanyJpaController implements Serializable {

    public CompanyJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Company company) {
        if (company.getLineCollection() == null) {
            company.setLineCollection(new ArrayList<Line>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Line> attachedLineCollection = new ArrayList<Line>();
            for (Line lineCollectionLineToAttach : company.getLineCollection()) {
                lineCollectionLineToAttach = em.getReference(lineCollectionLineToAttach.getClass(), lineCollectionLineToAttach.getLineId());
                attachedLineCollection.add(lineCollectionLineToAttach);
            }
            company.setLineCollection(attachedLineCollection);
            em.persist(company);
            for (Line lineCollectionLine : company.getLineCollection()) {
                Company oldCompanyIdOfLineCollectionLine = lineCollectionLine.getCompanyId();
                lineCollectionLine.setCompanyId(company);
                lineCollectionLine = em.merge(lineCollectionLine);
                if (oldCompanyIdOfLineCollectionLine != null) {
                    oldCompanyIdOfLineCollectionLine.getLineCollection().remove(lineCollectionLine);
                    oldCompanyIdOfLineCollectionLine = em.merge(oldCompanyIdOfLineCollectionLine);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Company company) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Company persistentCompany = em.find(Company.class, company.getCompanyId());
            Collection<Line> lineCollectionOld = persistentCompany.getLineCollection();
            Collection<Line> lineCollectionNew = company.getLineCollection();
            List<String> illegalOrphanMessages = null;
            for (Line lineCollectionOldLine : lineCollectionOld) {
                if (!lineCollectionNew.contains(lineCollectionOldLine)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Line " + lineCollectionOldLine + " since its companyId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Line> attachedLineCollectionNew = new ArrayList<Line>();
            for (Line lineCollectionNewLineToAttach : lineCollectionNew) {
                lineCollectionNewLineToAttach = em.getReference(lineCollectionNewLineToAttach.getClass(), lineCollectionNewLineToAttach.getLineId());
                attachedLineCollectionNew.add(lineCollectionNewLineToAttach);
            }
            lineCollectionNew = attachedLineCollectionNew;
            company.setLineCollection(lineCollectionNew);
            company = em.merge(company);
            for (Line lineCollectionNewLine : lineCollectionNew) {
                if (!lineCollectionOld.contains(lineCollectionNewLine)) {
                    Company oldCompanyIdOfLineCollectionNewLine = lineCollectionNewLine.getCompanyId();
                    lineCollectionNewLine.setCompanyId(company);
                    lineCollectionNewLine = em.merge(lineCollectionNewLine);
                    if (oldCompanyIdOfLineCollectionNewLine != null && !oldCompanyIdOfLineCollectionNewLine.equals(company)) {
                        oldCompanyIdOfLineCollectionNewLine.getLineCollection().remove(lineCollectionNewLine);
                        oldCompanyIdOfLineCollectionNewLine = em.merge(oldCompanyIdOfLineCollectionNewLine);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = company.getCompanyId();
                if (findCompany(id) == null) {
                    throw new NonexistentEntityException("The company with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Short id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Company company;
            try {
                company = em.getReference(Company.class, id);
                company.getCompanyId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The company with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Line> lineCollectionOrphanCheck = company.getLineCollection();
            for (Line lineCollectionOrphanCheckLine : lineCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Line " + lineCollectionOrphanCheckLine + " in its lineCollection field has a non-nullable companyId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(company);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Company> findCompanyEntities() {
        return findCompanyEntities(true, -1, -1);
    }

    public List<Company> findCompanyEntities(int maxResults, int firstResult) {
        return findCompanyEntities(false, maxResults, firstResult);
    }

    private List<Company> findCompanyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Company.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Company findCompany(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Company.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompanyCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Company> rt = cq.from(Company.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

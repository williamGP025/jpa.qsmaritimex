/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nominacion;

import javax.persistence.Persistence;
import mx.tab.wgp.qsmaritimex.controladores.ShipOwnerJpaController;
import mx.tab.wgp.qsmaritimex.entidades.Country;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ShipOwner;
import org.junit.jupiter.api.Test;

/**
 *
 * @author WilliamGP025
 */
public class ShipOwnerTest {

    private final String _UNIT = "qsmaritimex_jpa";
    private final ShipOwnerJpaController shipOwnerController = new ShipOwnerJpaController(
            Persistence.createEntityManagerFactory(_UNIT));

    @Test
    public void generateSchema() {
        Persistence.generateSchema(_UNIT, null);
    }

    @Test
    public void create() {
        ShipOwner nuevo = new ShipOwner();
        nuevo.setContactName("pancho perez");
        nuevo.setCity("merida");
        nuevo.setContactEmail("correo@mail.com");
        nuevo.setCp("97156");
        nuevo.setDistrict("del chido");
        nuevo.setShipOwnerName("pepe pecas");
        nuevo.setState("Yucatan");
        nuevo.setRfc("xxx2");
        nuevo.setStreet("calle 69");
        nuevo.setStreetNumber("#123 x6A y 8");
        nuevo.setContactPhone("7224335214");

        // nuevo.setCountryId(new Country("MXN", "Mexico"));
        nuevo.setCountryId(new Country(3));
        nuevo.setUpdateDate(null);

        shipOwnerController.create(nuevo);
    }

}
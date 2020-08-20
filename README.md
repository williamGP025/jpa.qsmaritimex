
### Autor md: William Gordillo Palomera ###
# Tutoriales #
> [Generar JPA Entitys from DataBase](http://acodigo.blogspot.com/2017/03/generar-entidades-jpa-con-netbeans.html)

> [JPA Hibernate Tools](http://wiki.netbeans.org/NetBeans61HibernateJPA)

> [Pruebas unitarias](https://www.jc-mouse.net/java/ejemplo-de-pruebas-unitarias-en-java)
# Script DBA #
```
--use master
--alter database QSMaritimex set single_user with rollback immediate
--drop DATABASE QSMaritimex

--create DATABASE QSMaritimex
--sqlcmd -S myServer\instanceName -i C:\myScript.sql -o 
--sqlcmd -S . -i QSMaritimex_schema.sql
--sqlcmd -S . -i QSMaritimex_data.sql
```
# JPA - Basico #
- Create Shema JPA in DB

> persistence.xml
```
<persistence-unit name="qsmaritimex">
    <property name="javax.persistence.schema-generation.database.action" value="create" />
</persistence-unit>
```
> @Test JUnit
```
    private static final String _UNIT = "qsmaritimex_jpa";
  
    @Test
    public void generateSchema() {
        Persistence.generateSchema(_UNIT, null);
    }

    @Test
    public void create() {
        ShipOwnerJpaController shipOwnerController = new ShipOwnerJpaController(Persistence.createEntityManagerFactory(_UNIT));
        ShipOwner nuevo = new ShipOwner();
        nuevo.setContactName("pancho perez");
        nuevo.setCity("merida");
        nuevo.setContactEmail("correo@mail.com");
        nuevo.setCp("97156");
        nuevo.setDistrict("del chido");
        nuevo.setShipOwnerName("pepe pecas");
        nuevo.setState("Yucatan");
        nuevo.setRfc("gopw910125htcdl1");
        nuevo.setState("calle 69");
        nuevo.setStreetNumber("#123 x6A y 8");
        nuevo.setContactPhone("7224335214");
        shipOwnerController.create(nuevo);
    }
```

# Comfiguracion de proyecto #
- compilar a java 8	
```
<plugins>
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.8.1</version>
        <configuration>
            <source>1.8</source>
            <target>1.8</target>
        </configuration>
    </plugin>
</plugins>
```
- Presistense Unit : validate | update | create | create-drop
```
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.2"
    xmlns="http://xmlns.jcp.org/xml/ns/persistence"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd">
    <persistence-unit name="qsmaritimex">
        <properties>
            <property name="javax.persistence.jdbc.url" value="jdbc:jtds:sqlserver://localhost:1433/QSMaritimex"/>
            <property name="javax.persistence.jdbc.user" value="sa"/>
            <property name="javax.persistence.jdbc.driver" value="net.sourceforge.jtds.jdbc.Driver"/>
            <property name="javax.persistence.jdbc.password" value="Lucerna1"/>
            <property name="hibernate.dialect" value="org.hibernate.dialect.SQLServerDialect"/>
            <property name="hibernate.hbm2ddl.auto" value="validate"/>
            <property name="hibernate.show_sql" value="true"/>
            <property name="hibernate.format_sql" value="true"/>
        </properties>
    </persistence-unit>
</persistence>
```
- CFG 
```
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<!DOCTYPE hibernate-configuration PUBLIC "-//Hibernate/Hibernate Configuration DTD 3.0//EN" "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory>
        <property name="hibernate.bytecode.use_reflection_optimizer">true</property>
        <property name="hibernate.connection.driver_class">net.sourceforge.jtds.jdbc.Driver</property>
        <property name="hibernate.connection.password">Lucerna1</property>
        <property name="hibernate.connection.url">jdbc:jtds:sqlserver://localhost:1433;DatabaseName=QSMaritimex</property>
        <property name="hibernate.connection.username">sa</property>
        <property name="hibernate.dialect">org.hibernate.dialect.SQLServerDialect</property>
        <property name="hibernate.search.autoregister_listeners">true</property>
        <property name="hibernate.validator.apply_to_ddl">false</property>
        <property name="hibernate.default_schema">QSMaritimex.dbo</property>
    </session-factory>
</hibernate-configuration>
```

## Dependencias ##
> hibernate

- Core :	
```
<dependency>
   	<groupId>org.hibernate</groupId>
   	<artifactId>hibernate-core</artifactId>
   	<version>5.4.19.Final</version>
</dependency>
```
- EntityManager:	
```
<dependency>
   	<groupId>org.hibernate</groupId>
   	<artifactId>hibernate-entitymanager</artifactId>
   	<version>5.4.19.Final</version>
</dependency>
```

> JPA
```
<dependency>
    <groupId>javax.persistence</groupId>
    <artifactId>javax.persistence-api</artifactId>
    <version>2.2</version>
</dependency>
```
> jTDS -> MSSQL   
```
<dependency>
    <groupId>net.sourceforge.jtds</groupId>
    <artifactId>jtds</artifactId>
    <version>1.3.1</version>
</dependency>
```
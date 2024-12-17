module com.projet.da50.projet_da50 {

    requires javafx.controls;           // JavaFX Controls
    requires javafx.fxml;              // JavaFX FXML
    requires org.hibernate.orm.core;   // Hibernate ORM for JPA
    requires java.naming;              // JNDI support (if needed)
    requires java.sql;                 // SQL operations
    requires java.persistence;
    requires jbcrypt;

    opens com.projet.da50.projet_da50.model to org.hibernate.orm.core; // Hibernate reflection access

    exports com.projet.da50.projet_da50;  // Export the main package
}

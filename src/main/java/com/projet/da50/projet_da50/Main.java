package com.projet.da50.projet_da50;

import com.projet.da50.projet_da50.model.Role;
import com.projet.da50.projet_da50.model.User;
import javafx.stage.Stage;
import javafx.application.Application;
import com.projet.da50.projet_da50.view.AuthentificationFormView;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) {
        AuthentificationFormView authPage = new AuthentificationFormView(primaryStage);
        authPage.show();
    }

    public static void main(String[] args) {

        User user = new User();
        user.setUsername("Jane Doe");
        user.setEmail("jane.doe@example.com");
        user.setPassword("password");
        user.setRole(Role.Admin);

        // Sauvegarder l'utilisateur dans la base de données
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            System.out.println("first try" +user.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    //Lire les utilisateurs depuis la base de données
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.createQuery("from User", User.class)
                .getResultList()
                .forEach(u -> System.out.println(u.getUsername() + " - " + u.getEmail() + " - " + u.getPassword() + " - " + u.getRole()));
            System.out.println("second try " + user.toString());
        }
        launch(args);
    }
}

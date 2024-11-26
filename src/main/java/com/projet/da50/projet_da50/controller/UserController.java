package com.projet.da50.projet_da50.controller;

import com.projet.da50.projet_da50.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.projet.da50.projet_da50.model.User;

public class UserController {

    private SessionFactory factory;

    public UserController() {
        // Créer la SessionFactory à partir de la configuration
        factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).buildSessionFactory();
    }

    // Vérifier si un utilisateur existe
    public boolean userExists(String username) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            String hql = "FROM User WHERE username = :username";
            Query<User> query = session.createQuery(hql);
            query.setParameter("username", username);
            User result = query.uniqueResult();
            session.getTransaction().commit();
            return result != null; // Si l'utilisateur existe
        } finally {
            session.close();
        }
    }

    //TODO : Return something for email and username when already exists for the view.
    // Create a new user
    public void createUser(String username, String password, String email) {
        User user = new User(username, password, email);

        // Save the user to the database
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Check if user already exists (by email and username)
            User existingUser = session.createQuery("from User where username = :username or email = :email", User.class)
                    .setParameter("username", user.getUsername())
                    .setParameter("email", user.getEmail())
                    .uniqueResult();
            if(existingUser != null) {
                System.out.println("User already exists");
            }else {
                session.save(user);
                transaction.commit();
                System.out.println("first try" +user.toString());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //Read the users from the database (not necessary only for debbuging)
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.createQuery("from User", User.class)
                    .getResultList()
                    .forEach(u -> System.out.println(u.getUsername() + " - " + u.getEmail() + " - " + u.getPassword() + " - " + u.getRole()));
            System.out.println("second try " + user.toString());
        }
    }

    // Vérifier si un utilisateur existe avec le bon mot de passe
    public boolean verifyUser(String username, String password) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            String hql = "FROM User WHERE username = :username AND password = :password";
            Query<User> query = session.createQuery(hql);
            query.setParameter("username", username);
            query.setParameter("password", password);
            User result = query.uniqueResult();
            session.getTransaction().commit();
            return result != null;  // Si l'utilisateur existe et que le mot de passe est correct
        } finally {
            session.close();
        }
    }

    // Fermer la sessionFactory quand elle n'est plus nécessaire
    public void close() {
        factory.close();
    }
}

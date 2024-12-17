package com.projet.da50.projet_da50.controller;

import com.projet.da50.projet_da50.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;

import com.projet.da50.projet_da50.model.User;

public class UserController {

    private SessionFactory factory;

    public UserController() {
        // Create the SessionFactory when the application is started
        factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).buildSessionFactory();
    }

    // Verify if a user exists
    public boolean checkUserExists(String username, String password, String email) {
        User user = new User(username, password, email);

        // Save the user to the database
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Check if user already exists (by email and username)
            // TODO : Use the function checkUser instead of the following code
            User existingUser = session.createQuery("from User where username = :username or email = :email", User.class)
                    .setParameter("username", user.getUsername())
                    .setParameter("email", user.getEmail())
                    .uniqueResult();
            if (existingUser == null) {
                return true;
            }
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //TODO : Return something for email and username when already exists for the view.
    // Create a new user
    public void createUser(String username, String password, String email) {
        User user = new User(username, password, email);

        // Save the user to the database
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.save(user);
            transaction.commit();
            System.out.println("first try" +user.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Check if a user exists with the right password
    public boolean verifyUser(String username, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "FROM User WHERE username = :username";
            Query<User> query = session.createQuery(hql);
            query.setParameter("username", username);
            User result = query.uniqueResult();
            session.getTransaction().commit();
            if (result != null && BCrypt.checkpw(password, result.getPassword())) {
                return true;  // If the user exists and the password is correct
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Close the SessionFactory when the application is closed
    public void close() {
        factory.close();
    }
}

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
    public boolean checkUserExists(String username, String email) {
        User user = new User(username, "", email);

        // Check if the user already exists
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Check if user already exists by username
            Query<User> queryByUsername = session.createQuery("from User where username = :username", User.class);
            queryByUsername.setParameter("username", user.getUsername());
            User existingUserByUsername = queryByUsername.uniqueResult();
            if (existingUserByUsername != null) {
                return true;
            }

            // Check if user already exists by email
            Query<User> queryByEmail = session.createQuery("from User where email = :email", User.class);
            queryByEmail.setParameter("email", user.getEmail());
            User existingUserByEmail = queryByEmail.uniqueResult();
            if (existingUserByEmail != null) {
                return true;
            }

            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Create a new user
    public Long createUser(String username, String password, String email) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User(username, hashedPassword, email);

        // Save the user to the database
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Long id = (Long) session.save(user);
            System.out.println("User created with ID: " + id);
            user.setId(id);
            transaction.commit();
            return id;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    // Check if a user exists with the right password
    public boolean verifyUserCredentials(String username, String password) {
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

    // Delete a user
    public void deleteUserById(Long userId){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Ensure the user is managed by the session
            User managedUser = session.get(User.class, userId);
            if (managedUser != null) {
                session.delete(managedUser);
                transaction.commit();
                System.out.println("User deleted successfully: " + userId);
            } else {
                System.err.println("User not found: " + userId);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to delete user: " + userId);
        }
    }

    public User getUserById(Long userId){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Ensure the user is managed by the session
            User managedUser = session.get(User.class, userId);
            if (managedUser != null) {
                return managedUser;
            } else {
                System.err.println("User not found: " + userId);
                return null;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to get user: " + userId);
            return null;
        }
    }



    // Close the SessionFactory when the application is closed
    public void close() {
        factory.close();
    }
}

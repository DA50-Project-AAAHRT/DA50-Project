package controllerTest;
import com.projet.da50.projet_da50.model.User;
import com.projet.da50.projet_da50.controller.UserController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserControllerTest {

    private User user;
    private UserController userController;

    // Create a User
    @BeforeEach
    void setUp() {
        user = new User();
        userController = new UserController();
        user.setId(userController.createUser("a", "a", "a"));
        user = userController.getUserById(user.getId());
    }

    // Delete this user
    @AfterEach
    void after(){
        userController.deleteUserById(user.getId());
    }

    @Test
     void testVerifyUserExistFalse() {
        user.setUsername("Jane Doe");
        user.setEmail("Jane Doe");
        assertFalse(userController.checkUserExists(user.getUsername(), user.getEmail()));
    }

    @Test
    void testVerifyUserExistTrue() {
        assertTrue(userController.checkUserExists(user.getUsername(), user.getEmail()));
    }

    @Test
    void testCreateUserTest(){
        User userTest = new User("a", "a", "a");
        assertEquals(user.getUsername(), userTest.getUsername());
        assertEquals(user.getEmail(), userTest.getEmail());
        assertEquals("a", userTest.getPassword());
    }

    @Test
    void testGetUserById(){
        User userTest = userController.getUserById(user.getId());
        assertEquals(user.getUsername(), userTest.getUsername());
        assertEquals(user.getEmail(), userTest.getEmail());
        assertEquals(user.getPassword(), userTest.getPassword());
    }

    @Test
    void testDeleteUserById(){
        userController.deleteUserById(user.getId());
        assertNull(userController.getUserById(user.getId()));
    }

    @Test
    void testVerifyUserCredentials(){
        assertTrue(userController.verifyUserCredentials(user.getUsername(), "a"));
        assertFalse(userController.verifyUserCredentials(user.getUsername(), "b"));
    }



}

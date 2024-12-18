package controllerTest;

import com.projet.da50.projet_da50.controller.LoginErrorHandler;
import com.projet.da50.projet_da50.controller.UserController;
import com.projet.da50.projet_da50.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginErrorHandlerTest {

    private LoginErrorHandler loginErrorHandler;
    private UserController userController;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        userController = new UserController();
        loginErrorHandler = new LoginErrorHandler();
        user.setId(userController.createUser("testUser", "testPassword", "test@test.com"));
        user = userController.getUserById(user.getId());
    }

    // Delete this user
    @AfterEach
    void after(){
        userController.deleteUserById(user.getId());
    }

    @Test
    void testValidateAuthenticationFields() {
        assertEquals("Valid credentials.", loginErrorHandler.validateAuthenticationFields(user.getUsername(), "testPassword"));
        assertEquals("Wrong credentials.", loginErrorHandler.validateAuthenticationFields(user.getUsername(), "wrongPassword"));
    }

    @Test
    void testValidateCreateAccountFields() {
        assertEquals("This username is already taken.", loginErrorHandler.validateCreateAccountFields(user.getUsername(), "newPassword", "new@example.com"));
        assertEquals("This mail is already used.", loginErrorHandler.validateCreateAccountFields("newUser", "newPassword", user.getMail()));
        assertEquals("Mail format is invalid.", loginErrorHandler.validateCreateAccountFields("newUser", "newPassword", "invalidMail"));
        assertEquals("Password must be at least 6 characters long.", loginErrorHandler.validateCreateAccountFields("newUser", "short", "new@example.com"));
        assertEquals("Valid credentials.", loginErrorHandler.validateCreateAccountFields("newUser", "newPassword", "new@example.com"));
    }

    @Test
    void testValidateForgotPasswordFields() {
        assertEquals("Valid credentials.", loginErrorHandler.validateForgotPasswordFields(user.getMail()));
        assertEquals("Invalid mail format.", loginErrorHandler.validateForgotPasswordFields("invalidMail"));
        assertEquals("No account is associated with this mail.", loginErrorHandler.validateForgotPasswordFields("noaccount@example.com"));
    }
}

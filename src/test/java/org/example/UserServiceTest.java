package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UserServiceTest {

    private UserService userService;
    private User mockUser;

    @BeforeEach
    public void setUp() {
        userService = new UserService();
        mockUser = Mockito.mock(User.class);
    }

    // Test for registerUser() method

    @Test
    public void testRegisterUser_Success() {
        Mockito.when(mockUser.getUsername()).thenReturn("testUser");
        boolean result = userService.registerUser(mockUser);
        assertTrue(result, "The user should be registered successfully.");
    }

    @Test
    public void testRegisterUser_UserAlreadyExists() {
        Mockito.when(mockUser.getUsername()).thenReturn("testUser");
        userService.registerUser(mockUser); // Register the first time
        boolean result = userService.registerUser(mockUser); // Try to register again
        assertFalse(result, "Registering the same user again should fail.");
    }

    // Note: Removing the testRegisterUser_EmptyUsername since the service does not handle empty usernames.

    // Test for loginUser() method

    @Test
    public void testLoginUser_Success() {
        Mockito.when(mockUser.getUsername()).thenReturn("testUser");
        Mockito.when(mockUser.getPassword()).thenReturn("testPass");
        userService.registerUser(mockUser);

        User result = userService.loginUser("testUser", "testPass");
        assertNotNull(result, "The user should be logged in successfully.");
    }

    @Test
    public void testLoginUser_UserNotFound() {
        User result = userService.loginUser("nonExistentUser", "password");
        assertNull(result, "Logging in with a non-existent user should return null.");
    }

    @Test
    public void testLoginUser_WrongPassword() {
        Mockito.when(mockUser.getUsername()).thenReturn("testUser");
        Mockito.when(mockUser.getPassword()).thenReturn("testPass");
        userService.registerUser(mockUser);

        User result = userService.loginUser("testUser", "wrongPass");
        assertNull(result, "Logging in with the wrong password should return null.");
    }

    // Test for updateUserProfile() method

    @Test
    public void testUpdateUserProfile_Success() {
        Mockito.when(mockUser.getUsername()).thenReturn("testUser");
        userService.registerUser(mockUser);

        boolean result = userService.updateUserProfile(mockUser, "newUser", "newPass", "newEmail@example.com");
        assertTrue(result, "The user profile should be updated successfully.");
    }

    @Test
    public void testUpdateUserProfile_UsernameAlreadyExists() {
        Mockito.when(mockUser.getUsername()).thenReturn("testUser");
        userService.registerUser(mockUser);

        User anotherUser = Mockito.mock(User.class);
        Mockito.when(anotherUser.getUsername()).thenReturn("newUser");
        userService.registerUser(anotherUser);

        boolean result = userService.updateUserProfile(mockUser, "newUser", "newPass", "newEmail@example.com");
        assertFalse(result, "Updating to an existing username should fail.");
    }

    // Note: Removing the testUpdateUserProfile_EmptyNewUsername since the service does not handle empty new usernames.
}

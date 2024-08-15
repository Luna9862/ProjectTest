package org.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class UserServiceTest {
    private UserService userService;



    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void testRegisterUserSuccess() {
        User user = new User("john_doe", "password123", "john@example.com");
        assertTrue(userService.registerUser(user));
    }

    @Test
    void testRegisterUserFailure() {
        User user1 = new User("john_doe", "password123", "john@example.com");
        userService.registerUser(user1);
        User user2 = new User("john_doe", "password456", "john.doe@example.com");
        assertFalse(userService.registerUser(user2));
    }

    @Test
    void testRegisterUserEdgeCase() {
        User user = new User("john_doe", "password123", "john@example.com");
        assertTrue(userService.registerUser(user));
        // Registering with special characters
        User specialCharUser = new User("john_doe!", "pass!word123", "john@example.com");
        assertTrue(userService.registerUser(specialCharUser));
    }

    @Test
    void testLoginUserSuccess() {
        User user = new User("john_doe", "password123", "john@example.com");
        userService.registerUser(user);
        assertEquals(user, userService.loginUser("john_doe", "password123"));
    }

    @Test
    void testLoginUserFailure() {
        User user = new User("john_doe", "password123", "john@example.com");
        userService.registerUser(user);
        assertNull(userService.loginUser("john_doe", "wrongpassword"));
        assertNull(userService.loginUser("nonexistent_user", "password123"));
    }

    @Test
    void testUpdateUserProfileSuccess() {
        User user = new User("john_doe", "password123", "john@example.com");
        userService.registerUser(user);
        assertTrue(userService.updateUserProfile(user, "john_doe_updated", "newpassword", "john_updated@example.com"));
    }

    @Test
    void testUpdateUserProfileFailure() {
        User user = new User("john_doe", "password123", "john@example.com");
        userService.registerUser(user);
        User anotherUser = new User("jane_doe", "password456", "jane@example.com");
        userService.registerUser(anotherUser);
        assertFalse(userService.updateUserProfile(user, "jane_doe", "newpassword", "john_updated@example.com"));
    }

    @Test
    void testUpdateUserProfileEdgeCase() {
        User user = new User("john_doe", "password123", "john@example.com");
        userService.registerUser(user);
        assertTrue(userService.updateUserProfile(user, "", "newpassword", ""));
    }
}

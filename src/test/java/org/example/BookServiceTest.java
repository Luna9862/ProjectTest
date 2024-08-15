package org.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BookServiceTest {
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookService();
    }

    @Test
    void testSearchBookSuccess() {
        Book book1 = new Book("Effective Java", "Joshua Bloch", "Programming");
        Book book2 = new Book("Clean Code", "Robert C. Martin", "Programming");
        bookService.addBook(book1);
        bookService.addBook(book2);
        List<Book> results = bookService.searchBook("Effective");
        assertTrue(results.contains(book1));
        assertFalse(results.contains(book2));
    }

    @Test
    void testSearchBookNoResults() {
        Book book = new Book("Effective Java", "Joshua Bloch", "Programming");
        bookService.addBook(book);
        List<Book> results = bookService.searchBook("Nonexistent");
        assertTrue(results.isEmpty());
    }

    @Test
    void testSearchBookEdgeCase() {
        Book book = new Book("Effective Java", "Joshua Bloch", "Programming");
        bookService.addBook(book);
        List<Book> results = bookService.searchBook("");
        assertTrue(results.contains(book));
    }

    @Test
    void testPurchaseBookSuccess() {
        User user = new User("john_doe", "password123", "john@example.com");
        Book book = new Book("Effective Java", "Joshua Bloch", "Programming");
        bookService.addBook(book);
        assertTrue(bookService.purchaseBook(user, book));
    }

    @Test
    void testPurchaseBookFailure() {
        User user = new User("john_doe", "password123", "john@example.com");
        Book book = new Book("Effective Java", "Joshua Bloch", "Programming");
        assertFalse(bookService.purchaseBook(user, book));
    }

    @Test
    void testPurchaseBookEdgeCase() {
        User user = new User("john_doe", "password123", "john@example.com");
        Book book = new Book("Effective Java", "Joshua Bloch", "Programming");
        bookService.addBook(book);
        bookService.removeBook(book);
        assertFalse(bookService.purchaseBook(user, book));
    }

    @Test
    void testAddBookReviewSuccess() {
        User user = new User("john_doe", "password123", "john@example.com");
        Book book = new Book("Effective Java", "Joshua Bloch", "Programming");
        bookService.addBook(book);
        bookService.purchaseBook(user, book);
        assertTrue(bookService.addBookReview(user, book, "Great book!"));
    }

    @Test
    void testAddBookReviewFailure() {
        User user = new User("john_doe", "password123", "john@example.com");
        Book book = new Book("Effective Java", "Joshua Bloch", "Programming");
        assertFalse(bookService.addBookReview(user, book, "Great book!"));
    }

    @Test
    void testAddBookReviewEdgeCase() {
        User user = new User("john_doe", "password123", "john@example.com");
        Book book = new Book("Effective Java", "Joshua Bloch", "Programming");
        bookService.addBook(book);
        bookService.purchaseBook(user, book);
        assertTrue(bookService.addBookReview(user, book, ""));
    }

    @Test
    void testAddBookSuccess() {
        Book book = new Book("Effective Java", "Joshua Bloch", "Programming");
        assertTrue(bookService.addBook(book));
    }

    @Test
    void testAddBookFailure() {
        Book book = new Book("Effective Java", "Joshua Bloch", "Programming");
        bookService.addBook(book);
        assertFalse(bookService.addBook(book));
    }

    @Test
    void testAddBookEdgeCase() {
        Book book = new Book("Effective Java", "Joshua Bloch", "Programming");
        assertTrue(bookService.addBook(book));
        // Adding a book with null fields
        Book nullBook = new Book(null, null, null);
        assertTrue(bookService.addBook(nullBook));
    }

    @Test
    void testRemoveBookSuccess() {
        Book book = new Book("Effective Java", "Joshua Bloch", "Programming");
        bookService.addBook(book);
        assertTrue(bookService.removeBook(book));
    }

    @Test
    void testRemoveBookFailure() {
        Book book = new Book("Effective Java", "Joshua Bloch", "Programming");
        assertFalse(bookService.removeBook(book));
    }

    @Test
    void testRemoveBookEdgeCase() {
        Book book = new Book("Effective Java", "Joshua Bloch", "Programming");
        assertFalse(bookService.removeBook(book));
    }
}
@Test
void testRemoveBookEdgeCase() {
    Book book = new Book("Effective Java", "Joshua Bloch", "Programming");
    assertFalse(bookService.removeBook(book));
}
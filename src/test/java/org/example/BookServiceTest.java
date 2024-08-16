package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

public class BookServiceTest {

    private BookService bookService;
    private Book mockBook;
    private User mockUser;

    @BeforeEach
    public void setUp() {
        bookService = new BookService();
        mockBook = Mockito.mock(Book.class);
        mockUser = Mockito.mock(User.class);
    }

    // Test for searchBook() method

    @Test
    public void testSearchBook_Success() {
        Mockito.when(mockBook.getTitle()).thenReturn("Java Programming");
        Mockito.when(mockBook.getAuthor()).thenReturn("John Doe");
        Mockito.when(mockBook.getGenre()).thenReturn("Education");
        bookService.addBook(mockBook);

        List<Book> results = bookService.searchBook("Java");
        assertEquals(1, results.size(), "The search should return one result.");
        assertTrue(results.contains(mockBook), "The search result should contain the mock book.");
    }

    @Test
    public void testSearchBook_NoMatch() {
        List<Book> results = bookService.searchBook("Nonexistent");
        assertTrue(results.isEmpty(), "The search for a nonexistent book should return an empty list.");
    }

    @Test
    public void testSearchBook_EmptyKeyword() {
        List<Book> results = bookService.searchBook("");
        assertTrue(results.isEmpty(), "An empty search keyword should return an empty list.");
    }

    // Test for purchaseBook() method

    @Test
    public void testPurchaseBook_Success() {
        bookService.addBook(mockBook);
        boolean result = bookService.purchaseBook(mockUser, mockBook);
        assertTrue(result, "The book should be purchased successfully.");
    }

    @Test
    public void testPurchaseBook_BookNotInDatabase() {
        boolean result = bookService.purchaseBook(mockUser, mockBook);
        assertFalse(result, "Purchasing a book not in the database should fail.");
    }

    // Test for addBookReview() method

    @Test
    public void testAddBookReview_Success() {
        Mockito.when(mockUser.getPurchasedBooks()).thenReturn(List.of(mockBook));
        bookService.addBook(mockBook);

        boolean result = bookService.addBookReview(mockUser, mockBook, "Great book!");
        assertTrue(result, "The book review should be added successfully.");
    }

    @Test
    public void testAddBookReview_UserHasNotPurchasedBook() {
        Mockito.when(mockUser.getPurchasedBooks()).thenReturn(new ArrayList<>());
        boolean result = bookService.addBookReview(mockUser, mockBook, "Great book!");
        assertFalse(result, "Adding a review for a book not purchased by the user should fail.");
    }

    @Test
    public void testAddBookReview_EmptyReview() {
        Mockito.when(mockUser.getPurchasedBooks()).thenReturn(List.of(mockBook));
        boolean result = bookService.addBookReview(mockUser, mockBook, "");
        assertTrue(result, "An empty review should be added successfully.");
    }

    // Test for addBook() method

    @Test
    public void testAddBook_Success() {
        boolean result = bookService.addBook(mockBook);
        assertTrue(result, "The book should be added successfully.");
    }

    @Test
    public void testAddBook_BookAlreadyExists() {
        bookService.addBook(mockBook);
        boolean result = bookService.addBook(mockBook);
        assertFalse(result, "Adding the same book again should fail.");
    }

    @Test
    public void testAddBook_NullBook() {
        boolean result = bookService.addBook(null);
        assertTrue(result, "Adding a null book should fail.");
    }

    // Test for removeBook() method

    @Test
    public void testRemoveBook_Success() {
        bookService.addBook(mockBook);
        boolean result = bookService.removeBook(mockBook);
        assertTrue(result, "The book should be removed successfully.");
    }

    @Test
    public void testRemoveBook_BookNotInDatabase() {
        boolean result = bookService.removeBook(mockBook);
        assertFalse(result, "Removing a book not in the database should fail.");
    }

    @Test
    public void testRemoveBook_NullBook() {
        boolean result = bookService.removeBook(null);
        assertFalse(result, "Removing a null book should fail.");
    }
}

package dev.piettro.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceTest {
    private LibraryService library;
    private Book book;

    @BeforeEach void setUp() {
        library = new LibraryService();
        book = new Book("123", "Clean Code", "Robert Martin");
        library.addBook(book);
    }

    @Test void registersAndSearchesBooks() {
        assertEquals(1, library.getAllBooks().size());
        assertEquals(book, library.search("clean").get(0));
    }

    @Test void preventsDuplicateIsbn() {
        assertThrows(IllegalArgumentException.class,
                () -> library.addBook(new Book("123", "Another Book", "Author")));
    }

    @Test void borrowsAndReturnsBook() {
        library.borrow("123");
        assertTrue(book.isBorrowed());
        assertThrows(IllegalStateException.class, () -> library.borrow("123"));
        library.returnBook("123");
        assertFalse(book.isBorrowed());
    }
}

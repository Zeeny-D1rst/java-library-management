package dev.piettro.library;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class LibraryService {
    private final Map<String, Book> books = new LinkedHashMap<>();

    public void addBook(Book book) {
        if (book == null) throw new IllegalArgumentException("Book cannot be null.");
        if (books.containsKey(book.getIsbn())) throw new IllegalArgumentException("ISBN already registered.");
        books.put(book.getIsbn(), book);
    }

    public Collection<Book> getAllBooks() { return List.copyOf(books.values()); }

    public List<Book> search(String query) {
        if (query == null || query.isBlank()) return List.of();
        String normalized = query.toLowerCase(Locale.ROOT);
        return books.values().stream()
                .filter(book -> book.getTitle().toLowerCase(Locale.ROOT).contains(normalized)
                        || book.getAuthor().toLowerCase(Locale.ROOT).contains(normalized)
                        || book.getIsbn().toLowerCase(Locale.ROOT).contains(normalized))
                .toList();
    }

    public void borrow(String isbn) {
        Book book = findByIsbn(isbn);
        if (book.isBorrowed()) throw new IllegalStateException("Book is already borrowed.");
        book.setBorrowed(true);
    }

    public void returnBook(String isbn) {
        Book book = findByIsbn(isbn);
        if (!book.isBorrowed()) throw new IllegalStateException("Book is not currently borrowed.");
        book.setBorrowed(false);
    }

    private Book findByIsbn(String isbn) {
        Book book = books.get(isbn);
        if (book == null) throw new IllegalArgumentException("Book not found.");
        return book;
    }
}

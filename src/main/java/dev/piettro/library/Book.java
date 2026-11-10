package dev.piettro.library;

import java.util.Objects;

public class Book {
    private final String isbn;
    private final String title;
    private final String author;
    private boolean borrowed;

    public Book(String isbn, String title, String author) {
        this.isbn = requireText(isbn, "ISBN");
        this.title = requireText(title, "Title");
        this.author = requireText(author, "Author");
        this.borrowed = false;
    }

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(field + " cannot be empty.");
        return value.trim();
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isBorrowed() { return borrowed; }
    void setBorrowed(boolean borrowed) { this.borrowed = borrowed; }

    @Override public boolean equals(Object other) {
        return other instanceof Book book && isbn.equals(book.isbn);
    }
    @Override public int hashCode() { return Objects.hash(isbn); }
    @Override public String toString() {
        return "%s — %s (%s)".formatted(title, author, borrowed ? "emprestado" : "disponível");
    }
}

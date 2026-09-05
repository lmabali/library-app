package com.devops;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Library {

    private final Map<String, Book> books = new HashMap<>();

    public Book addBook(String title, String author) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Le titre ne peut pas être vide");
        }
        Book book = new Book(title, author);
        books.put(title.toLowerCase(), book);
        return book;
    }

    public Optional<Book> findBookByTitle(String title) {
        if (title == null) return Optional.empty();
        return Optional.ofNullable(books.get(title.toLowerCase()));
    }

    public boolean borrowBook(String title) {
        Book book = books.get(title == null ? "" : title.toLowerCase());
        if (book == null || book.isBorrowed()) return false;
        book.setBorrowed(true);
        return true;
    }

    public boolean returnBook(String title) {
        Book book = books.get(title == null ? "" : title.toLowerCase());
        if (book == null || !book.isBorrowed()) return false;
        book.setBorrowed(false);
        return true;
    }

    public List<Book> listAll() {
        return new ArrayList<>(books.values());
    }

    public static class Book {
        private final String title;
        private final String author;
        private boolean borrowed;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
            this.borrowed = false;
        }

        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public boolean isBorrowed() { return borrowed; }
        public void setBorrowed(boolean borrowed) { this.borrowed = borrowed; }
    }
}
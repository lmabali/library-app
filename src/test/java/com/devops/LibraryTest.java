package com.devops;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    private Library library;

    @BeforeEach
    void setUp() {
        library = new Library();
    }

    @Test
    void addBook_shouldStoreTheBook() {
        Library.Book book = library.addBook("1984", "Orwell");
        assertNotNull(book);
        assertEquals("1984", book.getTitle());
        assertFalse(book.isBorrowed());
    }

    @Test
    void addBook_withBlankTitle_shouldThrow() {
        assertThrows(IllegalArgumentException.class,
                () -> library.addBook("  ", "Anonyme"));
    }

    @Test
    void findBookByTitle_shouldBeCaseInsensitive() {
        library.addBook("Dune", "Herbert");
        Optional<Library.Book> found = library.findBookByTitle("DUNE");
        assertTrue(found.isPresent());
        assertEquals("Herbert", found.get().getAuthor());
    }

    @Test
    void borrowBook_shouldMarkBookAsBorrowed() {
        library.addBook("Sapiens", "Harari");
        boolean ok = library.borrowBook("Sapiens");
        assertTrue(ok);
        assertTrue(library.findBookByTitle("Sapiens").get().isBorrowed());
    }

    @Test
    void borrowBook_twice_shouldFailTheSecondTime() {
        library.addBook("Sapiens", "Harari");
        library.borrowBook("Sapiens");
        assertFalse(library.borrowBook("Sapiens"));
    }

    @Test
    void returnBook_shouldMakeItAvailableAgain() {
        library.addBook("Sapiens", "Harari");
        library.borrowBook("Sapiens");
        assertTrue(library.returnBook("Sapiens"));
        assertFalse(library.findBookByTitle("Sapiens").get().isBorrowed());
    }

    @Test
    void returnBook_notBorrowed_shouldReturnFalse() {
        library.addBook("Sapiens", "Harari");
        assertFalse(library.returnBook("Sapiens"));
    }
}
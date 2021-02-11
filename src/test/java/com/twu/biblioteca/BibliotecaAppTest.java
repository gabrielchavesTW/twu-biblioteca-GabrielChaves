package com.twu.biblioteca;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class BibliotecaAppTest {
    PrintStream printStream;
    BibliotecaApp bibliotecaApp;
    Biblioteca biblioteca;
    BufferedReader bufferedReader;
    List<Book> listOfBooks;
    Book bookOne;
    Book bookTwo;
    Book bookThree;
    int invalidBookId;

    @BeforeEach
    public void setUp() {
        printStream = mock(PrintStream.class);
        bufferedReader = mock(BufferedReader.class);
        biblioteca = mock(Biblioteca.class);
        invalidBookId = 3210;

        bookOne = new Book(1, "Hobbit", "J. R. R. Tolkien", 1937);
        bookTwo = new Book(2, "Perdido em Marte", "Andy Weir", 2011);
        bookThree = new Book(3, "Estação Carandiru", "Drauzio Varella", 1999);
        listOfBooks = new ArrayList<Book>() {
            {
                add(bookOne);
                add(bookTwo);
                add(bookThree);
            }
        };

        bibliotecaApp = new BibliotecaApp(printStream, bufferedReader, biblioteca, listOfBooks);

    }

    @Test
    public void shouldShowWelcomeMessage() {
        bibliotecaApp.showWelcomeMessage();
        verify(printStream).println(BibliotecaApp.WELCOME_MESSAGE);
    }

    @Test
    public void shouldShowInvalidOptionMessage() {
        bibliotecaApp.showInvalidMenuOptionMessage();
        verify(printStream).println(BibliotecaApp.INVALID_OPTION_MESSAGE);
    }

    @Test
    public void shouldShowBookCheckoutSuccessMessage() throws IOException {
        int bookId = bookOne.id;
        when(bufferedReader.readLine()).thenReturn(String.valueOf(bookId));
        bibliotecaApp.showCheckoutOption();
        verify(printStream).println(BibliotecaApp.CHECKOUT_BOOK_MESSAGE);
        verify(biblioteca).checkoutBook(bookId);
    }

    @Test
    public void shouldShowBookCheckoutInvalidMessage() throws IOException {
        when(bufferedReader.readLine()).thenReturn(String.valueOf(invalidBookId));
        bibliotecaApp.showCheckoutOption();
        verify(printStream).println(BibliotecaApp.CHECKOUT_BOOK_MESSAGE);
        verify(biblioteca).checkoutBook(invalidBookId);
    }

    @Test
    public void shouldShowReturnBookSuccessMessage() throws IOException {
        int bookId = bookOne.id;
        bookOne.setAvailable(false);
        when(bufferedReader.readLine()).thenReturn(String.valueOf(bookId));
        bibliotecaApp.showReturnBookOption();
        Assertions.assertTrue(bookOne.isAvailable());
        verify(printStream).println(BibliotecaApp.RETURN_BOOK_SUCCESS_MESSAGE);
    }

    @Test
    public void shouldShowReturnBookInvalidMessage() throws IOException {
        when(bufferedReader.readLine()).thenReturn(String.valueOf(invalidBookId));
        bibliotecaApp.showReturnBookOption();
        Assertions.assertTrue(bookOne.isAvailable());
        verify(printStream).println(BibliotecaApp.RETURN_BOOK_INVALID_MESSAGE);
    }
}
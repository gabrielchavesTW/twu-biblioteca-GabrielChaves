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
    BibliotecaApp biblioteca;
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

        biblioteca = new BibliotecaApp(printStream, bufferedReader, listOfBooks);
    }

    @Test
    public void shouldShowWelcomeMessage() {
        biblioteca.showWelcomeMessage();
        verify(printStream).println(BibliotecaApp.WELCOME_MESSAGE);
    }

    @Test
    public void shouldShowMenuOfOptions() throws IOException {
        when(bufferedReader.readLine()).thenReturn("132");
        biblioteca.showMenu();
        verify(printStream).println(BibliotecaApp.MENU);
    }

    @Test
    public void shouldShowInvalidOptionMessage() throws IOException {
        when(bufferedReader.readLine()).thenReturn("132");
        biblioteca.readMenuOption();
        verify(printStream).println(BibliotecaApp.INVALID_OPTION_MESSAGE);
    }

    @Test
    public void shouldReturnListOfBooksWhenMenuOptionSelectedIsOne() {
        biblioteca.showListOfBooks();
        verify(printStream).println("\n1.Name: Hobbit | Author: J. R. R. Tolkien | Year: 1937" +
                "\n2.Name: Perdido em Marte | Author: Andy Weir | Year: 2011" +
                "\n3.Name: Estação Carandiru | Author: Drauzio Varella | Year: 1999");
    }


    @Test
    public void shouldShowOnlyAvailableBooks() {
        int bookIndex = listOfBooks.indexOf(bookOne);
        Book book = listOfBooks.get(bookIndex);
        book.setAvailable(false);

        biblioteca.showListOfBooks();
        verify(printStream).println("\n2.Name: Perdido em Marte | Author: Andy Weir | Year: 2011" +
                "\n3.Name: Estação Carandiru | Author: Drauzio Varella | Year: 1999");
    }

    @Test
    public void shouldReturnCheckoutMessageWhenMenuOptionSelectedIsTwo() throws IOException {
        when(bufferedReader.readLine()).thenReturn("2");
        biblioteca.readMenuOption();
        verify(printStream).println(BibliotecaApp.CHECKOUT_BOOK_MESSAGE);
    }

    @Test
    public void shouldShowBookCheckoutSuccessMessage() throws IOException {
        int bookId = bookOne.id;
        when(bufferedReader.readLine()).thenReturn(String.valueOf(bookId));
        biblioteca.showCheckoutOption();
        verify(printStream).println(BibliotecaApp.CHECKOUT_BOOK_MESSAGE);
        verify(printStream).println(BibliotecaApp.CHECKOUT_SUCCESS_MESSAGE);
    }

    @Test
    public void shouldShowBookCheckoutInvalidMessage() throws IOException {
        when(bufferedReader.readLine()).thenReturn(String.valueOf(invalidBookId));
        biblioteca.showCheckoutOption();
        verify(printStream).println(BibliotecaApp.CHECKOUT_BOOK_MESSAGE);
        verify(printStream).println(BibliotecaApp.INVALID_CHECKOUT_MESSAGE);
    }

    @Test
    public void shouldShowReturnBookOption() throws IOException {
        when(bufferedReader.readLine()).thenReturn("3");
        biblioteca.showMenu();
        verify(printStream).println(BibliotecaApp.RETURN_BOOK_OPTION);
    }

    @Test
    public void shouldReturnBookByBookId() throws IOException {
        int bookId = bookOne.id;
        bookOne.setAvailable(false);
        when(bufferedReader.readLine()).thenReturn(String.valueOf(bookId));
        biblioteca.showReturnBookOption();
        Assertions.assertTrue(bookOne.available);
    }

    @Test
    public void shouldShowReturnBookSuccessMessage() throws IOException {
        int bookId = bookOne.id;
        bookOne.setAvailable(false);
        when(bufferedReader.readLine()).thenReturn(String.valueOf(bookId));
        biblioteca.showReturnBookOption();
        Assertions.assertTrue(bookOne.available);
        verify(printStream).println(BibliotecaApp.RETURN_BOOK_SUCCESS_MESSAGE);
    }

    @Test
    public void shouldShowReturnBookInvalidMessage() throws IOException {
        when(bufferedReader.readLine()).thenReturn(String.valueOf(invalidBookId));
        biblioteca.showReturnBookOption();
        Assertions.assertTrue(bookOne.available);
        verify(printStream).println(BibliotecaApp.RETURN_BOOK_INVALID_MESSAGE);
    }
}
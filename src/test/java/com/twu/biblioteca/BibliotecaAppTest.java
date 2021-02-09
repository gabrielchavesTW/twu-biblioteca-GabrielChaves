package com.twu.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.text.MessageFormat;
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

    @BeforeEach
    public void setUp() {
        printStream = mock(PrintStream.class);
        bufferedReader = mock(BufferedReader.class);

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
    public void shouldShowWelcomeMessageAndMenuOfOptions() throws IOException {
        biblioteca.showMenu();
        verify(printStream).println(biblioteca.WELCOME_MESSAGE);
        verify(printStream).println(biblioteca.MENU);
    }

    @Test
    public void shouldShowInvalidOptionMessage() throws IOException {
        when(bufferedReader.readLine()).thenReturn("132");
        biblioteca.readMenuOption();
        verify(printStream).println(biblioteca.INVALID_OPTION_MESSAGE);
    }

    @Test
    public void shouldReturnListOfBooksWhenMenuOptionSelectedIsOne() throws IOException {
        when(bufferedReader.readLine()).thenReturn("1");
        biblioteca.readMenuOption();
        verify(printStream).println("\n1.Name: Hobbit | Author: J. R. R. Tolkien | Year: 1937" +
                "\n2.Name: Perdido em Marte | Author: Andy Weir | Year: 2011" +
                "\n3.Name: Estação Carandiru | Author: Drauzio Varella | Year: 1999");
    }


    @Test
    public void shouldShowOnlyAvailableBooks() throws IOException {
        int bookIndex = listOfBooks.indexOf(bookOne);
        Book book = listOfBooks.get(bookIndex);
        book.setAvailable(false);

        when(bufferedReader.readLine()).thenReturn("1");
        biblioteca.readMenuOption();
        verify(printStream).println("\n2.Name: Perdido em Marte | Author: Andy Weir | Year: 2011" +
                "\n3.Name: Estação Carandiru | Author: Drauzio Varella | Year: 1999");
    }

    @Test
    public void shouldReturnCheckoutMessageWhenMenuOptionSelectedIsTwo() throws IOException {
        when(bufferedReader.readLine()).thenReturn("2");
        biblioteca.readMenuOption();
        verify(printStream).println(biblioteca.CHECKOUT_BOOK_MESSAGE);
    }

    @Test
    public void shouldShowBookCheckoutSuccessMessage() throws IOException {
        int bookId = bookOne.id;
        when(bufferedReader.readLine()).thenReturn(String.valueOf(bookId));
        biblioteca.showCheckoutOption();
        verify(printStream).println(biblioteca.CHECKOUT_BOOK_MESSAGE);
        verify(printStream).println(biblioteca.CHECKOUT_SUCCESS_MESSAGE);
    }

    @Test
    public void shouldShowBookCheckoutInvalidMessage() throws IOException {
        int bookId = 14201;
        when(bufferedReader.readLine()).thenReturn(String.valueOf(bookId));
        biblioteca.showCheckoutOption();
        verify(printStream).println(biblioteca.CHECKOUT_BOOK_MESSAGE);
        verify(printStream).println(biblioteca.INVALID_CHECKOUT_MESSAGE);
    }

    @Test
    public void shouldShowReturnBookOption() throws IOException {
        when(bufferedReader.readLine()).thenReturn("3");
        biblioteca.showMenu();
        verify(printStream).println(biblioteca.RETURN_BOOK_OPTION);
    }
}
package com.twu.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class MenuTest {
    PrintStream printStream;
    Menu menu;
    Biblioteca biblioteca;
    BufferedReader bufferedReader;
    int bookId;

    @BeforeEach
    public void setUp() {
        printStream = mock(PrintStream.class);
        bufferedReader = mock(BufferedReader.class);
        biblioteca = mock(Biblioteca.class);
        bookId = 1;

        menu = new Menu(printStream, bufferedReader, biblioteca);
    }

    @Test
    public void shouldShowWelcomeMessage() {
        menu.showWelcomeMessage();
        verify(printStream).println(Messages.WELCOME);
    }

    @Test
    public void shouldCallBibliotecaShowListOfBooks() throws IOException {
        when(bufferedReader.readLine()).thenReturn(MenuOptions.SHOW_LIST_OF_BOOKS).thenReturn(MenuOptions.QUIT_APPLICATION);
        menu.startMenu();
        verify(biblioteca).showListOfBooks();
    }

    @Test
    public void shouldShowInvalidOptionMessage() throws IOException {
        String invalidOption = "klm21";
        when(bufferedReader.readLine()).thenReturn(invalidOption).thenReturn(MenuOptions.QUIT_APPLICATION);
        menu.startMenu();
        verify(printStream).println(Messages.INVALID_OPTION);
    }

    @Test
    public void shouldShowCheckoutBookMessageAndCallCheckoutBook() throws IOException {
        when(bufferedReader.readLine()).thenReturn(MenuOptions.CHECKOUT_BOOK).thenReturn(String.valueOf(bookId)).thenReturn(MenuOptions.QUIT_APPLICATION);
        menu.startMenu();
        verify(printStream).println(Messages.CHECKOUT_BOOK);
        verify(biblioteca).checkoutBook(bookId);
    }

    @Test
    public void shouldShowReturnBookOptionAndCallReturnBook() throws IOException {
        when(bufferedReader.readLine()).thenReturn(MenuOptions.RETURN_BOOK).thenReturn(String.valueOf(bookId)).thenReturn(MenuOptions.QUIT_APPLICATION);
        menu.startMenu();
        verify(printStream).println(Messages.RETURN_BOOK_OPTION);
        verify(biblioteca).returnBook(bookId);
    }
}
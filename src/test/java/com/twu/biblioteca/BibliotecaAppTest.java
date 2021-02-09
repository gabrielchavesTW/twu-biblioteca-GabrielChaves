package com.twu.biblioteca;

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


    @BeforeEach
    public void setUp() {
        printStream = mock(PrintStream.class);
        bufferedReader = mock(BufferedReader.class);
        listOfBooks = new ArrayList<Book>(){
            {
                add(new Book("Hobbit"));
                add(new Book("Perdido em Marte"));
                add(new Book("Estação Carandiru"));
            }
        };
        biblioteca = new BibliotecaApp(printStream, bufferedReader, listOfBooks);
    }

    @Test
    public void shouldShowWelcomeMessage() {
        biblioteca.showWelcomeMessage();
        verify(printStream).println(biblioteca.WELCOME_MESSAGE);
    }

    @Test
    public void shouldShowMenuOfOptions() throws IOException {
        biblioteca.showMenu();
        verify(printStream).println(biblioteca.MENU);
    }

    @Test
    public void shouldReturnListOfBooksWhenOptionSelectedIsOne() throws IOException {
        when(bufferedReader.readLine()).thenReturn("1");
        biblioteca.readMenuOption();
        verify(printStream).println("\n1-Hobbit\n2-Perdido em Marte\n3-Estação Carandiru");
    }

    @Test
    public void shouldShowInvalidOption() throws IOException {
        when(bufferedReader.readLine()).thenReturn("132");
        biblioteca.readMenuOption();
        verify(printStream).println(biblioteca.INVALID_OPTION_MESSAGE);
    }
}
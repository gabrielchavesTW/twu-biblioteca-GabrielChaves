package com.twu.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class BibliotecaAppTest {
     PrintStream printStream;
     BibliotecaApp biblioteca;
     BufferedReader bufferedReader;

    @BeforeEach
    public void setUp() {
        printStream = mock(PrintStream.class);
        bufferedReader = mock(BufferedReader.class);
        biblioteca = new BibliotecaApp(printStream, bufferedReader);
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
        verify(printStream).println("Show list of books");
    }

    @Test
    public void shouldShowInvalidOption() throws IOException {
        when(bufferedReader.readLine()).thenReturn("132");
        biblioteca.readMenuOption();
        verify(printStream).println(biblioteca.INVALID_OPTION_MESSAGE);
    }
}
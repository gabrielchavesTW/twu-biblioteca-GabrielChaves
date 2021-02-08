package com.twu.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.PrintStream;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
    public void showWelcomeMessage() {
        biblioteca.showWelcomeMessage();
        verify(printStream).println(biblioteca.WELCOME_MESSAGE);
    }
}
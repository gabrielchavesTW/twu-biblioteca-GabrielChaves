package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.PrintStream;

public class BibliotecaApp {
    private PrintStream printStream;
    private BufferedReader bufferedReader;
    public String WELCOME_MESSAGE = "";

    public BibliotecaApp(PrintStream printStream, BufferedReader bufferedReader){
        this.printStream = printStream;
        this.bufferedReader = bufferedReader;
    }

    public void showWelcomeMessage(){
        printStream.println(WELCOME_MESSAGE);
    }
}

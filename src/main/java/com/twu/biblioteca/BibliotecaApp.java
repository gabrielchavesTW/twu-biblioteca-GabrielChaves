package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class BibliotecaApp {
    private PrintStream printStream;
    private BufferedReader bufferedReader;
    public static final String WELCOME_MESSAGE = "";
    public static final String MENU = "1- List of books";
    public static final String INVALID_OPTION_MESSAGE = "Invalid option.";

    public BibliotecaApp(PrintStream printStream, BufferedReader bufferedReader){
        this.printStream = printStream;
        this.bufferedReader = bufferedReader;
    }

    public void showWelcomeMessage(){
        printStream.println(WELCOME_MESSAGE);
    }

    public void showMenu() throws IOException {
        printStream.println(MENU);
        readMenuOption();
    }

    public void readMenuOption() throws IOException {
        String menuOption = bufferedReader.readLine();
        showSelectedMenuOption(menuOption);
    }

    private void showSelectedMenuOption(String menuOption) throws IOException {
        if(menuOption == "1")
            printStream.println("Show list of books");
        else {
            printStream.println(INVALID_OPTION_MESSAGE);
        }
    }
}

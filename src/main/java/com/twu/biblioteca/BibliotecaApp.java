package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.List;

public class BibliotecaApp {
    private PrintStream printStream;
    private BufferedReader bufferedReader;
    private List<Book> listOfBooks;
    public static final String WELCOME_MESSAGE = "";
    public static final String MENU = "1- List of books";
    public static final String INVALID_OPTION_MESSAGE = "Invalid option.";



    public BibliotecaApp(PrintStream printStream, BufferedReader bufferedReader, List<Book> listOfBooks){
        this.printStream = printStream;
        this.bufferedReader = bufferedReader;
        this.listOfBooks = listOfBooks;
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
            showListOfBooks();
        else {
            printStream.println(INVALID_OPTION_MESSAGE);
        }
    }

    private void showListOfBooks() {
        int index = 1;
        String outputString = "";
        for(Book book : listOfBooks){
            if(book.available){
                outputString += MessageFormat.format("\n{0}.Name: {1} | Author: {2} | Year: {3}", index, book.name, book.author, String.valueOf(book.year));
                index++;
            }
        }
        printStream.println(outputString);
    }
}

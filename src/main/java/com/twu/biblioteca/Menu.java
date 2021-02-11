package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class Menu {
    private final PrintStream printStream;
    private final BufferedReader bufferedReader;
    private final Biblioteca biblioteca;

    public Menu(PrintStream printStream, BufferedReader bufferedReader, Biblioteca biblioteca) {
        this.printStream = printStream;
        this.bufferedReader = bufferedReader;
        this.biblioteca = biblioteca;
    }

    public void showWelcomeMessage() {
        printStream.println(Messages.WELCOME);
    }

    public void showMenu() throws IOException {
        printStream.println(Messages.MENU);
        readMenuOption();
    }

    public void readMenuOption() throws IOException {
        String menuOption = bufferedReader.readLine();
        showSelectedMenuOption(menuOption);
    }

    private void showSelectedMenuOption(String menuOption) throws IOException {
        if (menuOption.equals("1")) {
            biblioteca.showListOfBooks();
        } else if (menuOption.equals("2")) {
            showCheckoutOption();
        } else if (menuOption.equals("3")) {
            showReturnBookOption();
        } else if (menuOption.equals("0")) {
            System.exit(0);
        } else {
            showInvalidMenuOptionMessage();
        }

        showMenu();
    }

    public void showInvalidMenuOptionMessage() {
        printStream.println(Messages.INVALID_OPTION);
    }


    public void showCheckoutOption() throws IOException {
        printStream.println(Messages.CHECKOUT_BOOK);
        readCheckoutOption();
    }

    private void readCheckoutOption() throws IOException {
        String checkoutOption = bufferedReader.readLine();
        biblioteca.checkoutBook(Integer.parseInt(checkoutOption));
    }

    public void showReturnBookOption() throws IOException {
        printStream.println(Messages.RETURN_BOOK_OPTION);
        readReturnBookOption();
    }

    private void readReturnBookOption() throws IOException {
        String returnBookOption = bufferedReader.readLine();
        biblioteca.returnBook(Integer.parseInt(returnBookOption));
    }
}

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

    private void showMenu()  {
        printStream.println(Messages.MENU);
    }

    public void startMenu() throws IOException {
        boolean closeMenu;
        do{
            showMenu();
            closeMenu = readMenuOption();
        } while(!closeMenu);
    }

    private boolean readMenuOption() throws IOException {
        String menuOption = bufferedReader.readLine();
        return showSelectedMenuOption(menuOption);
    }

    private boolean showSelectedMenuOption(String menuOption) throws IOException {
        boolean closeMenu = false;
        if (menuOption.equals(MenuOptions.SHOW_LIST_OF_BOOKS)) {
            biblioteca.showListOfBooks();
        } else if (menuOption.equals(MenuOptions.CHECKOUT_BOOK)) {
            showCheckoutOption();
        } else if (menuOption.equals(MenuOptions.RETURN_BOOK)) {
            showReturnBookOption();
        } else if (menuOption.equals(MenuOptions.QUIT_APPLICATION)) {
            closeMenu = true;
        } else {
            showInvalidMenuOptionMessage();
        }

        return closeMenu;
    }

    private void showInvalidMenuOptionMessage() {
        printStream.println(Messages.INVALID_OPTION);
    }


    private void showCheckoutOption() throws IOException {
        printStream.println(Messages.CHECKOUT_BOOK);
        readCheckoutOption();
    }

    private void readCheckoutOption() throws IOException {
        String checkoutOption = bufferedReader.readLine();
        biblioteca.checkoutBook(Integer.parseInt(checkoutOption));
    }

    private void showReturnBookOption() throws IOException {
        printStream.println(Messages.RETURN_BOOK_OPTION);
        readReturnBookOption();
    }

    private void readReturnBookOption() throws IOException {
        String returnBookOption = bufferedReader.readLine();
        biblioteca.returnBook(Integer.parseInt(returnBookOption));
    }
}

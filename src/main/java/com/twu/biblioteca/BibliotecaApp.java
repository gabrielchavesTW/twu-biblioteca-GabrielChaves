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
    public static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n";
    public static final String MENU = "1- List of books\n2- Checkout a book\n";
    public static final String INVALID_OPTION_MESSAGE = "Invalid option.";
    public static final String CHECKOUT_BOOK_MESSAGE = "Type the number of the book to checkout it.\n";
    public static final String CHECKOUT_SUCCESS_MESSAGE = "Thank you! Enjoy the book";
    public static final String INVALID_CHECKOUT_MESSAGE = "Sorry, that book is not vailable.";
    public static final String RETURN_BOOK_OPTION = "Type the book number that you want to return:\n";
    public static final String RETURN_BOOK_SUCCESS_MESSAGE = "Thank you for returning the book.";


    public BibliotecaApp(PrintStream printStream, BufferedReader bufferedReader, List<Book> listOfBooks) {
        this.printStream = printStream;
        this.bufferedReader = bufferedReader;
        this.listOfBooks = listOfBooks;
    }

    public void showMenu() throws IOException {
        printStream.println(WELCOME_MESSAGE);
        printStream.println(MENU);
        readMenuOption();
    }

    public void readMenuOption() throws IOException {
        String menuOption = bufferedReader.readLine();
        showSelectedMenuOption(menuOption);
    }

    private void showSelectedMenuOption(String menuOption) throws IOException {
        if (menuOption == "1")
            showListOfBooks();
        else if (menuOption == "2") {
            showCheckoutOption();
        } else if (menuOption == "3") {
            showReturnBookOption();
        } else {
            printStream.println(INVALID_OPTION_MESSAGE);
        }

    }

    private void showListOfBooks() {
        String outputString = "";
        for (Book book : listOfBooks) {
            if (book.available)
                outputString += MessageFormat.format("\n{0}.Name: {1} | Author: {2} | Year: {3}", book.id, book.name, book.author, String.valueOf(book.year));
        }
        printStream.println(outputString);
    }

    public void showCheckoutOption() throws IOException {
        printStream.println(CHECKOUT_BOOK_MESSAGE);
        readCheckoutOption();
    }

    private void readCheckoutOption() throws IOException {
        String checkoutOption = bufferedReader.readLine();
        checkoutBook(Integer.valueOf(checkoutOption));
    }

    private void checkoutBook(int bookId) {
        boolean bookFindedAndChecked = false;
        for (Book book : listOfBooks) {
            if (book.id == bookId) {
                book.setAvailable(false);
                bookFindedAndChecked = true;
            }
        }

        if (bookFindedAndChecked)
            printStream.println(CHECKOUT_SUCCESS_MESSAGE);
        else
            printStream.println(INVALID_CHECKOUT_MESSAGE);
    }

    private void showReturnBookOption() {
        printStream.println(RETURN_BOOK_OPTION);
    }

    public void returnBook(int bookId) {
        boolean anyBookReturned = false;
        for(Book book : listOfBooks){
            if(book.id == bookId){
                book.setAvailable(true);
                anyBookReturned = true;
            }
        }

        if(anyBookReturned)
            printStream.println(RETURN_BOOK_SUCCESS_MESSAGE);
    }
}

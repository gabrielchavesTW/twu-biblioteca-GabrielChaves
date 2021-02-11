package com.twu.biblioteca;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.List;

public class Biblioteca {
    PrintStream printStream;
    List<Book> books;



    public Biblioteca(PrintStream printStream, List<Book> books) {
        this.printStream = printStream;
        this.books = books;
    }

    public void showListOfBooks() {
        String outputString = "";
        for (Book book : books) {
            if (book.available)
                outputString += (MessageFormat.format("\n{0}.Name: {1} | Author: {2} | Year: {3}", book.id, book.name, book.author, String.valueOf(book.year)));
        }

        if(outputString.isEmpty()){
            outputString = Messages.NO_BOOKS_AVAILABLE;
        }

        printStream.println(outputString);
    }

    public void checkoutBook(Book book) {
        book.available = false;
        printStream.println(Messages.BOOK_CHECKOUT_SUCCESS);
    }
}

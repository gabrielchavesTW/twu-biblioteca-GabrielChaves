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
            if (book.isAvailable())
                outputString += (MessageFormat.format("\n{0}.Name: {1} | Author: {2} | Year: {3}", book.id, book.name, book.author, String.valueOf(book.year)));
        }

        if(outputString.isEmpty()){
            outputString = Messages.NO_BOOKS_AVAILABLE;
        }

        printStream.println(outputString);
    }

    public void checkoutBook(int bookId) {
        boolean bookChecked = false;
        for(Book book : books){
            if(book.id == bookId && book.isAvailable()){
                book.setAvailable(false);
                bookChecked = true;
            }
        }
        if(bookChecked)
            printStream.println(Messages.BOOK_CHECKOUT_SUCCESS);
        else
            printStream.println(Messages.BOOK_CHECKOUT_UNSUCCESS);
    }

    public void returnBook(int bookId) {
        boolean bookReturned = false;
        for(Book book : books){
            if(book.id == bookId && !book.isAvailable()){
                book.setAvailable(true);
                bookReturned = true;
            }
        }
        if(bookReturned)
            printStream.println(Messages.BOOK_RETURN_SUCCESS);
        else
            printStream.println(Messages.BOOK_RETURN_UNSUCCESS);
    }
}

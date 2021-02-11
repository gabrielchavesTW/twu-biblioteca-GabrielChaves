package com.twu.biblioteca;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BibliotecaTest {
    PrintStream printStream;
    Biblioteca biblioteca;
    List<Book> books;
    Book bookOne;
    Book bookTwo;
    Book bookThree;

    @BeforeEach
    public void setUp(){
        this.printStream = mock(PrintStream.class);
        bookOne = new Book(1, "Hobbit", "J. R. R. Tolkien", 1937);
        bookTwo = new Book(2, "Perdido em Marte", "Andy Weir", 2011);
        bookThree = new Book(3, "Estação Carandiru", "Drauzio Varella", 1999);
        books = new ArrayList<Book>() {
            {
                add(bookOne);
                add(bookTwo);
                add(bookThree);
            }
        };
        this.biblioteca = new Biblioteca(printStream, books);
    }

    @Test
    public void shouldPrintListOfBooks(){
        biblioteca.showListOfBooks();
        verify(printStream).println("\n1.Name: Hobbit | Author: J. R. R. Tolkien | Year: 1937" +
                "\n2.Name: Perdido em Marte | Author: Andy Weir | Year: 2011" +
                "\n3.Name: Estação Carandiru | Author: Drauzio Varella | Year: 1999");
    }

    @Test
    public void shouldCheckoutABook(){
        biblioteca.checkoutBook(bookOne.id);
        verify(printStream).println(Messages.BOOK_CHECKOUT_SUCCESS);
        Assertions.assertFalse(bookOne.isAvailable());
    }

    @Test
    public void shouldShowOnlyAvailableBooks()
    {
        biblioteca.checkoutBook(bookOne.id);
        biblioteca.showListOfBooks();
        verify(printStream).println("\n2.Name: Perdido em Marte | Author: Andy Weir | Year: 2011" +
                "\n3.Name: Estação Carandiru | Author: Drauzio Varella | Year: 1999");
    }

    @Test
    public void shouldShowBookCheckoutUnsuccessMessageWhenBookIdDoenstExist(){
        biblioteca.checkoutBook(1332);
        verify(printStream).println(Messages.BOOK_CHECKOUT_UNSUCCESS);
        Assertions.assertTrue(bookOne.isAvailable());
        Assertions.assertTrue(bookTwo.isAvailable());
        Assertions.assertTrue(bookThree.isAvailable());
    }

    @Test
    public void shouldReturnABook(){
        bookOne.setAvailable(false);
        biblioteca.returnBook(bookOne.id);
        verify(printStream).println(Messages.BOOK_RETURN_SUCCESS);
        Assertions.assertTrue(bookOne.isAvailable());
    }

    @Test
    public void shouldShowUnsuccessReturnMessageWhenBookIdDoesntExist(){
        biblioteca.returnBook(1332);
        verify(printStream).println(Messages.BOOK_RETURN_UNSUCCESS);
    }

    @Test
    public void shouldShowNoBooksAvailableMessage(){
        bookOne.setAvailable(false);
        bookTwo.setAvailable(false);
        bookThree.setAvailable(false);
        biblioteca.showListOfBooks();

        verify(printStream).println(Messages.NO_BOOKS_AVAILABLE_MESSAGE);
    }
}

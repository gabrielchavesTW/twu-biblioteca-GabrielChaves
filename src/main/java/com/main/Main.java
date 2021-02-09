package com.main;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BibliotecaApp biblioteca = new BibliotecaApp(System.out, new BufferedReader(new InputStreamReader(System.in)), books());
        biblioteca.showMenu();
    }

    private static List<Book> books() {
        return new ArrayList<Book>() {
            {
                add(new Book(1, "Hobbit", "J. R. R. Tolkien", 1937));
                add(new Book(2, "Perdido em Marte", "Andy Weir", 2011));
                add(new Book(3, "Estação Carandiru", "Drauzio Varella", 1999));
            }
        };
    }
}

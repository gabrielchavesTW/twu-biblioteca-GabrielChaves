package com.main;

import com.twu.biblioteca.Biblioteca;
import com.twu.biblioteca.Menu;
import com.twu.biblioteca.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Biblioteca biblioteca = new Biblioteca(System.out, books());
        Menu menu = new Menu(System.out, new BufferedReader(new InputStreamReader(System.in)), biblioteca);
        menu.showWelcomeMessage();
        menu.startMenu();
        System.exit(0);
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

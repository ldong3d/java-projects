package org.everybody.librarymanagementsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(String title, String author) {
        Book book = new Book(title, author);
        books.add(book);
    }

    public void searchBook(String keyword) {
        List<Book> searchResults = new ArrayList<>();

        for (Book book : books) {
            if (book.getTitle().contains(keyword) || book.getAuthor().contains(keyword)) {
                searchResults.add(book);
            }
        }

        if (searchResults.isEmpty()) {
            System.out.println("No books found matching the search keyword.");
        } else {
            System.out.println("Search results:");
            for (Book book : searchResults) {
                System.out.println(book.getTitle() + " by " + book.getAuthor());
            }
        }
    }

    public void checkoutBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                if (!book.isCheckedOut()) {
                    book.setCheckedOut(true);
                    System.out.println("Book '" + title + "' checked out successfully.");
                } else {
                    System.out.println("Book '" + title + "' is already checked out.");
                }
                return;
            }
        }

        System.out.println("Book '" + title + "' not found in the library.");
    }

    public void returnBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                if (book.isCheckedOut()) {
                    book.setCheckedOut(false);
                    System.out.println("Book '" + title + "' returned successfully.");
                } else {
                    System.out.println("Book '" + title + "' is not checked out.");
                }
                return;
            }
        }

        System.out.println("Book '" + title + "' not found in the library.");
    }

    public void saveBooksToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (Book book : books) {
                writer.println(book.getTitle() + "," + book.getAuthor() + "," + book.isCheckedOut());
            }
            System.out.println("Books saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving books to file: " + e.getMessage());
        }
    }

    public void loadBooksFromFile(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            books.clear();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String title = parts[0];
                String author = parts[1];
                boolean checkedOut = Boolean.parseBoolean(parts[2]);
                Book book = new Book(title, author);
                book.setCheckedOut(checkedOut);
                books.add(book);
            }
            System.out.println("Books loaded from file: " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }
}

package org.everybody.librarymanagementsystem;

import java.io.*;
import java.util.*;

public class LibraryManagementSystem {
    public static void RunLibraryManagementSystem(String[] args) {
        Library library = new Library();

        // Loading books from a file (if available)
        library.loadBooksFromFile("library.txt");

        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("----- Library Management System -----");
            System.out.println("1. Add a book");
            System.out.println("2. Search for books");
            System.out.println("3. Check out a book");
            System.out.println("4. Return a book");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                System.out.println();

                switch (choice) {
                    case 1:
                        System.out.print("Enter the title of the book: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter the author of the book: ");
                        String author = scanner.nextLine();
                        library.addBook(title, author);
                        System.out.println("Book added successfully.\n");
                        break;
                    case 2:
                        System.out.print("Enter the search keyword: ");
                        String keyword = scanner.nextLine();
                        library.searchBook(keyword);
                        System.out.println();
                        break;
                    case 3:
                        System.out.print("Enter the title of the book to check out: ");
                        String checkoutTitle = scanner.nextLine();
                        library.checkoutBook(checkoutTitle);
                        System.out.println();
                        break;
                    case 4:
                        System.out.print("Enter the title of the book to return: ");
                        String returnTitle = scanner.nextLine();
                        library.returnBook(returnTitle);
                        System.out.println();
                        break;
                    case 0:
                        // Saving books to a file
                        library.saveBooksToFile("library.txt");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.\n");
                }
            } else {
                System.out.println("Invalid input. Please try again.\n");
                scanner.nextLine(); // Consume invalid input
            }
        }

       // scanner.close();
    }
}


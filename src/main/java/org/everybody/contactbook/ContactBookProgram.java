package org.everybody.contactbook;

import java.util.Scanner;

public class ContactBookProgram {
    public static void RunContactBook(String[] args) {
        ContactBook contactBook = new ContactBook();
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("----- Contact Book -----");
            System.out.println("1. Add a contact");
            System.out.println("2. View contacts");
            System.out.println("3. Edit a contact");
            System.out.println("4. Delete a contact");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                System.out.println();

                switch (choice) {
                    case 1:
                        System.out.print("Enter the name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter the phone number: ");
                        String phoneNumber = scanner.nextLine();
                        System.out.print("Enter the email: ");
                        String email = scanner.nextLine();
                        contactBook.addContact(name, phoneNumber, email);
                        System.out.println();
                        break;
                    case 2:
                        contactBook.viewContacts();
                        System.out.println();
                        break;
                    case 3:
                        System.out.print("Enter the index of the contact to edit: ");
                        int editIndex = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character
                        System.out.print("Enter the new name: ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter the new phone number: ");
                        String newPhoneNumber = scanner.nextLine();
                        System.out.print("Enter the new email: ");
                        String newEmail = scanner.nextLine();
                        contactBook.editContact(editIndex - 1, newName, newPhoneNumber, newEmail);
                        System.out.println();
                        break;
                    case 4:
                        System.out.print("Enter the index of the contact to delete: ");
                        int deleteIndex = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character
                        contactBook.deleteContact(deleteIndex - 1);
                        System.out.println();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.\n");
                }
            } else {
                System.out.println("Invalid input. Please try again.\n");
                //scanner.nextLine(); // Consume invalid input
            }
        }

        scanner.close();
    }
}
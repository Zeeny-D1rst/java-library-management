package dev.piettro.library;

import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        LibraryService library = new LibraryService();
        library.addBook(new Book("978-0132350884", "Clean Code", "Robert C. Martin"));
        library.addBook(new Book("978-0201633610", "Design Patterns", "Erich Gamma et al."));
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                System.out.println("\n=== Library Manager ===\n1. List books\n2. Add book\n3. Search\n4. Borrow\n5. Return\n0. Exit");
                System.out.print("Choose: ");
                String option = scanner.nextLine().trim();
                try {
                    switch (option) {
                        case "1" -> library.getAllBooks().forEach(System.out::println);
                        case "2" -> {
                            System.out.print("ISBN: "); String isbn = scanner.nextLine();
                            System.out.print("Title: "); String title = scanner.nextLine();
                            System.out.print("Author: "); String author = scanner.nextLine();
                            library.addBook(new Book(isbn, title, author));
                            System.out.println("Book registered.");
                        }
                        case "3" -> {
                            System.out.print("Search: ");
                            library.search(scanner.nextLine()).forEach(System.out::println);
                        }
                        case "4" -> { System.out.print("ISBN: "); library.borrow(scanner.nextLine().trim()); System.out.println("Loan registered."); }
                        case "5" -> { System.out.print("ISBN: "); library.returnBook(scanner.nextLine().trim()); System.out.println("Book returned."); }
                        case "0" -> running = false;
                        default -> System.out.println("Invalid option.");
                    }
                } catch (IllegalArgumentException | IllegalStateException exception) {
                    System.out.println("Error: " + exception.getMessage());
                }
            }
        }
    }
}

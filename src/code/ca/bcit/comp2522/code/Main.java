package ca.bcit.comp2522.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Driver class demonstrating all eight lab tasks.
 *
 * @author Ziad Malik, Sebastion Roby, Evan Tang, Jack Moscovitch
 * @version 1.0
 */
public final class Main
{
    /** Separator printed between tasks. */
    private static final String SEPARATOR = "─".repeat(50);

    /** Index of the first book used in single-book demonstrations. */
    private static final int FIRST_BOOK_INDEX = 0;

    private Main() { }

    // Task 1

    /**
     * Prints a book's title, genre, year, and page count.
     * Referenced as {@code Main::printBook} in Tasks 1 and 2.
     *
     * @param b the book to print
     */
    public static void printBook(final ca.bcit.comp2522.code.Book b)
    {
        System.out.println(b.getTitle()         + " | " +
                b.getGenre()          + " | " +
                b.getYearPublished()  + " | " +
                b.getPageCount()      + " pages");
    }

    // ── Task 4 ───────────────────────────────────────────────────────────────

    /**
     * Applies {@code action} to every element in {@code list}.
     * The wildcard {@code ? extends T} is required because generics are
     * invariant — {@code List<Book>} is not a subtype of {@code List<Object>}.
     *
     * @param <T>    the element type consumed by the action
     * @param list   the list to iterate
     * @param action the operation applied to each element
     */
    public static <T> void printAll(final List<? extends T> list,
                                    final Consumer<T>       action)
    {
        for(final T element : list)
        {
            action.accept(element);
        }
    }

    // ── Task 7 ───────────────────────────────────────────────────────────────

    /**
     * Demonstrates a local inner class by printing a checkout receipt.
     *
     * @param lib  the library the book is checked out from
     * @param book the book being checked out
     */
    private static void processCheckout(final ca.bcit.comp2522.code.Library lib,
                                        final ca.bcit.comp2522.code.Book book)
    {
        // CheckoutReceipt captures lib and book — both are effectively final
        class CheckoutReceipt
        {
            void print()
            {
                System.out.println("Library : " + lib.getName());
                System.out.println("Book    : " + book.getTitle());
                System.out.println("Year    : " + book.getYearPublished());
            }
        }

        final CheckoutReceipt receipt;
        receipt = new CheckoutReceipt();
        receipt.print();
    }

    // ── Sample data ──────────────────────────────────────────────────────────

    private static ca.bcit.comp2522.code.Library sampleLibrary()
    {
        final List<ca.bcit.comp2522.code.Book> books;
        books = new ArrayList<>();

        books.add(new ca.bcit.comp2522.code.Book("The Great Gatsby",         ca.bcit.comp2522.code.Book.GENRE_FICTION,    1925, 180));
        books.add(new ca.bcit.comp2522.code.Book("Thinking, Fast & Slow",    ca.bcit.comp2522.code.Book.GENRE_NONFICTION, 2011, 499));
        books.add(new ca.bcit.comp2522.code.Book("Clean Code",               ca.bcit.comp2522.code.Book.GENRE_REFERENCE,  2008, 431));
        books.add(new ca.bcit.comp2522.code.Book("Dune",                     ca.bcit.comp2522.code.Book.GENRE_FICTION,    1965, 412));
        books.add(new ca.bcit.comp2522.code.Book("Sapiens",                  ca.bcit.comp2522.code.Book.GENRE_NONFICTION, 2011, 443));
        books.add(new ca.bcit.comp2522.code.Book("The Pragmatic Programmer", ca.bcit.comp2522.code.Book.GENRE_REFERENCE,  1999, 352));

        return new ca.bcit.comp2522.code.Library("BCIT Digital Library", books);
    }

    // ── main ─────────────────────────────────────────────────────────────────

    /**
     * Entry point — runs all eight tasks in order.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(final String[] args)
    {
        final ca.bcit.comp2522.code.Library library;
        final List<ca.bcit.comp2522.code.Book> catalog;

        library = sampleLibrary();
        catalog = library.getCatalog();

        // ════════════════════════════════════════════════════════════════
        // Task 1 — Four Types of Method References
        // ════════════════════════════════════════════════════════════════
        System.out.println("Task 1: Four Types of Method References");
        System.out.println(SEPARATOR);

        // Type 1 — Static: Main::printBook replaces b -> Main.printBook(b)
        final Consumer<ca.bcit.comp2522.code.Book> staticRef;
        staticRef = Main::printBook;

        System.out.println("Type 1 (static — Main::printBook):");
        staticRef.accept(catalog.get(FIRST_BOOK_INDEX));

        // Type 2 — Instance on a specific object: extraLibrary::addBook
        final List<ca.bcit.comp2522.code.Book> emptyList;
        final ca.bcit.comp2522.code.Library extraLibrary;
        final Consumer<ca.bcit.comp2522.code.Book> instanceRef;

        emptyList    = new ArrayList<>();
        extraLibrary = new ca.bcit.comp2522.code.Library("Extra Library", emptyList);
        instanceRef  = extraLibrary::addBook;

        instanceRef.accept(catalog.get(FIRST_BOOK_INDEX));
        System.out.println("\nType 2 (instance on specific object — extraLibrary::addBook):");
        System.out.println("Extra library size after add: " + extraLibrary.getCatalog().size());

        // Type 3 — Instance on any object of a type: Book::getTitle
        final Function<ca.bcit.comp2522.code.Book, String> typeRef;
        typeRef = ca.bcit.comp2522.code.Book::getTitle;

        System.out.println("\nType 3 (instance on any object of a type — Book::getTitle):");
        System.out.println(typeRef.apply(catalog.get(FIRST_BOOK_INDEX)));

        // Type 4 — Constructor reference: Book::new (copy constructor)
        final Function<ca.bcit.comp2522.code.Book, ca.bcit.comp2522.code.Book> constructorRef;
        final ca.bcit.comp2522.code.Book copiedBook;

        constructorRef = ca.bcit.comp2522.code.Book::new;
        copiedBook     = constructorRef.apply(catalog.get(FIRST_BOOK_INDEX));

        System.out.println("\nType 4 (constructor reference — Book::new):");
        System.out.println("Copy is not null: " + (copiedBook != null));
        System.out.println("Copy: " + copiedBook);

        // ════════════════════════════════════════════════════════════════
        // Task 2 — Method References with forEach
        // ════════════════════════════════════════════════════════════════
        System.out.println("\nTask 2: Method References with forEach");
        System.out.println(SEPARATOR);

        // Main::printBook as Consumer<Book> passed to forEach
        System.out.println("All books via catalog.forEach(Main::printBook):");
        catalog.forEach(Main::printBook);

        // String::toUpperCase and System.out::println assigned and used in a loop
        final Function<String, String> toUpperCase;
        final Consumer<String>         printLine;

        toUpperCase = String::toUpperCase;
        printLine   = System.out::println;

        System.out.println("\nTitles in uppercase:");
        for(final ca.bcit.comp2522.code.Book book : catalog)
        {
            printLine.accept(toUpperCase.apply(book.getTitle()));
        }

        // ════════════════════════════════════════════════════════════════
        // Task 3 — Shelf<T> Generic Container
        // ════════════════════════════════════════════════════════════════
        System.out.println("\nTask 3: Shelf<T> Generic Container");
        System.out.println(SEPARATOR);

        // Shelf<Integer>: T bound to Integer (implements Comparable<Integer>)
        final ca.bcit.comp2522.code.Shelf<Integer> pageCountShelf;
        pageCountShelf = new ca.bcit.comp2522.code.Shelf<>();

        for(final ca.bcit.comp2522.code.Book book : catalog)
        {
            pageCountShelf.add(book.getPageCount());
        }

        System.out.println("Shelf<Integer> (page counts):");
        System.out.println("  Smallest : " + pageCountShelf.getSmallest());
        System.out.println("  Largest  : " + pageCountShelf.getLargest());

        // Shelf<String>: T bound to String (implements Comparable<String>)
        final ca.bcit.comp2522.code.Shelf<String> titleShelf;
        titleShelf = new ca.bcit.comp2522.code.Shelf<>();

        for(final ca.bcit.comp2522.code.Book book : catalog)
        {
            titleShelf.add(book.getTitle());
        }

        System.out.println("\nShelf<String> (titles, alphabetical):");
        System.out.println("  Smallest : " + titleShelf.getSmallest());
        System.out.println("  Largest  : " + titleShelf.getLargest());

        // ════════════════════════════════════════════════════════════════
        // Task 4 — printAll Wildcard Utility
        // ════════════════════════════════════════════════════════════════
        System.out.println("\nTask 4: printAll Wildcard Utility");
        System.out.println(SEPARATOR);

        // T inferred as Book; List<Book> satisfies List<? extends Book>
        System.out.println("Titles via printAll:");
        printAll(catalog, b -> System.out.println("  " + b.getTitle()));

        System.out.println("\nGenres via printAll:");
        printAll(catalog, b -> System.out.println("  " + b.getGenre()));

        // ════════════════════════════════════════════════════════════════
        // Task 5 — LibraryStats Static Nested Class
        // ════════════════════════════════════════════════════════════════
        System.out.println("\nTask 5: LibraryStats Static Nested Class");
        System.out.println(SEPARATOR);

        // Static — no Library instance needed
        final ca.bcit.comp2522.code.Library.LibraryStats stats;
        stats = new ca.bcit.comp2522.code.Library.LibraryStats();

        System.out.println(ca.bcit.comp2522.code.Book.GENRE_FICTION    + " count   : " +
                stats.countByGenre(catalog, ca.bcit.comp2522.code.Book.GENRE_FICTION));
        System.out.println(ca.bcit.comp2522.code.Book.GENRE_NONFICTION + " count: " +
                stats.countByGenre(catalog, ca.bcit.comp2522.code.Book.GENRE_NONFICTION));
        System.out.println(ca.bcit.comp2522.code.Book.GENRE_REFERENCE  + " count : " +
                stats.countByGenre(catalog, ca.bcit.comp2522.code.Book.GENRE_REFERENCE));
        System.out.println("Average pages      : " +
                stats.averagePageCount(catalog));

        // ════════════════════════════════════════════════════════════════
        // Task 6 — Librarian Non-Static Inner Class
        // ════════════════════════════════════════════════════════════════
        System.out.println("\nTask 6: Librarian Non-Static Inner Class");
        System.out.println(SEPARATOR);

        // Non-static — must be created via an existing Library instance
        final ca.bcit.comp2522.code.Library.Librarian librarian;
        librarian = library.new Librarian("Alex");
        librarian.recommend();

        // ════════════════════════════════════════════════════════════════
        // Task 7 — CheckoutReceipt Local Inner Class
        // ════════════════════════════════════════════════════════════════
        System.out.println("\nTask 7: CheckoutReceipt Local Inner Class");
        System.out.println(SEPARATOR);

        processCheckout(library, catalog.get(FIRST_BOOK_INDEX));

        // ════════════════════════════════════════════════════════════════
        // Task 8 — Anonymous Inner Class Comparator
        // ════════════════════════════════════════════════════════════════
        System.out.println("\nTask 8: Anonymous Inner Class Comparator");
        System.out.println(SEPARATOR);

        Collections.sort(catalog, new Comparator<ca.bcit.comp2522.code.Book>()
        {
            @Override
            public int compare(final ca.bcit.comp2522.code.Book b1, final ca.bcit.comp2522.code.Book b2)
            {
                return Integer.compare(b2.getPageCount(), b1.getPageCount());
            }
        });

        System.out.println("Catalog sorted by page count (descending):");
        catalog.forEach(Main::printBook);
    }
}

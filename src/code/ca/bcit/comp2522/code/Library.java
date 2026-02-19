package ca.bcit.comp2522.code;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a named library that holds a catalog of books.
 * Contains two nested classes: the static {@link LibraryStats} and
 * the non-static {@link Librarian}.
 *
 * @author Ziad Malik, Sebastion Roby, Evan Tang, Jack Moscovitch
 * @version 1.0
 */
public final class Library
{
    /** Index of the first book in the catalog. */
    private static final int FIRST_BOOK_INDEX = 0;

    private final String     name;
    private final List<Book> catalog;

    /**
     * Constructs a Library with the given name and initial books.
     *
     * @param name         the library's name
     * @param initialBooks the starting catalog
     */
    public Library(final String     name,
                   final List<Book> initialBooks)
    {
        validateName(name);
        validateInitialBooks(initialBooks);

        this.name    = name;
        this.catalog = new ArrayList<>(initialBooks);
    }

    /**
     * Adds a book to the catalog.
     * Used as an instance method reference {@code extraLibrary::addBook} in Task 1.
     *
     * @param book the book to add
     */
    public void addBook(final Book book)
    {
        validateBook(book);
        catalog.add(book);
    }

    /**
     * Returns the library's name.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the catalog list directly.
     * The caller may add, remove, or sort entries.
     *
     * @return the mutable catalog list
     */
    public List<Book> getCatalog()
    {
        return catalog;
    }

    /**
     * @throws IllegalArgumentException if name is null or blank
     */
    private static void validateName(final String name)
    {
        if(name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Library name must not be null or blank.");
        }
    }

    /*
     * @throws IllegalArgumentException if initialBooks is null
     */
    private static void validateInitialBooks(final List<Book> initialBooks)
    {
        if(initialBooks == null)
        {
            throw new IllegalArgumentException("Initial books list must not be null.");
        }
    }

    /*
     * @throws IllegalArgumentException if book is null
     */
    private static void validateBook(final Book book)
    {
        if(book == null)
        {
            throw new IllegalArgumentException("Book must not be null.");
        }
    }

    // Task 5 — Static Nested Class

    /**
     * Provides catalog statistics.
     * Static — no {@link Library} instance is needed to instantiate it.
     */
    public static final class LibraryStats
    {
        /**
         * Returns the number of books whose genre matches the given value.
         *
         * @param books the list to examine
         * @param genre the genre to match
         * @return count of matching books
         */
        public int countByGenre(final List<Book> books,
                                final String     genre)
        {
            int count;
            count = 0;

            for(final Book book : books)
            {
                if(genre.equals(book.getGenre()))
                {
                    count++;
                }
            }

            return count;
        }

        /**
         * Returns the average page count across all books.
         *
         * @param books the list to average
         * @return mean page count as a double
         */
        public double averagePageCount(final List<Book> books)
        {
            int total;
            total = 0;

            for(final Book book : books)
            {
                total += book.getPageCount();
            }

            return (double) total / books.size();
        }
    }

    // Task 6 — Non-Static Inner Class

    /**
     * A librarian who works at the enclosing {@link Library}.
     * Non-static — holds an implicit reference to the outer instance,
     * allowing direct access to its private fields.
     */
    public final class Librarian
    {
        private final String name;

        /**
         * Constructs a Librarian with the given name.
         *
         * @param name the librarian's name
         */
        public Librarian(final String name)
        {
            validateLibrarianName(name);
            this.name = name;
        }

        /**
         * Prints the enclosing library's name and the first book in its catalog.
         * Accesses the outer class fields {@code Library.this.name} and {@code catalog} directly.
         */
        public void recommend()
        {
            final Book firstBook;
            firstBook = catalog.get(FIRST_BOOK_INDEX);

            System.out.println(name + " at " + Library.this.name +
                    " recommends: " + firstBook);
        }

        /**
         * @throws IllegalArgumentException if name is null or blank
         */
        private static void validateLibrarianName(final String name)
        {
            if(name == null || name.isBlank())
            {
                throw new IllegalArgumentException("Librarian name must not be null or blank.");
            }
        }
    }
}

package ca.bcit.comp2522.code;

/**
 * Represents a book in the library catalog.
 *
 * @author Ziad Malik, Sebastion Roby, Evan Tang, Jack Moscovitch
 * @version 1.0
 */
public final class Book
{
    /** Accepted genre value for fiction. */
    public static final String GENRE_FICTION    = "Fiction";

    /** Accepted genre value for non-fiction. */
    public static final String GENRE_NONFICTION = "NonFiction";

    /** Accepted genre value for reference. */
    public static final String GENRE_REFERENCE  = "Reference";

    private final String title;
    private final String genre;
    private final int    yearPublished;
    private final int    pageCount;

    /**
     * Constructs a Book with the given details.
     *
     * @param title         the book's title
     * @param genre         the genre; must be one of {@link #GENRE_FICTION},
     *                      {@link #GENRE_NONFICTION}, or {@link #GENRE_REFERENCE}
     * @param yearPublished the year published
     * @param pageCount     the number of pages
     */
    public Book(final String title,
                final String genre,
                final int    yearPublished,
                final int    pageCount)
    {
        validateTitle(title);
        validateGenre(genre);

        this.title         = title;
        this.genre         = genre;
        this.yearPublished = yearPublished;
        this.pageCount     = pageCount;
    }

    /**
     * Copy constructor — used as a constructor reference {@code Book::new} in Task 1.
     *
     * @param source the Book to copy
     */
    public Book(final Book source)
    {
        validateSource(source);

        this.title         = source.title;
        this.genre         = source.genre;
        this.yearPublished = source.yearPublished;
        this.pageCount     = source.pageCount;
    }

    /**
     * Returns the title.
     *
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Returns the genre.
     *
     * @return the genre
     */
    public String getGenre()
    {
        return genre;
    }

    /**
     * Returns the year published.
     *
     * @return the year published
     */
    public int getYearPublished()
    {
        return yearPublished;
    }

    /**
     * Returns the page count.
     *
     * @return the page count
     */
    public int getPageCount()
    {
        return pageCount;
    }

    /**
     * Returns a string containing all four fields.
     *
     * @return formatted book string
     */
    @Override
    public String toString()
    {
        return title        +
                " ["  + genre        +
                ", "  + yearPublished +
                ", "  + pageCount    + " pages]";
    }

    /*
     * Validates that title is not null or blank.
     *
     * @param title the title to check
     * @throws IllegalArgumentException if title is null or blank
     */
    private static void validateTitle(final String title)
    {
        if(title == null || title.isBlank())
        {
            throw new IllegalArgumentException("Title must not be null or blank.");
        }
    }

    /*
     * Validates that genre is one of the three accepted constants.
     *
     * @param genre the genre to check
     * @throws IllegalArgumentException if genre is not recognised
     */
    private static void validateGenre(final String genre)
    {
        if(!GENRE_FICTION.equals(genre)    &&
                !GENRE_NONFICTION.equals(genre) &&
                !GENRE_REFERENCE.equals(genre))
        {
            throw new IllegalArgumentException(
                    "Genre must be " + GENRE_FICTION    + ", " +
                            GENRE_NONFICTION + ", or "           +
                            GENRE_REFERENCE  + ".");
        }
    }

    /*
     * Validates that source is not null.
     *
     * @param source the source Book to check
     * @throws IllegalArgumentException if source is null
     */
    private static void validateSource(final Book source)
    {
        if(source == null)
        {
            throw new IllegalArgumentException("Source Book must not be null.");
        }
    }
}

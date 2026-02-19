package ca.bcit.comp2522.code;

import java.util.ArrayList;
import java.util.List;

/**
 * A generic shelf that stores items of any {@link Comparable} type.
 *
 * @param <T> the item type; must implement {@link Comparable} so that
 *            {@code compareTo} can be called inside {@link #getSmallest()}
 *            and {@link #getLargest()}
 *
 * @author Ziad Malik, Sebastion Roby, Evan Tang, Jack Moscovitch
 * @version 1.0
 */
public final class Shelf<T extends Comparable<T>>
{
    /** Index used as the starting reference when scanning for min/max. */
    private static final int FIRST_INDEX = 0;

    private final List<T> items;

    /**
     * Constructs an empty Shelf.
     */
    public Shelf()
    {
        items = new ArrayList<>();
    }

    /**
     * Adds an item to this shelf.
     *
     * @param item the item to add
     */
    public void add(final T item)
    {
        validateItem(item);
        items.add(item);
    }

    /**
     * Returns the smallest item by natural ordering.
     *
     * @return the item that compares as smallest
     */
    public T getSmallest()
    {
        validateNotEmpty();

        T smallest;
        smallest = items.get(FIRST_INDEX);

        for(final T item : items)
        {
            if(item.compareTo(smallest) < 0)
            {
                smallest = item;
            }
        }

        return smallest;
    }

    /**
     * Returns the largest item by natural ordering.
     *
     * @return the item that compares as largest
     */
    public T getLargest()
    {
        validateNotEmpty();

        T largest;
        largest = items.get(FIRST_INDEX);

        for(final T item : items)
        {
            if(item.compareTo(largest) > 0)
            {
                largest = item;
            }
        }

        return largest;
    }

    /**
     * Validates that the item to add is not null.
     *
     * @param item the item to check
     * @throws IllegalArgumentException if item is null
     */
    private void validateItem(final T item)
    {
        if(item == null)
        {
            throw new IllegalArgumentException("Item must not be null.");
        }
    }

    /**
     * Validates that the shelf is not empty before a min/max query.
     *
     * @throws IllegalStateException if the shelf is empty
     */
    private void validateNotEmpty()
    {
        if(items.isEmpty())
        {
            throw new IllegalStateException("Shelf is empty.");
        }
    }
}

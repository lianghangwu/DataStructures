package ListExercise2_AList;

import java.util.Arrays;

public class AList<T> implements ListInterface<T> {
    private T[] list; // Array of list entries; ignore list[0]
    private int numberOfEntries;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    public AList(int initialCapacity)
    {
        if(initialCapacity < DEFAULT_CAPACITY)
            initialCapacity = DEFAULT_CAPACITY;
        else
            checkCapacity(initialCapacity);

        @SuppressWarnings("unchecked")
        T[] tempList = (T[]) new Object[initialCapacity + 1];
        list = tempList;
        numberOfEntries = 0;
        initialized = true;
    }
    public AList()
    {
        this(DEFAULT_CAPACITY);
    }

    // Doubles the capacity of the array list if it is full.
    // Precondition: checkInitialization has been called.
    private void ensureCapacity()
    {
        int capacity = list.length - 1;
        if (numberOfEntries >= capacity)
        {
            int newCapacity = 2 * capacity;
            checkCapacity(newCapacity); // Is capacity too big?
            list = Arrays.copyOf(list, newCapacity + 1);
        } // end if
    } // end ensureCapacity

    // Makes room for a new entry at newPosition.
    // Precondition: 1 <= newPosition <= numberOfEntries + 1;
    // numberOfEntries is list's length before addition;
    // checkInitialization has been called.
    private void makeRoom(int newPosition)
    {
        assert (newPosition >= 1) && (newPosition <= numberOfEntries + 1);

        int newIndex = newPosition;
        int lastIndex = numberOfEntries;
        // Move each entry to next higher index, starting at end of
        // list and continuing until the entry at newIndex is moved
        for (int index = lastIndex; index >= newIndex; index--)
            list[index + 1] = list[index];
    } // end makeRoom

    // Shifts entries that are beyond the entry to be removed to the
    // next lower position.
    // Precondition: 1 <= givenPosition < numberOfEntries;
    // numberOfEntries is list's length before removal;
    // checkInitialization has been called.
    private void removeGap(int givenPosition)
    {
        assert (givenPosition >= 1) && (givenPosition < numberOfEntries);

        int removedIndex = givenPosition;
        int lastIndex = numberOfEntries;

        for (int index = removedIndex; index < lastIndex; index++)
            list[index] = list[index + 1];
    } // end removeGap

    // Throws an exception if this object is not initialized.
    private void checkInitialization()
    {
        if (!initialized)
            throw new SecurityException ("ListExercise2.AList object is not initialized properly.");
    } // end checkInitialization

    // Throws an exception if the client requests a capacity that is too large.
    private void checkCapacity(int capacity)
    {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a list " +
                    "whose capacity exceeds " +
                    "allowed maximum.");
    } // end checkCapacity

    // Adds a new entry to the end of this list
    public void add(T newEntry)
    {
        checkInitialization();
        list[numberOfEntries + 1] = newEntry;
        numberOfEntries ++;
        ensureCapacity();
    }

    // Adds a new entry at a specified position within this list.
    public void add(int newPosition, T newEntry)
    {
        checkInitialization();
        if(newPosition >= 1 && newPosition <= numberOfEntries + 1)
        {
            if(newPosition <= numberOfEntries)
                makeRoom(newPosition);
            list[newPosition] = newEntry;
            numberOfEntries ++;
            ensureCapacity();
        }
        else
            throw new IndexOutOfBoundsException("Given position of add new entry is out of bounds.");
    }

    // Removes the entry at a given position from this list.
    public T remove(int givenPosition)
    {
        checkInitialization();
        if(givenPosition >= 1 && givenPosition <= numberOfEntries)
        {
            assert !isEmpty();
            T result = list[givenPosition];
            if(givenPosition <= numberOfEntries)
                removeGap(givenPosition);
            numberOfEntries --;
            return result;
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
    }

    //Removes all entries from this list.
    public void clear()
    {
        list = (T[]) new Object[DEFAULT_CAPACITY];
        numberOfEntries = 0;
    }

    // Replaces the entry at a given position in this list.
    public T replace(int givenPosition, T newEntry)
    {
        checkInitialization();
        if(givenPosition >= 1 && givenPosition <= numberOfEntries)
        {
            assert !isEmpty();
            T originalEntry = list[givenPosition];
            list[givenPosition] = newEntry;
            return originalEntry;
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
    }

    // Retrieves the entry at a given position in this list.
    public T getEntry(int givenPosition)
    {
        checkInitialization();
        if(givenPosition >= 1 && givenPosition <= numberOfEntries)
        {
            assert !isEmpty();
            return list[givenPosition];
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
    }

    // Sees whether this list contains a given entry.
    public boolean contains(T anEntry)
    {
        checkInitialization();
        boolean found = false;
        for(int i = 1; i <= numberOfEntries; i ++)
        {
            if(anEntry.equals(list[i]))
            {
                found = true;
                break;
            }
        }
        return found;
    }


    // Gets the length of this list.
    public int getLength()
    {
        return numberOfEntries;
    }


    // Sees whether this list is empty.
    public boolean isEmpty()
    {
        return numberOfEntries == 0;
    }

    // Retrieves all entries that are in this list in the order in which they occur in the list.
    public T[] toArray()
    {
        T[] res = Arrays.copyOfRange(list, 1, numberOfEntries + 1);
        return res;
    }
}
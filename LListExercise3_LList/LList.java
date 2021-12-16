package LListExercise3_LList;

import java.lang.Object;

public class LList<T> implements ListInterface<T>
{
    private Node firstNode; // Reference to first node of chain
    private int numberOfEntries;

    public LList()
    {
        initializeDataFields();
    }

    // Initializes the class's data fields to indicate an empty list.
    private void initializeDataFields()
    {
        firstNode = null;
        numberOfEntries = 0;
    } // end initializeDataFields

    // Returns a reference to the node at a given position.
    // Precondition: The chain is not empty;
    // 1 <= givenPosition <= numberOfEntries.
    private Node getNodeAt(int givenPosition)
    {
        assert !isEmpty() && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
        Node currentNode = firstNode;

        // Traverse the chain to locate the desired node
        // (skipped if givenPosition is 1)
        for (int counter = 1; counter < givenPosition; counter++)
            currentNode = currentNode.getNext();

        assert currentNode != null;

        return currentNode;
    } // end getNodeAt

    //Inner class Node.
    private class Node<T>
    {
        private T data;
        private Node next;

        private Node(T data, Node next)
        {
            this.data = data;
            this.next = next;
        }

        private Node(T data)
        {
            this(data, null);
        }

        private T getData()
        {
            return this.data;
        }

        private void setData(T data)
        {
            this.data = data;
        }

        private Node getNext()
        {
            return this.next;
        }

        private void setNext(Node next)
        {
            this.next = next;
        }
    }

    // Adds a new entry to the end of this list
    public void add(T newEntry)
    {
        Node newNode = new Node(newEntry);
        if(isEmpty())
            firstNode = newNode;
        else
        {
            Node lastNode = getNodeAt(numberOfEntries);
            lastNode.setNext(newNode);
        }
        numberOfEntries ++;
    }

    // Adds a new entry at a specified position within this list.
    public void add(int newPosition, T newEntry)
    {
        if(newPosition >= 1 && newPosition <= numberOfEntries + 1)
        {
            Node newNode = new Node(newEntry);
            if(newPosition == 1)
            {
                newNode.setNext(firstNode);
                firstNode = newNode;
            }
            else
            {
                Node before = getNodeAt(newPosition - 1);
                Node after = before.getNext();
                before.setNext(newNode);
                newNode.setNext(after);
            }
            numberOfEntries ++;
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to add operation.");
    }

    // Removes the entry at a given position from this list.
    public T remove(int givenPosition)
    {
        if(givenPosition >= 1 && givenPosition <= numberOfEntries)
        {
            assert !isEmpty();
            T result = null;
            if(givenPosition == 1)
            {
                result = (T) firstNode.getData();
                firstNode = firstNode.getNext();
            }
            else
            {
                Node before = getNodeAt(givenPosition - 1);
                result = (T) before.getNext().getData();
                before.setNext(before.getNext().getNext());
            }
            numberOfEntries --;
            return result;
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
    }

    //Removes all entries from this list.
    public void clear()
    {
        initializeDataFields();
    }

    // Replaces the entry at a given position in this list.
    public T replace(int givenPosition, T newEntry)
    {
        if(givenPosition >= 1 && givenPosition <= numberOfEntries)
        {
            assert !isEmpty();
            T originalData = (T) getNodeAt(givenPosition).getData();
            getNodeAt(givenPosition).setData(newEntry);
            return originalData;
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
    }

    // Retrieves the entry at a given position in this list.
    public T getEntry(int givenPosition)
    {
        if(givenPosition >= 1 && givenPosition <= numberOfEntries)
        {
            assert !isEmpty();
            return (T) getNodeAt(givenPosition).getData();
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
    }

    // Sees whether this list contains a given entry.
    public boolean contains(T anEntry)
    {
        boolean found = false;
        Node currenNode = firstNode;
        while(currenNode != null && !found)
        {
            if(currenNode.getData().equals(anEntry))
                found = true;
            else
                currenNode = currenNode.getNext();
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
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];
        int index = 0;
        Node currentNode = firstNode;
        while(index < numberOfEntries && currentNode != null)
        {
            result[index ++] = (T) currentNode.getData();
            currentNode = currentNode.getNext();
        }
        return result;
    }
}
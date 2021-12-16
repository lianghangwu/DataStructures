package BagExercise1_LinkedBag;

public class LinkedBag<T> implements BagInterface<T> {

    private Node firstNode; // Reference to first node of chain
    private int numberOfEntries;

    //default constructor
    public LinkedBag()
    {
        firstNode = null;
        numberOfEntries = 0;
    } // end default constructor

    // Implement the unimplemented methods

    public int getCurrentSize()
    {
        return numberOfEntries;
    }

    public boolean isEmpty()
    {
        return numberOfEntries == 0;
    }

    public boolean add(T newEntry)
    {
        Node newNode = new Node(newEntry);
        newNode.setNext(firstNode);

        firstNode = newNode;
        numberOfEntries ++;

        return true;
    }

    public T remove()
    {
        T result = null;
        if(numberOfEntries > 0)
        {
            result = (T) firstNode.getData();
            firstNode = firstNode.getNext();
            numberOfEntries --;
        }

        return result;
    }

    public boolean remove(T anEntry)
    {
        boolean result = false;
        Node node = getReferenceTo(anEntry);

        if(node != null)
        {
            node.setData(firstNode.getData());

            firstNode = firstNode.getNext();
            numberOfEntries --;
            result = true;
        }

        return result;
    }

    public void clear()
    {
        while(!isEmpty())
            remove();
    }

    public int getFrequencyOf(T anEntry)
    {
        int frequency = 0, loopCounter = 0;
        Node currentNode = firstNode;

        while(loopCounter < numberOfEntries && currentNode != null)
        {
            if(anEntry.equals(currentNode.getData()))
                frequency ++;

            loopCounter ++;
            currentNode = currentNode.getNext();
        }

        return frequency;
    }

    public boolean contains(T anEntry)
    {
        boolean found = false;
        Node currentNode = firstNode;

        while(!found && currentNode != null)
        {
            if(anEntry.equals(currentNode.getData()))
                found = true;
            else
                currentNode = currentNode.getNext();
        }

        return found;
    }

    public T[] toArray()
    {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];

        int index = 0;
        Node currentNode = firstNode;
        while(index < numberOfEntries && currentNode != null)
        {
            result[index] = (T) currentNode.getData();
            index ++;
            currentNode = currentNode.getNext();
        }

        return result;
    }

    // Locates a given entry within this bag.
    // Returns a reference to the node containing the entry, if located,
    // or null otherwise.
    private Node getReferenceTo(T anEntry)
    {
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && (currentNode != null))
        {
            if (anEntry.equals(currentNode.data))
                found = true;
            else
                currentNode = currentNode.next;
        } // end while

        return currentNode;
    } // end getReferenceTo


    private class Node<T>
    {
        private T data;
        private Node next;

        private Node(T data)
        {
            this(data, null);
        }

        private Node(T data, Node next)
        {
            this.data = data;
            this.next = next;
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
    }// end Node

}// end LinkedBag

package Dictionary1_linkedDictionary;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedDictionary<K, V> implements DictionaryInterface<K, V>
{
    private Node firstNode; // Reference to first node of chain
    private int numberOfEntries;

    public LinkedDictionary()
    {
        initializeDataFields();
    } // end default constructor

    private void initializeDataFields()
    {
        firstNode = null;
        numberOfEntries = 0;
    }


    public V add(K key, V value)
    {
        V result = null;

        Node currentNode = firstNode;
        while(currentNode != null && !key.equals(currentNode.getKey()))
            currentNode = currentNode.getNextNode();

        if(currentNode == null)
        {
            Node newNode = new Node(key, value);
            newNode.setNextNode(firstNode);
            firstNode = newNode;
            numberOfEntries ++;
        }
        else
        {
            result = currentNode.getValue();
            currentNode.setValue(value);
        }

        return result;
    }


    public V remove(K key)
    {
        V result = null;

        if(!this.isEmpty())
        {
            Node currentNode = firstNode;
            Node nodeBefore = null;

            while(currentNode != null && !key.equals(currentNode.getKey()))
            {
                nodeBefore = currentNode;
                currentNode = currentNode.getNextNode();
            }

            if(currentNode != null)
            {
                Node nodeAfter = currentNode.getNextNode();
                if(nodeBefore == null) firstNode = nodeAfter;
                else nodeBefore.setNextNode(nodeAfter);

                result = currentNode.getValue();
                numberOfEntries --;
            }

        }
        return result;
    }


    public V getValue(K key)
    {
        V result = null;

        Node currentNode = firstNode;
        while(currentNode != null && !key.equals(currentNode.getKey()))
            currentNode = currentNode.getNextNode();

        if(currentNode != null)
            result = currentNode.getValue();

        return result;
    }


    public boolean contains(K key)
    {
        return getValue(key) != null;
    }


    public Iterator<K> getKeyIterator()
    {
        return new KeyIterator();
    }


    public Iterator<V> getValueIterator()
    {
        return new ValueIterator();
    }


    public boolean isEmpty()
    {
        return numberOfEntries == 0;
    }


    public int getSize() {
        return numberOfEntries;
    }


    public void clear()
    {
        initializeDataFields();
    }


    // Same as in SortedLinkedDictionary.
// Since iterators implement Iterator, methods must be public.
    private class KeyIterator implements Iterator<K>
    {
        private Node nextNode;

        private KeyIterator()
        {
            nextNode = firstNode;
        } // end default constructor

        public boolean hasNext()
        {
            return nextNode != null;
        } // end hasNext

        public K next()
        {
            K result;

            if (hasNext())
            {
                result = nextNode.getKey();
                nextNode = nextNode.getNextNode();
            }
            else
            {
                throw new NoSuchElementException();
            } // end if

            return result;
        } // end next

        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove
    } // end KeyIterator

    private class ValueIterator implements Iterator<V>
    {
        private Node nextNode;

        private ValueIterator()
        {
            nextNode = firstNode;
        } // end default constructor

        public boolean hasNext()
        {
            return nextNode != null;
        } // end hasNext

        public V next()
        {
            V result;

            if (hasNext())
            {
                result = nextNode.getValue();
                nextNode = nextNode.getNextNode();
            }
            else
            {
                throw new NoSuchElementException();
            } // end if

            return result;
        } // end next

        public void remove()
        {
            throw new java.lang.UnsupportedOperationException();
        } // end remove
    } // end getValueIterator









    private class Node
    {
        private K key;
        private V value;
        private Node next;

        private Node(K searchKey, V dataValue)
        {
            key = searchKey;
            value = dataValue;
            next = null;
        } // end constructor

        private Node(K searchKey, V dataValue, Node nextNode)
        {
            key = searchKey;
            value = dataValue;
            next = nextNode;
        } // end constructor

        private K getKey()
        {
            return key;
        } // end getKey

        private V getValue()
        {
            return value;
        } // end getValue

        private void setValue(V newValue)
        {
            value = newValue;
        } // end setValue

        private Node getNextNode()
        {
            return next;
        } // end getNextNode

        private void setNextNode(Node nextNode)
        {
            next = nextNode;
        } // end setNextNode
    } // end Node

} // end LinkedDictionary

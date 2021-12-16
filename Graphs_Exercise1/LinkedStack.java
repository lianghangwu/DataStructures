package Graphs_Exercise1;

public final class LinkedStack<T> implements StackInterface<T>
{
    private Node topNode; // References the first node in the chain

    //default constructor
    public LinkedStack()
    {
        topNode = null;
    }

    // Implement the unimplemented methods
    public void push(T newEntry)
    {
        Node newNode = new Node(newEntry, topNode);
        topNode = newNode;
    }

    public T pop()
    {
        T top = peek(); //may throw EmptyStackException
        assert topNode != null;
        topNode = topNode.getNext();
        return top;
    }

    public T peek()
    {
        if(topNode == null) throw new EmptyStackException();

        return (T) topNode.getData();
    }

    public boolean isEmpty()
    {
        return topNode == null;
    }

    public void clear()
    {
        topNode = null;
    }

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

        private void setData(T data) {
            this.data = data;
        }

        private T getData() {
            return data;
        }

        private void setNext(Node next) {
            this.next = next;
        }

        private Node getNext() {
            return next;
        }
    }// end Node

}// end LinkedStack
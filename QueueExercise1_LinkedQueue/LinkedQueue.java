package QueueExercise1_LinkedQueue;

public final class LinkedQueue<T> implements QueueInterface<T>
{
    private Node head;
    private Node tail;

    public LinkedQueue()
    {
        this.head = null;
        this.tail = null;
    }

    public void enqueue(T newEntry)
    {
        Node newNode = new Node(newEntry, null);
        if(this.isEmpty())
            head = newNode;
        else
            tail.setNext(newNode);

        tail = newNode;
    }

    public T dequeue()
    {
        T front = getFront();
        assert head != null;
        head.setData(null);
        head = head.getNext();

        if(head == null)
            tail = null;

        return front;
    }

    public T getFront()
    {
        if(this.isEmpty())
            throw new EmptyQueueException();
        else
            return (T) this.head.getData();
    }

    public boolean isEmpty()
    {
        return this.head == null && this.tail == null;
    }

    public void clear()
    {
        this.head = null;
        this.tail = null;
    }

    class Node<T>
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

        private T getData() {
            return data;
        }

        private void setData(T data) {
            this.data = data;
        }

        private Node getNext() {
            return next;
        }

        private void setNext(Node next) {
            this.next = next;
        }

    } // end Node
} // end Linkedqueue
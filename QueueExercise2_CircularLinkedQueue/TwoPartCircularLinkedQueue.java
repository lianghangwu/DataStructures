package QueueExercise2_CircularLinkedQueue;

public final class TwoPartCircularLinkedQueue<T> implements QueueInterface<T>
{
    private Node queueNode; // References first node in queue
    private Node freeNode; // References node after back of queue

    public TwoPartCircularLinkedQueue()
    {
        freeNode = new Node(null, null);
        freeNode.setNext(freeNode);
        queueNode = freeNode;
    } // end default constructor

    public void enqueue(T newEntry)
    {
        freeNode.setData(newEntry);

        if(this.isChainFull())
        {
            Node newNode = new Node(null, queueNode);
            freeNode.setNext(newNode);
        }
        freeNode = freeNode.getNext();
    }

    public T dequeue()
    {
        T front = this.getFront();
        assert !isEmpty();
        queueNode.setData(null);
        queueNode = queueNode.getNext();

        return front;
    }

    public T getFront()
    {
        if(this.isEmpty())
            throw new EmptyQueueException();
        else
            return (T) queueNode.getData();
    }

    public boolean isEmpty()
    {
        return queueNode == freeNode;
    }

    public void clear()
    {
        while (!isEmpty())
            dequeue();
    } // end clear

    private boolean isChainFull()
    {
        return freeNode.getNext() == queueNode;
    }

    class Node<T>
    {
        private T data;
        private Node next;

        public Node(T data, Node next)
        {
            this.data = data;
            this.next = next;
        }

        public Node(T data)
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

    } // end Node
} // end TwoPartCircularLinkedQueue
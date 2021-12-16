package ListExercise1;

public class Node<T> {
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
}

package queue;

public class LinkedQueue extends AbstractQueue {
    private static class Node {
        Object value;
        Node next;
        private Node(final Object element) {
            this.value = element;
        }
    }
    private Node head;
    private Node tail;

    @Override
    public void clearAbstract() {
        head = null;
        tail = null;
    }

    @Override
    public Object element() {
        return head.value;
    }

    @Override
    public void enqueueAbstract(final Object value) {
        if (tail == null) {
            head = new Node(value);
            tail = head;
        } else {
            tail.next = new Node(value);
            tail = tail.next;
        }
    }

    public void dequeueAbstract() {
        head = head.next;
        if (head == null) {
            tail = null;
        }
    }


    public void toStrAbstract(StringBuilder sb) {
        Node elem = head;
        while (elem != null) {
            sb.append(elem.value);
            if (elem.next != null) {
                sb.append(", ");
            }
            elem = elem.next;
        }
    }

}
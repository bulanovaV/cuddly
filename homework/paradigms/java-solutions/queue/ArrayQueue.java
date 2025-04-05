package queue;

public class ArrayQueue extends AbstractQueue {

    // Model : a[1] .. a[n]
    // Inv : elem != null for elem in a && 0 <= head < capacity && 0 <= tail < capacity
    // Let immutable(ind) : a'[i] == a[i] for i = ind...n

    // Pred : element != null
    // Post : n' = n + 1 && a'[n] == element && immutable(0)
    //enqueue(element)

    // Pred : size > 0
    // Post : R == a[head] && immutable(0)
    //element()

    // Pred : size > 0
    // Post : R == a[head] && a'[head] == null && immutable(1) && head' == head + 1 && size' == size - 1
    //dequeue()

    // Pred : true
    // Post : R == size && immutable(0)
    //size()

    // Pred : true
    // Post : R == (size == 0) && immutable(0)
    //isEmpty()

    // Pred : true
    // Post : (elem == null for elem in a) && size' = 0
    //clear()


    private int capacity = 16;
    private Object[] queue = new Object[capacity];
    private int head;
    private int tail;


    // Pred : element != null
    // Post : n' = n + 1 && a'[n] == element && immutable(0)
    //enqueue(element)
    @Override
    public void enqueueAbstract(Object element) {
        expandAbstarct();
        queue[tail] = element;
        tail = (tail + 1) % capacity;
    }

    // Pred : size > 0
    // Post : R == a[head] && immutable(0)
    //element()
    @Override
    public Object element() {
        return queue[head];
    }


    // Pred : size > 0
    // Post : R == a[head] && a'[head] == null && immutable(1) && head' == head + 1 && size' == size - 1
    //dequeue()
    public void dequeueAbstract() {
        queue[head] = 0;
        head = (head + 1) % capacity;
    }

    // Pred : true
    // Post : (elem == null for elem in a) && size' = 0
    //clear()
    @Override
    public void clearAbstract() {
        head = 0;
        tail = 0;
        queue = new Object[capacity];
    }

    public void expandAbstarct() {
        if (size == capacity) {
            Object[] newQueue = new Object[capacity * 2];
            for (int i = 0; i < size; i++) {
                int ind = (head + i);
                newQueue[i] = queue[ind % capacity];
            }
            queue = newQueue;
            capacity *= 2;
            head = 0;
            tail = size;
        }
    }

    public Object get(final int index) {
        int pos = getPos(index);
        return queue[pos];
    }

    public void set(final int index, final Object value) {
        int pos = getPos(index);
        queue[pos] = value;
    }

    private int getPos(final int index) {
        int pos = ((tail - 1) - index + capacity);
        return pos % capacity;
    }

    @Override
    public void toStrAbstract(StringBuilder sb) {
        for (int i = 0; i < size; i++) {
            int ind = (head + i);
            sb.append(queue[ind % capacity]);
            if (i + 1 != size) {
                sb.append(", ");
            }
        }
    }
}
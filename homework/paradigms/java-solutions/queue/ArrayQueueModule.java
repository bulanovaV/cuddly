package queue;

public class ArrayQueueModule {

    // Model : a[1] .. a[n]
    // Inv : elem != null for elem in a
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

    private static int capacity = 16;
    private static Object[] queue = new Object[capacity];
    private static int head = 0;
    private static int tail = 0;
    private static int size = 0;


    // Pred : true
    // Post : (elem == null for elem in a) && size' = 0
    //clear()
    public static void clear() {
        queue = new Object[capacity];
        head = 0;
        tail = 0;
        size = 0;
    }

    // Pred : size > 0
    // Post : R == a[head] && immutable(0)
    //element()
    public static int size() {
        return size;
    }

    // Pred : true
    // Post : R == (size == 0) && immutable(0)
    //isEmpty()
    public static boolean isEmpty() {
        return size == 0;
    }

    // Pred : size > 0
    // Post : R == a[head] && immutable(0)
    //element()
    public static Object element() {
        return queue[head];
    }

    // Pred : size > 0
    // Post : R == a[head] && a'[head] == null && immutable(1) && head' == head + 1 && size' == size - 1
    //dequeue()
    public static Object dequeue() {
        size--;
        Object cur = element();
        head = (head + 1) % capacity;
        return cur;
    }

    // Pred : element != null
    // Post : n' = n + 1 && a'[n] == element && immutable(0)
    //enqueue(element)
    public static void enqueue(final Object element) {
        expand();
        size++;
        queue[tail] = element;
        tail = (tail + 1) % capacity;
    }

    private static void expand() {
        if (size == capacity) {
            Object[] newQueue = new Object[capacity * 2];
            for (int ind = 0; ind < size; ind++) {
                int pos = (head + ind);
                newQueue[ind] = queue[pos % capacity];
            }
            queue = newQueue;
            capacity *= 2;
            head = 0;
            tail = size;
        }
    }

    public static Object get(final int index) {
        int pos = getPos(index);
        return queue[pos];
    }

    public static void set(final int index, final Object value) {
        int pos = getPos(index);
        queue[pos] = value;
    }

    private static int getPos(final int index) {
        int pos = (tail - 1 - index + capacity);
        return pos % capacity;
    }

    public static String toStr() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int ind = (head + i);
            sb.append(queue[ind % capacity]);
            if (i + 1 != size) {
                sb.append(", ");
            }
        }
        return "[" + sb.toString() + "]";
    }
}
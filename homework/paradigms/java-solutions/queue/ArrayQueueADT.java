package queue;

public class ArrayQueueADT {

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

    private int capacity = 16;
    private Object[] arr = new Object[capacity];
    private int head;
    private int tail;
    private int size;

    // Pred : true
    // Post : (elem == null for elem in a) && size' = 0
    //clear()
    public static void clear(final ArrayQueueADT queue) {
        queue.arr = new Object[queue.capacity];
        queue.head = 0;
        queue.tail = 0;
        queue.size = 0;
    }

    // Pred : size > 0
    // Post : R == a[head] && immutable(0)
    //element()
    public static int size(final ArrayQueueADT queue) {
        return queue.size;
    }

    // Pred : true
    // Post : R == (size == 0) && immutable(0)
    //isEmpty()
    public static boolean isEmpty(final ArrayQueueADT queue) {
        return queue.size == 0;
    }

    // Pred : size > 0
    // Post : R == a[head] && immutable(0)
    //element()
    public static Object element(final ArrayQueueADT queue) {
        return queue.arr[queue.head];
    }

    // Pred : size > 0
    // Post : R == a[head] && a'[head] == null && immutable(1) && head' == head + 1 && size' == size - 1
    //dequeue()
    public static Object dequeue(final ArrayQueueADT queue) {
        queue.size--;
        Object element = queue.arr[queue.head];
        queue.arr[queue.head] = null;
        queue.head = (queue.head + 1) % queue.capacity;
        return element;
    }

    // Pred : element != null
    // Post : n' = n + 1 && a'[n] == element && immutable(0)
    //enqueue(element)
    public static void enqueue(final ArrayQueueADT queue, final Object element) {
        expand(queue);
        queue.size++;
        queue.arr[queue.tail] = element;
        queue.tail = (queue.tail + 1) % queue.capacity;
    }

    private static void expand(final ArrayQueueADT queue) {
        if (queue.size == queue.capacity) {
            Object[] newQueue = new Object[queue.capacity * 2];
            for (int i = 0; i < queue.size; i++) {
                int ind = (queue.head + i);
                newQueue[i] = queue.arr[ind % queue.capacity];
            }
            queue.arr = newQueue;
            queue.capacity *= 2;
            queue.head = 0;
            queue.tail = queue.size;
        }
    }

    public static Object get(final ArrayQueueADT queue, final int index) {
        int pos = queue.getPos(index);
        return queue.arr[pos];
    }

    public static void set(final ArrayQueueADT queue, final int index, final Object value) {
        int pos = queue.getPos(index);
        queue.arr[pos] = value;
    }

    private int getPos(int index) {
        int pos = (tail - 1 - index + capacity);
        return pos % capacity;
    }

    public static String toStr(final ArrayQueueADT queue) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < queue.size; i++) {
            int pos = (queue.head + i);
            sb.append(queue.arr[pos % queue.capacity]);
            if (i + 1 != queue.size) {
                sb.append(", ");
            }
        }
        return "[" + sb.toString() + "]";
    }

}
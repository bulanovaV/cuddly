package queue;

public interface Queue {

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


    void clear();

    int size();

    boolean isEmpty();

    Object element();

    Object dequeue();

    void enqueue(final Object element);

    String toStr();
}

package queue;

public abstract class AbstractQueue implements Queue {
    protected int size;

    public abstract void enqueueAbstract(final Object element);

    public abstract void dequeueAbstract();

    @Override
    public abstract Object element();

    public abstract void clearAbstract();

    public abstract void toStrAbstract(StringBuilder sb);

    @Override
    public String toStr(){
        StringBuilder sb = new StringBuilder();
        toStrAbstract(sb);
        return "[" + sb + "]";
    }


    @Override
    public void enqueue(final Object element) {
        enqueueAbstract(element);
        size++;
    }

    @Override
    public Object dequeue() {
        size--;
        Object value = element();
        dequeueAbstract();
        return value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        clearAbstract();
    }


}
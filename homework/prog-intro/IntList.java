import java.util.Arrays;

public class IntList {
    private int[] list;
    private int size;
    private int cntStr = 0;

    public IntList() {
        list = new int[10];
        size = 0;
    }

    private void resize() {
        list = Arrays.copyOf(list, list.length * 2);
    }

    public void add(int value) {
        if (value == 0) {
            cntStr++;
        }
        if (size >= list.length) {
            resize();
        }
        list[size] = value;
        size++;
    }

    public int get(int ind) {
        return list[ind];
    }

    public int cntStr() {
        return cntStr;
    }

    public int size() {
        return size;
    }
}
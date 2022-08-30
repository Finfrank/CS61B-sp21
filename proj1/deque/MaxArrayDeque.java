package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{

    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    public T max() {
        return max(comparator);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) return null;

        T maxv = get(0);
        for (int i = 1; i < size(); i++) {
            if ((c.compare(get(i), maxv)) > 0)
                maxv = get(i);
        }
        return maxv;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof MaxArrayDeque)) return false;
        MaxArrayDeque<T> m = (MaxArrayDeque<T>) o;
        if (m.max() != max()) return false;
        return super.equals(o);
    }

}

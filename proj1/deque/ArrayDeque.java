package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    public void addFirst(T item) {
        items[nextFirst] = item;
        size ++;
        nextFirst --;
        if (nextFirst == -1) resize(size * 2);
    }

    public void addLast(T item) {
        items[nextLast] = item;
        size ++;
        nextLast ++;
        if (nextLast == items.length) resize(size * 2);
    }

    public void resize(int capacity) {
        T[] n = (T[]) new Object[capacity];
        int newFirst = Math.abs(capacity - size) / 2;
        System.arraycopy(items, nextFirst + 1, n, newFirst, size);
        nextFirst = newFirst - 1;
        nextLast = newFirst + size;
        items = n;
    }

    private void shrinkSize() {
        if (isEmpty()) {
            resize(8);
        } else if (items.length / 4 > size && size >= 4) {
            resize(size * 2);
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst += 1;
        T item = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        shrinkSize();
        return item;
    }

    public T removeLast() {
        if (isEmpty()) return null;
        nextLast --;
        T item = items[nextLast];
        items[nextLast] = null;
        size --;
        shrinkSize();
        return item;
    }
    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++)
            System.out.println(get(i) + " ");
        System.out.println();
    }

    public T get(int index) {
        if (index < 0 || index > size -1) return null;
        return items[nextFirst + 1 + index];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int index;

        ArrayDequeIterator() {
            index = 0;
        }

        public boolean hasNext() {
            return index < size;
        }

        public T next() {
            T item = (T)get(index);
            index ++;
            return item;
        }
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (o == null || !(o instanceof Deque)) return false;

        Deque<T> t = (Deque<T>) o;
        if (t.size() != size) return false;
        for (int i = 0; i < size; i++) {
            if (t.get(i) != get(i)) return false;
        }

        return true;

    }












}

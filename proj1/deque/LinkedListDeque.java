package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node<T> {
        private Node<T> prev;
        private Node<T> next;
        private T item;

        Node (Node<T> p,Node<T> n,T i) {
            this.prev = p;
            this.next = n;
            this.item = i;
        }
    }
    private int size;
    private Node<T> sentinel = new Node<>(null, null, null);

    public LinkedListDeque() {
        size = 0;
        sentinel.next = sentinel.prev = sentinel;
    }

    public void addFirst(T item) {
        Node<T> temp = new Node<>(sentinel, sentinel.next, item);
        sentinel.next.prev = temp;
        sentinel.next = temp;
        size ++;
    }

    public void addLast(T item) {
        Node<T> temp = new Node<>(sentinel.prev, sentinel, item);
        sentinel.prev.next = temp;
        sentinel.prev = temp;
        size++;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node<T> t = sentinel.next;
        while (t != sentinel) {
            System.out.println( t.item + " ");
            t = t.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) return null;
        Node<T> temp = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return temp.item;
    }

    public T removeLast() {
        if (size == 0) return null;
        Node<T> temp = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return temp.item;
    }

    public T get(int index) {
        if (index < 0 && index > size -1) return null;
        Node<T> t = sentinel.next;
        while (t != sentinel && index > 0) {
            t = t.next;
            index --;
        }
        return t.item;
    }

    public T getRecursive(int index) {
        if (index < 0 || index > size - 1) return null;
        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, Node<T> t) {
        if (index == 0)  return t.item;
        return getRecursiveHelper(index - 1, t.next);
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node<T> t;

        LinkedListDequeIterator() {
            t = sentinel.next;
        }
        public boolean hasNext() {
            return (t != sentinel);
        }
        public T next() {
            T item = t.item;
            t = t.next;
            return item;
        }
    }

    public boolean equals(Object o) {
        Deque t = (Deque) o;
        if(size != t.size()) return false;
        if(!(o instanceof Deque)) return false;
        Node<T> p = sentinel.next;

        for (int i = 0; i < size; i++) {
            if(!p.item.equals(t.get(i))) return false;
        }
        return true;
    }
}

package collection.iterable;

import java.util.Iterator;

public class MyArrayIterator<E> implements Iterator<E> {

    private int currentIndex = -1;
    private Object[] targetArr;

    public MyArrayIterator(Object[] targetArr) {
        this.targetArr = targetArr;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < targetArr.length - 1;
    }

    @Override
    public E next() {
        return (E)targetArr[++currentIndex];
    }
}

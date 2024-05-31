package collection.iterable;

import java.util.Iterator;

public class MyArray<E> implements Iterable<E>{

    private Object[] numbers;

    public MyArray(Object[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayIterator<E>(numbers);
    }

}

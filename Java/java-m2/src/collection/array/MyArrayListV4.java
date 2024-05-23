package collection.array;

import java.util.Arrays;

public class MyArrayListV4<E> {

    private static final int DEFAULT_CAPACITY = 20;

    private Object[] elementData;
    private int size;

    public MyArrayListV4() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayListV4(int initialCapacity) {
        elementData = new Object[initialCapacity];
    }

    public int size(){
        return size;
    }

    public void add(E e){
        //코드 추가
        if(size == elementData.length){
            grow();
        }
        elementData[size++] = e;
    }

    public E add(int index, E e){
        E oldValue = get(index);
        if(size == elementData.length){
            grow();
        }
        shiftRight(index);
        elementData[index] = e;
        size++;
        return oldValue;
    }

    private void shiftRight(int index) {
        for(int i=size; i>index; i--){
            elementData[i] = elementData[i-1];
        }
    }

    public E remove(int index){
        E oldValue = get(index);
        shiftLeft(index);
        size--;
        return oldValue;
    }

    private void shiftLeft(int index) {
        for(int i=index; i<size; i++){
            elementData[i] = elementData[i+1];
        }
    }

    //코드 추가
    private void grow() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity * 2;
        //배열을 새로 만들고, 기존 배열을 새로운 배열에 복사

        Object[] newArr = Arrays.copyOf(elementData, newCapacity);
        elementData = newArr;
    }

    public E get(int index){
        return (E)elementData[index];
    }

    public E set(int index,E element){
        E oldValue = get(index);
        elementData[index] = element;
        return oldValue;
    }

    public int indexOf(E o){
        for(int i=0; i<size; i++){
            if(o.equals(elementData[i])){
                return i;
            }
        }
        return -1;
    }

    public String toString(){
        //Object[] objects = Arrays.copyOf(elementData, size);
        //return Arrays.toString(objects) + " size=" + size  + ", capacity=" + elementData.length;
        return Arrays.toString(elementData);
    }
}

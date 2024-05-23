package collection.array;

import java.util.Arrays;

public class MyArrayListV1 {

    private static final int DEFAULT_CAPACITY = 5;

    private Object[] elementData;
    private int size;

    public MyArrayListV1() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayListV1(int initialCapacity) {
        elementData = new Object[initialCapacity];
    }

    public int size(){
        return size;
    }

    public void add(Object e){
        elementData[size++] = e;
    }

    public Object get(int index){
        return elementData[index];
    }

    public Object set(int index,Object element){
        Object oldValue = get(index);
        elementData[index] = element;
        return oldValue;
    }

    public int indexOf(Object o){
        for(int i=0; i<size; i++){
            if(o.equals(elementData[i])){
                return i;
            }
        }
        return -1;
    }

    public String toString(){
        Object[] objects = Arrays.copyOf(elementData, size);
        return Arrays.toString(objects) + " size=" + size  + ", capacity=" + elementData.length;
    }
}

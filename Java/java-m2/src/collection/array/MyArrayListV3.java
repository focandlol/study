package collection.array;

import java.util.Arrays;

public class MyArrayListV3 {

    private static final int DEFAULT_CAPACITY = 20;

    private Object[] elementData;
    private int size;

    public MyArrayListV3() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayListV3(int initialCapacity) {
        elementData = new Object[initialCapacity];
    }

    public int size(){
        return size;
    }

    public void add(Object e){
        //코드 추가
        if(size == elementData.length){
            grow();
        }
        elementData[size++] = e;
    }

    public void add(int index, Object e){
        if(size == elementData.length){
            grow();
        }
        shiftRight(index);
        elementData[index] = e;
        size++;
    }

    private void shiftRight(int index) {
        for(int i=size; i>index; i--){
            elementData[i] = elementData[i-1];
        }
    }

    public void remove(int index){
        shiftLeft(index);
        size--;
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
        //Object[] objects = Arrays.copyOf(elementData, size);
        //return Arrays.toString(objects) + " size=" + size  + ", capacity=" + elementData.length;
        return Arrays.toString(elementData);
    }
}

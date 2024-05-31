package collection.iterable;

import java.util.Iterator;

public class MyArrayMain {

    public static void main(String[] args) {

        MyArray myArray = new MyArray(new int[]{1, 2, 3, 4});
        Iterator<Integer> iterator = myArray.iterator();
        System.out.println("iterator 사용");
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        //향상된 for문 사용하기 위해서는
        //iterator, iterable 구현 필요
        System.out.println("for-each 사용");
        for (Integer i : myArray) {
            System.out.println("i = " + i);
        }

    }
}

package collection.iterable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class JavaIterableMain {

    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        JavaIterableMain.<Integer>printAll(list.iterator());

        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        printAll(set.iterator());


    }

    private static <E> void printAll(Iterator<E> iterator){
        System.out.println("iterator = " + iterator.getClass());
        while(iterator.hasNext()){
            E next = iterator.next();
            System.out.println("next = " + next);
        }
    }
}

package generic.test.ex4;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class UniqueNamesTest2 {

    public static void main(String[] args) {

        Integer[] inputArr = {30,20,20,10,10};

        Set<Integer> set = new LinkedHashSet<>();;
        for(Integer i : inputArr) {
            set.add(i);
        }

        Iterator<Integer> iterator = set.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

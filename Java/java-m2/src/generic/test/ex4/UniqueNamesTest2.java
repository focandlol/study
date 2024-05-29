package generic.test.ex4;

import java.util.*;

public class UniqueNamesTest2 {

    public static void main(String[] args) {

        Integer[] inputArr = {30,20,20,10,10};
        List<Integer> inputArr1 = List.of(inputArr);

        Set<Integer> set = new LinkedHashSet<>(inputArr1);

        /*for(Integer i : inputArr) {
            set.add(i);
        }*/

        Iterator<Integer> iterator = set.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

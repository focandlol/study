package collection.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsSortMain {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Integer max = Collections.max(list);
        Integer min = Collections.min(list);

        System.out.println("max = " + max);
        System.out.println("min = " + min);

        System.out.println("list = " + list);

        Collections.shuffle(list); // 랜덤으로 섞임
        System.out.println("list = " + list);

        Collections.sort(list); //정렬 list.sort()쓰는게 좋음
        System.out.println("list = " + list);

        Collections.reverse(list); // 정렬 기준의 반대로 정렬
        System.out.println("list = " + list);
    }
}

package dataType;

// Java 프로그래밍 - 변수와 자료형_4

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class DataType4 {
    public static void main(String[] args) {

//      1. 자료형 - 리스트
        System.out.println("== 리스트 ==");
        ArrayList list = new ArrayList();

//      1-1. add
        list.add(1);
        list.add("hello");
        list.add(2);
        list.add(3);
        list.add(4);
        list.add("world!");
        System.out.println("list = " + list);

        list.add(0,1);
        System.out.println("list = " + list);

//      1-2. get
        System.out.println(list.get(0));
        System.out.println(list.get(3));

//      1-3. size
        System.out.println(list.size());

//      1-4. remove
        System.out.println(list.remove(0));
        System.out.println("list = " + list);

        System.out.println(list.remove(Integer.valueOf(2)));
        System.out.println("list = " + list);

//      1-5. clear
        list.clear();
        System.out.println("list = " + list);

//      1-6. sort
        ArrayList list2 = new ArrayList();
        list2.add(5);
        list2.add(3);
        list2.add(4);
        System.out.println("list2 = " + list2);

        list2.sort(Comparator.naturalOrder());
        System.out.println("list2 = " + list2);
        list2.sort(Comparator.reverseOrder());
        System.out.println("list2 = " + list2);


//      1-7. contains
        System.out.println(list2.contains(1));
        System.out.println(list2.contains(3));

//      2. Maps
        System.out.println("== Maps ==");
        HashMap map = new HashMap();

//      2-1. put
        map.put("kiwi",9000);
        map.put("apple",10000);
        map.put("mango",12000);
        System.out.println("map = " + map);

//      2-2. get
        System.out.println(map.get("mandarine"));
        System.out.println(map.get("kiwi"));

//      2-3. size
        System.out.println(map.size());

//      2-4. remove
        System.out.println(map.remove("kiwi"));
        System.out.println(map.remove("mandarine"));
        System.out.println("map = " + map);


//      2-5. containsKey
        System.out.println(map.containsKey("apple"));
        System.out.println(map.containsKey("kiwi"));


//      3. Generics
        System.out.println("== Generics ==");
        ArrayList l3 = new ArrayList();
        l3.add(1);
        l3.add("hello");
        System.out.println("l3 = " + l3);

        ArrayList<String> l4 = new ArrayList<>();
//        l4.add(1);
        l4.add("hello");
        System.out.println("l4 = " + l4);

        HashMap map1 = new HashMap();
        map1.put(123,"id");
        map1.put("apple",10000);
        System.out.println("map1 = " + map1);

        HashMap<String,Integer> map2 = new HashMap();
//       map2.put(123,"id");
        map2.put("apple",10000);
        System.out.println("map2 = " + map2);

    }

}


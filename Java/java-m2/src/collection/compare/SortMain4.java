package collection.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SortMain4 {

    public static void main(String[] args) {
        MyUser myUser1 = new MyUser("고경민", 30);
        MyUser myUser2 = new MyUser("이어어", 20);
        MyUser myUser3 = new MyUser("박미미", 10);

        List<MyUser> list = new LinkedList<>();
        list.add(myUser1);
        list.add(myUser2);
        list.add(myUser3);
        System.out.println("기본 데이터");
        System.out.println(list);

        System.out.println("Comparable 기본 정렬");
        list.sort(null); //기본 정렬 방법1
        //Collections.sort(list);//기본 정렬 방법2
        System.out.println(list);

        System.out.println("IdComparator 정렬");
        list.sort(new IdComparator());//comparator사용 방법 1
        //Collections.sort(list,new IdComparator()); //comparator사용 방법2
        System.out.println(list);
    }




}

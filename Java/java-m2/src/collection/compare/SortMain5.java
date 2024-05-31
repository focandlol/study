package collection.compare;

import java.util.TreeSet;

public class SortMain5 {

    public static void main(String[] args) {
        MyUser myUser1 = new MyUser("고경민", 30);
        MyUser myUser2 = new MyUser("이어어", 20);
        MyUser myUser3 = new MyUser("박미미", 10);

        TreeSet<MyUser> treeSet1 = new TreeSet<>(); //treeSet은 데이터를 넣을 때 부터 정렬되어야 함
        treeSet1.add(myUser1);
        treeSet1.add(myUser2);
        treeSet1.add(myUser3);
        System.out.println("Comparable 기본 정렬");
        System.out.println(treeSet1);

        TreeSet<MyUser> treeSet2 = new TreeSet<>(new IdComparator());//comparator로 정렬 원할 시 생성자에 넘기면 됨
        treeSet2.add(myUser1);
        treeSet2.add(myUser2);
        treeSet2.add(myUser3);
        System.out.println("IdComparator 정렬");
        System.out.println(treeSet2);
    }
}

package collection.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImmutableMain {

    public static void main(String[] args) {
        //불변 리스트 생성
        List<Integer> list = List.of(1, 2, 3);

        //가변 리스트로 변경
        ArrayList<Integer> mutableList = new ArrayList<>(list);
        mutableList.add(4);
        System.out.println("mutableList = " + mutableList);
        System.out.println("mutableList.getClass() = " + mutableList.getClass());

        //다시 불변 리스트로 변경
        List<Integer> unModifiableList = Collections.unmodifiableList(mutableList);
        System.out.println("unModifiableList.getClass() = " + unModifiableList.getClass());

    }
}

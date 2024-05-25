package generic.test.ex3;

import java.util.ArrayList;
import java.util.List;

public class ListEx1 {

    public static void main(String[] args) {
        List<Integer> studentList = new ArrayList<Integer>();
        studentList.add(90);
        studentList.add(80);
        studentList.add(70);
        studentList.add(60);
        studentList.add(50);

        int total = 0;
        for (int i = 0; i < studentList.size(); i++) {
            total += studentList.get(i);
        }
        double average = (double) total / studentList.size();
        System.out.println("점수 총합: " + total);
        System.out.println("점수 평균: " + average);
    }
}

package basicMath.practice1;

import java.util.ArrayList;
import java.util.Arrays;

public class Practice1_2 {
    public static void main(String[] args) {
        System.out.println(pascal(1));
        System.out.println(pascal(2));
        System.out.println(pascal(3));
        System.out.println(pascal(4));
        System.out.println(pascal(5));
    }

    private static String pascal(int n) {
        ArrayList<Integer>[] list = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();

            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || j == i) {
                    list[i].add(1);
                } else {
                    list[i].add(list[i - 1].get(j - 1) + list[i - 1].get(j));
                }
            }
        }
        return Arrays.toString(list);
    }
}

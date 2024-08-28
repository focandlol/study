package basicMath.practice1;

import java.util.HashSet;

public class Practice4 {
    public static void main(String[] args) {
        System.out.println(find(19));
        System.out.println(find(2));
        System.out.println(find(61));
    }

    private static boolean find(int i) {
        HashSet<Integer> set = new HashSet<>();

        while(set.add(i)) {
            String s = String.valueOf(i);
            int sum = 0;
            for (int j = 0; j < s.length(); j++) {
                sum += s.charAt(j) - '0';
            }

            if (sum == 1) {
                return true;
            } else {
                i = sum;
            }
        }
        return false;
    }
}

package pre.hint;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Programmers120844 {
    static class Solution {
        static int[] solution(int[] numbers, String direction) {
            List<Integer> list = Arrays.stream(numbers).boxed().collect(Collectors.toList());
            if(direction.equals("right")) list.add(0,list.remove(list.size()-1));
            else list.add(list.remove(0));
            return list.stream().mapToInt(i -> i).toArray();
        }
    }
    public static void main(String[] args) {
        int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        String direction = "right";
        System.out.println(Arrays.toString(Solution.solution(numbers, direction)));
    }
}

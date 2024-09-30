package pre.hint;

import java.util.*;
import java.util.stream.*;

public class Programmers120896 {
    static class Solution {
       static String solution(String s) {
            String a = "";
            Map<Character, Long> collect = IntStream.range(0, s.length()).mapToObj(i -> s.charAt(i))
                    .collect(Collectors.groupingBy(q -> q, Collectors.counting()));

            a = collect.keySet().stream().filter(t -> collect.get(t) == 1).sorted().map(b -> String.valueOf(b)).collect(Collectors.joining());
            return a;
        }
    }
    public static void main(String[] args) {
       String a = "asdas";
       System.out.println(Solution.solution(a));

    }
}

package array;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class a {
    public static void main(String[] args) {
        String s = "Naver";
        Map<String, Long> map = IntStream.range(0,s.length()).mapToObj(i -> String.valueOf(s.charAt(i)))
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        String c = map.keySet().stream().filter(e -> map.get(e) == Collections.max(map.values()))
                .sorted().findFirst().get();

        //Set<Map.Entry<Character, Long>> entries = map.entrySet();
//        Optional<Character> first = entries.stream().filter(e -> e.getValue() == Collections.max(map.values()))
//                .map(i -> i.getKey()).sorted().findFirst();

    }


}

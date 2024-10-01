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

        int[] arr = new int[2];
        double v = IntStream.range(1, arr.length - 1).map(i -> arr[i]).average().orElse(0);
        int v1 = (int) v;

        String[] sp = new String[12];
        Arrays.stream(sp).collect(Collectors.toSet()).size();

        int[] arr1 = new int[12];
        int[] arr2 = new int[12];
        Set<Integer> commonSet = Arrays.stream(arr1).boxed().filter(n -> Arrays.binarySearch(arr2,n) > -1)
                .collect(Collectors.toSet());

    }


}

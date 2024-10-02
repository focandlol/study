package array;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class a {
    public static void main(String[] args) {
//        String s = "Naver";
//        Map<String, Long> map = IntStream.range(0,s.length()).mapToObj(i -> String.valueOf(s.charAt(i)))
//                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
//
//        String c = map.keySet().stream().filter(e -> map.get(e) == Collections.max(map.values()))
//                .sorted().findFirst().get();
//
//        //Set<Map.Entry<Character, Long>> entries = map.entrySet();
////        Optional<Character> first = entries.stream().filter(e -> e.getValue() == Collections.max(map.values()))
////                .map(i -> i.getKey()).sorted().findFirst();
//
//        int[] arr = new int[2];
//        double v = IntStream.range(1, arr.length - 1).map(i -> arr[i]).average().orElse(0);
//        int v1 = (int) v;
//
//        String[] sp = new String[12];
//        Arrays.stream(sp).collect(Collectors.toSet()).size();
//
//        int[] arr1 = new int[12];
//        int[] arr2 = new int[12];
//        Set<Integer> commonSet = Arrays.stream(arr1).boxed().filter(n -> Arrays.binarySearch(arr2,n) > -1)
//                .collect(Collectors.toSet());
//
//        int[] as = new int[123];
//        int fee = 5;
//        Arrays.stream(as).map(a -> as[a]*5).sum();
//        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
//        int[] array = list.stream().mapToInt(i -> i).toArray();
//
//        Map<Integer, Long> collect = Arrays.stream(as).boxed().collect(Collectors.groupingBy(a -> a, Collectors.counting()));
//        Integer i1 = collect.keySet().stream().filter(i -> collect.get(i) == 1).findAny().orElse(0);

        int n = -120;
        StringBuilder sb = new StringBuilder(String.valueOf(n));
        System.out.println(sb.length());
        for(int i=0; i<sb.length(); i++) {
            System.out.println(sb.charAt(i));
        }
        sb.reverse();
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());
        //System.out.println(sb.length());
        //int i = Integer.parseInt(sb.deleteCharAt(sb.length() - 1).reverse().toString()) * -1;
       // System.out.println(i);
        // Integer.parseInt(sb.toString());
        //System.out.println(sb);
    }


}

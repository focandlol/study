package sort;

import java.util.ArrayList;
import java.util.HashMap;

public class Practice2 {
    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        System.out.println(solution(strs));

        strs = new String[]{"abc","bac","bca","xyz","zyx","aaa"};
        System.out.println(solution(strs));

        strs = new String[]{"ccc","aaa","bbb"};
        System.out.println(solution(strs));

        strs = new String[]{"bbb","aaa","ccc"};
        System.out.println(solution(strs));

        strs = new String[]{"cba","abc","bca"};
        System.out.println(solution(strs));

        strs = new String[]{"b","c","a"};
        System.out.println(solution(strs));

        strs = new String[]{"c","d","b"};
        System.out.println(solution(strs));

        strs = new String[]{"czzza","azzza","bzzza"};
        System.out.println(solution(strs));

        strs = new String[]{"czzz","azzz","bzzz"};
        System.out.println(solution(strs));
    }

    private static ArrayList<ArrayList<String>> solution(String[] strs) {
        if(strs == null || strs.length == 0) return new ArrayList<>();

        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for(int i = 0; i < strs.length; i++) {
            char[] charArray = strs[i].toCharArray();
            sort(charArray);
            String key = String.valueOf(charArray);
            System.out.println(key);
            if(!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(strs[i]);
        }

//        for (String s : map.keySet()) {
//            System.out.println(s);
//        }

        return new ArrayList<>(map.values());
    }

    private static void sort(char[] charArray) {
        quick(charArray,0,charArray.length-1);
    }

    private static void quick(char[] charArray, int start, int end) {
        if(start >= end) return;

        int pivot = partition(charArray,start,end);

        quick(charArray,start,pivot-1);
        quick(charArray,pivot,end);
    }

    private static int partition(char[] charArray, int start, int end) {
        int pivot = charArray[(start + end) / 2];

        while(start <= end){
            while(charArray[start] < pivot){
                start++;
            }

            while(charArray[end] > pivot){
                end--;
            }

            if(start <= end){
                swap(charArray,start++,end--);
            }
        }
        return start;
    }

    private static void swap(char[] charArray, int start, int end) {
        char temp = charArray[end];
        charArray[end] = charArray[start];
        charArray[start] = temp;
    }
}

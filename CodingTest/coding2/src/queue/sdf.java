package queue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class sdf {
    public static void main(String[] args) {
        String s = "174771177";
        System.out.println(solution(s));
    }
    public static int solution(String s) {
        int[] arr = new int[10];
        int max = 0;
        for(int i=0; i<s.length(); i++){
            arr[s.charAt(i) - '0']++;
            max = Math.max(max,arr[s.charAt(i) - '0']);
        }
        System.out.println(max);

        System.out.println(Arrays.toString(arr));
        return Arrays.stream(arr).boxed().collect(Collectors.toList()).indexOf(max);


    }
}

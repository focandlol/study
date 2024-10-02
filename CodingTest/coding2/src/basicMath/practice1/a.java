package basicMath.practice1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class a {
    public static void main(String[] args) {
        int[] arr = new int[5];
        Set<Integer> collect = Arrays.stream(arr).boxed().collect(Collectors.toSet());
        IntStream.range(1, arr.length + 1).filter(i -> !collect.contains(i)).toArray();
    }
}

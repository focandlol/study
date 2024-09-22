package heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ss {
    public static void main(String[] args) {
        int[][] arr = {{1,2},{3,4}};
        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
    }
}

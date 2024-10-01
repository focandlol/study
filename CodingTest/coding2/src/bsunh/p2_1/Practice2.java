package bsunh.p2_1;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Practice2 {
    public static void main(String[] args) {
        int[][] intervals = {{1,4},{2,4},{3,6},{4,4}};
        int[] queries = {2,3,4,5};
        System.out.println(Arrays.toString(solution(intervals,queries)));

        intervals = new int[][]{{2,3},{2,5},{1,8},{20,25}};
        queries = new int[]{2,19,5,22};
        System.out.println(Arrays.toString(solution(intervals,queries)));
    }

    private static int[] solution(int[][] intervals, int[] queries) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((x,y) -> (x[1] - x[0]) - (y[1]-y[0]));
        int[] arr = new int[queries.length];
        Arrays.sort(intervals,(x,y) -> x[0] - y[0]);

        for(int i=0; i<queries.length; i++){
            boolean flag = false;
            for(int j=0; j< intervals.length; j++){
                if(intervals[j][0] <= queries[i] && intervals[j][1] >= queries[i]){
                    flag = true;
                    queue.add(new int[]{intervals[j][0], intervals[j][1]});
                }else if(intervals[j][0] > queries[i]){
                    break;
                }
            }
            if(flag){
                arr[i] = queue.peek()[1] - queue.peek()[0] + 1;
            }else{
                arr[i] = -1;
            }
            queue.clear();
        }
        return arr;
    }
}

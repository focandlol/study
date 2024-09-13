package practice.p3_1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Practice3 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        solution(arr,4,3);

        arr = new int[]{1,2,3,4,5,6,7,8,9,10};
        solution(arr,5,5);

        arr = new int[]{2,4};
        solution(arr,1,3);

        arr = new int[]{2,4};
        solution(arr,3,3);
    }

    private static void solution(int[] arr, int k, int x) {
        PriorityQueue<Integer> pq = new PriorityQueue((Comparator<Integer>) (o1, o2) -> {
            int a = Math.abs(x-o1);
            int b = Math.abs(x-o2);

            if(a != b){
                return a - b;
            }
            return o1 - o2;
        });

        for (int i : arr) {
            pq.add(i);
        }

        List<Integer> list = new ArrayList<>();

        for(int i=0; i<k; i++){
            if(!pq.isEmpty()) {
                list.add(pq.poll());
            }else{
                break;
            }
        }
        list.sort(Integer::compareTo);
        for (Integer i : list) {
            System.out.print(i + " ");
        }
        System.out.println();

    }
}

package bsunh.p2_1;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Practice3 {
    public static void main(String[] args) {
        int[] target = {9,3,5};
        System.out.println(solution(target));

        target = new int[]{8,5};
        System.out.println(solution(target));

        target = new int[]{1,1,1,2};
        System.out.println(solution(target));
    }

    private static boolean solution(int[] target) {
        int dap = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a,b) -> b-a);
        for (int i = 0; i < target.length; i++) {
            pq.add(target[i]);
            dap += target[i];
        }

        while(dap != 3 && pq.peek() != 1){
            int poll = pq.poll();
            int ch = poll - (dap - poll);
            if(ch > 0) {
                dap = poll;
                pq.add(ch);
            }else{
                return false;
            }
        }
        return true;

    }
}

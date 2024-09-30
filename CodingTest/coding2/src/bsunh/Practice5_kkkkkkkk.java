package bsunh;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Practice5_kkkkkkkk {
    public static void main(String[] args) {
        int[] forbidden = {14,4,18,1,15};
        System.out.println(solution(forbidden,3,15,9));

        forbidden = new int[]{8,3,16,6,12,20};
        System.out.println(solution(forbidden,15,13,11));

        forbidden = new int[]{1,6,2,14,5,17,4};
        System.out.println(solution(forbidden,16,9,7));
    }

    private static int solution(int[] forbidden, int a, int b, int x) {
        int limit = x+2*a+2*b;
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<forbidden.length; i++) {
            set.add(forbidden[i]);
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0,0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if(cur[0] >= limit) {
                break;
            }
            if(cur[0] == x){
                return cur[1];
            }
            if(!set.contains(cur[0]+a)) {
                queue.add(new int[]{cur[0] + a, cur[1] + 1, 0});
            }
            if(cur[0] - b >= 0 && cur[2] != 1 && !set.contains(cur[0] - b)){
                queue.add(new int[]{cur[0]-b,cur[1]+1,1});
            }
        }
        return -1;
    }
}

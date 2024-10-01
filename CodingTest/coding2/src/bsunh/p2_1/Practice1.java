package bsunh.p2_1;

import javax.print.attribute.IntegerSyntax;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Practice1 {
    public static void main(String[] args) {
        int[][] times = {{1,4},{2,3},{4,6}};
        System.out.println(solution(times,1));
        times = new int[][]{{3,10},{1,5},{2,6}};
        System.out.println(solution(times,0));
    }

    private static int solution(int[][] times, int targetFriend) {
        int targetPos = times[targetFriend][0];
        PriorityQueue<Integer> chair = new PriorityQueue<>();
        Arrays.sort(times,(x,y) -> x[0] - y[0]);
        PriorityQueue<int[]> friends = new PriorityQueue<>((x,y) -> x[0] - y[0]);


        for(int i=0; i<times.length; i++) {
            chair.add(i);
        }

        for(int i=0; i<times.length; i++){
            while(!friends.isEmpty() && friends.peek()[0] <= times[i][0]){
                chair.add(friends.poll()[1]);
            }

            if(targetPos == times[i][0]){
                break;
            }

            friends.add(new int[]{times[i][1],chair.poll()});
        }
        return chair.peek();
    }
}

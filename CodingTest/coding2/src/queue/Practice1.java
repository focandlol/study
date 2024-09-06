package queue;

import java.util.LinkedList;
import java.util.Queue;

public class Practice1 {
    public static void main(String[] args) {
        System.out.println(findLastCard(4));
        System.out.println(findLastCard(7));
        System.out.println(findLastCard(9));
    }

    private static int findLastCard(int num) {
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i = 1 ; i<=num; i++){
            queue.offer(i);
        }

        while(queue.size() > 1){
            queue.poll();
            queue.offer(queue.poll());
        }

        return queue.poll();
    }
}

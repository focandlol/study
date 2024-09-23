package queue.p2_1;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Practice1 {
    public static void main(String[] args) {
        int docs = 1;
        int target = 0;
        int[] priority = {5};
        solution(docs,target,priority);

        docs = 4;
        target = 2;
        priority = new int[]{1,2,3,4};
        solution(docs,target,priority);

        docs = 6;
        target = 0;
        priority = new int[]{1,1,9,1,1,1};
        solution(docs,target,priority);
    }

    private static void solution(int docs, int target, int[] priority) {
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i=0; i<docs; i++) {
            queue.add(priority[i]);
        }

        int count = 1;
        int max = Collections.max(queue);
        while(!queue.isEmpty()){
            int cur = queue.peek();

            if(cur < max){
                queue.add(queue.poll());
                target = (target -1 + queue.size()) % queue.size();
            }else{
                if(target == 0){
                    System.out.println(count);
                    break;
                }else{
                    queue.poll();
                    target = (target -1 + queue.size()) % queue.size();
                    count++;
                    max = Collections.max(queue);
                }
            }
        }

    }
}

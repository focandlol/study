package queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Practice2 {
    public static void main(String[] args) {
        System.out.println(getJosephusPermutation(5,2));
        System.out.println(getJosephusPermutation(7,3));

    }

    private static ArrayList getJosephusPermutation(int n, int k) {
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i=1; i<=n; i++) {
            queue.offer(i);
        }

        int count = 1;
        ArrayList<Integer> arr = new ArrayList<>();
        while(!queue.isEmpty()) {
            int a = queue.poll();
            if(count == k || queue.isEmpty()) {
                count = 1;
                arr.add(a);
                continue;
            }
            queue.offer(a);
            count++;
        }
        return arr;
    }
}


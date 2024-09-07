package deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class Practice1 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        reorderData(arr);

        int[] arr2 = {1,2,3,4,5,6,7};
        reorderData(arr2);
    }

    private static void reorderData(int[] arr) {
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < arr.length; i++) {
            deque.add(arr[i]);
        }

        while (!deque.isEmpty()) {
            System.out.print(deque.removeFirst());
            if (!deque.isEmpty()) {
                System.out.print(" -> ");
                System.out.print(deque.removeLast());
                System.out.print(" -> ");
            }
        }
    }
}

package basic.bru;

import java.util.Stack;

public class aa {
    public static void main(String[] args) {
        int delay = 5; // 메시지를 처리하는 데 걸리는 시간
        int capacity = 5; // 큐의 용량
        //int[] times = {3, 2, 0, 0, 2, 3, 0, 0, 2, 2, 5}; // 소요된 시간 배열
        int[] times = {6,4,0,0,0,0,0};

        int lostMessages = solution(delay, capacity, times);
        System.out.println(lostMessages); // 결과 출력
    }

    public static int solution(int delay, int capacity, int[] times) {
        int size = 0;
        int time = 0;
        int cnt = 0;
        for (int k : times) {
            time += k;
            while (time >= delay) {
                if (size == 0) {
                    break;
                }
                size -= 1;
                time -= delay;
            }
            if (size == capacity) {
                cnt++;
            } else {
                size++;
            }
        }

        return cnt;
    }


}

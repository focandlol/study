package basic.bru;

import java.util.LinkedList;
import java.util.Queue;
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
        int time = 0;
        int count = 0;
        int size = 0;

        for(int i=0; i<times.length; i++){
            time += times[i];
            if(time >= delay){
                //size = size -1<0 ? 0 : size -1;

                size--;

                // while(time >= delay){
                //     size--;
                //     time -= delay;
                // }
                time -= delay;
                if(size < 0 && times[i] > delay){
                    time = 0;
                }
            }
            if(size >= capacity){
                count++;
            }else{
                size++;
            }
        }
        return count;
    }


}

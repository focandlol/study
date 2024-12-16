package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj7662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        long t = Long.parseLong(st.nextToken());

        StringBuilder sb = new StringBuilder();

        for(long i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            PriorityQueue<Integer> maxPq = new PriorityQueue<>((a,b) -> b.compareTo(a));
            PriorityQueue<Integer> minPq = new PriorityQueue<>((a,b) -> a.compareTo(b));
            HashMap<Integer, Integer> map = new HashMap<>();

            for(int j=0; j<k; j++){
                st = new StringTokenizer(br.readLine());
                String s = st.nextToken();
                char c = s.charAt(0);

                int number = Integer.parseInt(st.nextToken());

                if(c == 'I'){
                    maxPq.add(number);
                    minPq.add(number);
                    map.put(number, map.getOrDefault(number, 0) + 1);
                }else{
                    if(number == -1){
                        if(minPq.isEmpty()) continue;
                        else{
                            while(!minPq.isEmpty() && map.get(minPq.peek()) == 0){
                                minPq.poll();
                            }
                            if(!minPq.isEmpty()){
                                int poll = minPq.poll();
                                map.put(poll, map.get(poll) - 1);
                            }
                        }
                    }else{
                        while(!maxPq.isEmpty() && map.get(maxPq.peek()) == 0){
                            maxPq.poll();
                        }
                        if(!maxPq.isEmpty()){
                            int poll = maxPq.poll();
                            map.put(poll, map.get(poll) - 1);
                        }
                    }
                }
            }
            long max = Long.MAX_VALUE;
            long min = Long.MIN_VALUE;
            if(maxPq.isEmpty() && minPq.isEmpty()){
                sb.append("EMPTY").append("\n");
            }else{
                while(!maxPq.isEmpty() && map.get(maxPq.peek()) == 0){
                    maxPq.poll();
                }
                if(!maxPq.isEmpty()){
                    max = maxPq.peek();
                }
                while(!minPq.isEmpty() && map.get(minPq.peek()) == 0){
                    minPq.poll();
                }
                if(!minPq.isEmpty()){
                    min = minPq.peek();
                }
                if(max == Long.MAX_VALUE && min == Long.MIN_VALUE){
                    sb.append("EMPTY").append("\n");
                }else{
                    sb.append(max).append(" ").append(min).append("\n");
                }
            }
        }
        System.out.print(sb);

    }
}

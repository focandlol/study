package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        Km[] arr = new Km[n];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr[i] = new Km(start, end);
        }
        Arrays.sort(arr,(o1,o2) -> {
            if(o1.end == o2.end){
                return o1.start - o2.start;
            }
            return o1.end - o2.end;
        });

        int end = 0;
        int count = 0;
        for(int i=0; i<n; i++) {
            if(end <= arr[i].start) {
                end = arr[i].end;
                count++;
            }
        }
        System.out.println(count);
    }

    private static class Km {
        int start;
        int end;
        public Km(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }
}

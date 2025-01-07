package basic;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj30805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int[] b = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        Queue<Integer> queue = new ArrayDeque<Integer>();

        int startA = 0;
        int startB = 0;

        while (startA < n && startB < m) {
            int result = 0;
            for (int i = startA; i < n; i++) {
                for (int j = startB; j < m; j++) {
                    if (a[i] == b[j]) {
                        result = Math.max(result, a[i]);
                    }
                }
            }
            if (result != 0) {
                queue.add(result);
                while (a[startA] != result) startA++;
                while (b[startB] != result) startB++;
                startA++;
                startB++;
            } else {
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(queue.size()).append("\n");
        while(!queue.isEmpty()) {
            sb.append(queue.poll()).append(" ");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }
}

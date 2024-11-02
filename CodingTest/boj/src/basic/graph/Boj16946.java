package basic.graph;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj16946 {
    static int[][] di = {{0,1,0,-1},{1,0,-1,0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] arr = new char[n][m];
        int[][] group = new int[n][m];
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();

        for(int i=0; i<n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        int idx = 1;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(arr[i][j] == '0' && group[i][j] == 0) {
                    int count = 1;
                    group[i][j] = idx;
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[]{i, j});
                    while(!q.isEmpty()) {
                        int[] cur = q.poll();
                        for(int k=0; k<4; k++){
                            int x = cur[0] + di[0][k];
                            int y = cur[1] + di[1][k];
                            if(x >= 0 && x <n && y >= 0 && y < m && arr[x][y] == '0' && group[x][y] == 0) {
                                group[x][y] = idx;
                                count++;
                                q.add(new int[]{x, y});
                            }
                        }
                    }
                    map.put(idx,count);
                    idx++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                int count = 0;
                if(arr[i][j] == '1') {
                    count++;
                    HashSet<Integer> set = new HashSet<Integer>();
                    for(int k=0; k<4; k++){
                        int x = i + di[0][k];
                        int y = j + di[1][k];
                        if(x >=0 && x<n && y >= 0 && y < m && arr[x][y] == '0' && group[x][y] != 0) {
                            if(set.add(group[x][y])) {
                                count += map.get(group[x][y]);
                            }
                        }
                    }
                }

                sb.append(count % 10);
            }

            sb.append("\n");
        }
        System.out.println(sb);
    }
}

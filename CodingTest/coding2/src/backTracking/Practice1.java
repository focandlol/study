package backTracking;

import java.util.Arrays;

public class Practice1 {
    public static void main(String[] args) {
        solution(3,2);
    }

    private static void solution(int n, int m) {
        int[] arr = new int[m];
        boolean[] visited = new boolean[n+1];
        back(arr,visited,0,n,m);

    }

    private static void back(int[] arr, boolean[] visited, int depth,int n,int m) {
        if (depth == m) {
            System.out.print(Arrays.toString(arr) + " ");
            return;
        }

        for(int i=1; i<=n; i++){
            if(!visited[i]){
                arr[depth] = i;
                visited[i] = true;
                back(arr,visited,depth+1,n,m);
                visited[i] = false;
            }

        }
    }
}

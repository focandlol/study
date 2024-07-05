import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon9095TopDown {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        arr = new int[11];
        for(int i=0; i<arr.length; i++){
            arr[i] = -1;
        }
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 4;

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            System.out.println(find(a));
        }
    }

    private static int find(int a) {
        if(arr[a] != -1){
            return arr[a];
        }
        return arr[a] = find(a-1) + find(a-2) + find(a-3);
    }
}

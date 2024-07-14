import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BaekJoon2485 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] cha = new int[n-1];
        int[] num = new int[n];
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(br.readLine());
            num[i] = a;
        }

        for(int i=0; i<n-1; i++){
            cha[i] = num[i+1] - num[i];
        }

        int minCom = 0;
        for(int i=0; i<n-1; i++){
            minCom = find(cha[i],minCom);
        }

        int count = (num[n-1] - num[0]) / minCom + 1 - n;
        System.out.println(count);

    }

    private static int find(int a, int b) {
        if(b == 0){
            return a;
        }
        return find(b,a%b);
    }
}

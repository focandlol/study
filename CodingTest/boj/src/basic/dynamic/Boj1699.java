package basic.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Boj1699 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n+1];
        arr[1]  = 1;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=2; i<=n; i++){
            if(Math.sqrt(i) % 1 == 0){
                arr[i] = 1;
                list.add(i);
            }
            else{
                arr[i] = arr[i-1] + 1;
                for(int j=0; j<list.size(); j++) {
                    arr[i] = Math.min(arr[i], arr[list.get(j)] + arr[i - list.get(j)]);
                }
            }
        }
        System.out.println(arr[n]);

    }
}

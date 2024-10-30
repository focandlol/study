package basic.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Boj1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> plus = new ArrayList<Integer>();
        ArrayList<Integer> minus = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            int a = Integer.parseInt(br.readLine());
            if(a < 1){
                minus.add(a);
            }else{
                plus.add(a);
            }
        }

        Collections.sort(plus,Collections.reverseOrder());
        Collections.sort(minus);

        int sum = 0;
        int idx = 0;
        while(idx < plus.size()) {
            if(idx + 1 < plus.size() && plus.get(idx + 1) != 1 && plus.get(idx) != 1) {
                sum += plus.get(idx) * plus.get(idx + 1);
                idx+=2;
            }else{
                sum += plus.get(idx++);
            }
        }

        idx = 0;
        while(idx< minus.size()) {
            if(idx + 1 < minus.size()) {
                sum += minus.get(idx) * minus.get(idx + 1);
                idx+=2;
            }else{
                sum += minus.get(idx++);
            }

        }
        System.out.println(sum);

    }
}

package basic.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2875 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int canN = n / 2;
        int canM = m;
        int remain = 0;
        int canTeam = 0;
        if (canN > canM) {
            canTeam = canM;
            remain += n - 2*canTeam;
        }else if (canN < canM) {
            canTeam = canN;
            remain += n - 2*canTeam;
            remain += m - canTeam;
        }else{
            canTeam = canM;
            remain += n - 2*canTeam;
        }

        int na = k-remain;
        if(na <= 0){
            System.out.println(canTeam);
        }else{
            int a = na / 3;
            if(na % 3 == 0){
                System.out.println(canTeam - a >= 0 ? (canTeam - a) : 0);
            }else{
                System.out.println(canTeam - (a + 1) >= 0 ? (canTeam - (a + 1)) : 0);
            }
        }
    }
}

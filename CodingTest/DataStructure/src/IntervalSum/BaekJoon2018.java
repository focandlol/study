package IntervalSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon2018
{
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int count =0;
        int start = 0;
        int end = 0;
        int sum = 0;

        while(start <= num){
            if(sum == num){
                count++;
                sum += end;
                end++;
            }
            if(sum < num){
                sum += end;
                end++;
            }
            if(sum > num){
                sum -= start;
                start++;
            }
        }
        System.out.println(count);


    }
}


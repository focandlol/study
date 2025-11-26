```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if(n == 1){
            System.out.println("0");
            return;
        }
       int[] arr = new int[n+1];
        ArrayList<Integer> list = new ArrayList<>();


       for(int i=2; i*i<=n; i++){
           if(sosu(i)){
               for(int j = i*i; j<=n; j = j + i){
                   arr[j] = 1;
               }
           }
       }
       for(int i=2; i<=n; i++){
           if(arr[i] == 0){
               list.add(i);
           }
       }

       int left = 0;
       int right = 0;
       int count = 0;
       int sum = 2;
       while(left <= right){
           if(sum < n){
               if(right == list.size()-1){
                   break;
               }
               right++;
               sum += list.get(right);
           }else {
               if (sum == n){
                   count++;
               }
               sum -= list.get(left);
               left++;
           }
       }

       System.out.println(count);




    }

    private static boolean sosu(int i) {
        for(int a = 2; a*a <= i; a++){
            if(i % a == 0){
                return false;
            }
        }
        return true;
    }
}

```

```
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int need = sc.nextInt();
        int count = 0;
        int i = 1;
        int j = num;

        int[] arr = new int[num + 1];
        arr[0] = 0;

        for(int b=1; b<=num; b++){
            arr[b] = Integer.parseInt(sc.next());
        }

        Arrays.sort(arr);

        int start = arr[i];
        int end = arr[j];
        while(end > start){
            if(start + end == need){
                count++;
                j -=1;
                i +=1;
                end = arr[j];
                start = arr[i];
            }
            if(start + end > need){
                j -= 1;
                end = arr[j];
            }
            if(start + end < need){
                i += 1;
                start = arr[i];
            }
        }

        System.out.println(count);
    }
}
```

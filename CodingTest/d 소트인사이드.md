```
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] a = sc.next().split("");

        for(int i=0; i<a.length; i++){
            int max = 0;
            int rMax = 0;
            String temp = null;
            boolean swap = true;
            for(int j=i; j<a.length; j++){
                if(Integer.parseInt(a[j]) > rMax){
                    max = j;
                    rMax = Integer.parseInt(a[j]);
                    swap = false;
                }
            }
            if(swap){
                break;
            }

            if(Integer.parseInt(a[i]) < Integer.parseInt(a[max]) && i != max) {
                temp = a[i];
                a[i] = a[max];
                a[max] = temp;
            }
        }

        for(int i=0; i<a.length; i++){
            System.out.print(a[i]);
        }
    }
}
```

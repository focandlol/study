import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon10872 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int dap = 1;
        for(int i=n; i>1; i--){
            dap *= i;
        }

        if(n==0 || n==1){
            System.out.println(1);
        }else{
            System.out.println(dap);
        }
    }
}

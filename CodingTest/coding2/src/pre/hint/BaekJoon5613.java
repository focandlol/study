package pre.hint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon5613 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a = "";
        int dap = 0;
        while((a = br.readLine()) != null){
            if(a.equals("+")){
                dap += Integer.parseInt(br.readLine());
            }else if(a.equals("-")){
                dap -= Integer.parseInt(br.readLine());
            }else if(a.equals("*")){
                dap *= Integer.parseInt(br.readLine());
            }else if (a.equals("/")){
                dap /= Integer.parseInt(br.readLine());
            }else if(a.equals("="))break;
            else{
                dap = Integer.parseInt(a);
            }
        }
        System.out.println(dap);
    }
}

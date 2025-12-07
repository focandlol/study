```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int l;
    static int c;
    static char[] arr;
    static char[] dap;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new char[c];
        dap = new char[l];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<c; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(arr);

        back(0,0,false,0);
        System.out.println(sb);
    }

    private static void back(int depth, int start, boolean mo,int ja) {
        if(depth == l){
            if(!mo || ja<2) return;
            else {
                for (int i = 0; i < l; i++) {
                    sb.append(dap[i]);
                }
                sb.append('\n');
            }
            return;
        }

        for(int i=start; i<c; i++){
            dap[depth] = arr[i];
            if(arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u'){
                back(depth+1, i+1, true,ja);
            }else{
                back(depth+1, i+1, mo,ja+1);
            }
        }
    }
}
```

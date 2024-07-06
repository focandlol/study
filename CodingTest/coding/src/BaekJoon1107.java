import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1107 {
    static boolean[] broken;
    static int target;
    static long count;

    public static void dfs(int idx, int click){
        for(int i=0; i<10; i++){
            if(!broken[i]){
                int newBtn = click*10+i;
                int cnt = Math.abs(target-newBtn) + String.valueOf(newBtn).length();
                count = Math.min(count,cnt);

                if(idx<6){
                    dfs(idx+1,newBtn);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        broken = new boolean[10];
        if(n>0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++){
                int cur = Integer.parseInt(st.nextToken());
                broken[cur] = true;
            }
        }

        if(target == 100){
            System.out.println(0);
            return;
        }

        count = Math.abs(target-100);
        dfs(0,0);
        System.out.println(count);

    }
}

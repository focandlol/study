package dynamic;

public class Practice3 {
    public static void main(String[] args) {
        int[][] items = {{6,13},{4,8},{3,6},{5,12}};
        System.out.println(solution(items,4,7));
    }

    private static int solution(int[][] items, int n, int k) {
        int[][] dp = new int[n+1][k+1];

        for(int i=0; i<n; i++){
            for(int j=1; j<=k; j++){
                if(items[i][0] > j){
                    dp[i+1][j] = dp[i][j];
                }else{
                    dp[i+1][j] = Math.max(dp[i][j],items[i][1] + dp[i][j-items[i][0]]);
                }
            }
        }
        return dp[n][k];
    }
}

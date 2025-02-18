package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Boj9252 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String a1 = br.readLine();
    String a2 = br.readLine();

    int[][] dp = new int[a2.length()+1][a1.length()+1];

    for(int i=1; i<=a2.length(); i++){
      for(int j=1; j<=a1.length(); j++){

        if(a1.charAt(i-1)==a2.charAt(j-1)){
          dp[j][i] = dp[j-1][i-1] + 1;
        }else{
          dp[j][i] = Math.max(dp[j-1][i], dp[j][i-1]);
        }
      }
    }

    int i = a2.length();
    int j = a1.length();
    StringBuilder sb = new StringBuilder();
    Stack<Character> st = new Stack<>();
    while (i > 0 && j > 0) {
      if (dp[i][j] == dp[i - 1][j]) {
        i--;
      } else if (dp[i][j] == dp[i][j - 1]) {
        j--;
      } else {
        st.push(a1.charAt(j-1));
        i--;
        j--;
      }
    }
    while (!st.isEmpty()) {
      sb.append(st.pop());
    }

    System.out.println(dp[a2.length()][a1.length()]);
    System.out.println(sb);
  }

}

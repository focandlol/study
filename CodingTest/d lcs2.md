```
import java.io.*;
 import java.util.Stack;

 public class Main {
     static int[][] dp;
     static StringBuilder sb = new StringBuilder();
     
     public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


         char[] str1 = br.readLine().toCharArray();
         char[] str2 = br.readLine().toCharArray();

         int length_1 = str1.length;
         int length_2 = str2.length;

         dp = new int[length_1 + 1][length_2 + 1];

         for(int i = 1; i <= length_1; i++) {
             for(int j = 1; j <= length_2; j++) {
                 // (i-1)번째 문자 == (j-1)번째 문자
                 if(str1[i - 1] == str2[j - 1]) {
                     dp[i][j] = dp[i - 1][j - 1] + 1;
                 } else {
                     //이전 열(i-1)과 이전 행(j-1)의 값 중 큰 것으로 갱신
                     dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                 }
             }
         }
         ToString(str1, length_1, length_2);
         System.out.println(dp[length_1][length_2]);
         System.out.println(sb);
     }
     static void ToString(char[] str, int i, int j) {
         Stack<Character> st = new Stack<>();
         while (i > 0 && j > 0) {
             if (dp[i][j] == dp[i - 1][j]) {
                 i--;
             } else if (dp[i][j] == dp[i][j - 1]) {
                 j--;
             } else {
                 st.push(str[i-1]);
                 i--;
                 j--;
             }
         }
         while (!st.isEmpty()) {
             sb.append(st.pop());
         }
     }
 }
```

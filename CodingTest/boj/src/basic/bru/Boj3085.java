package basic.bru;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj3085 {
    static String[][] arr;
    static int n;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new String[n][n];


        for(int i=0; i<n; i++){
            String input = br.readLine();
            for(int j=0; j<n; j++){
                arr[i][j] = String.valueOf(input.charAt(j));
            }
        }



        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++) {
                if (j + 1 < n) {
                    if (!arr[i][j].equals(arr[i][j + 1])) {
                        changeA(i, j);
                    }
                    if (!arr[j][i].equals(arr[j + 1][i])) {
                        changeB(j, i);
                    }
                }
            }
        }

        System.out.println(max);

    }

    private static void changeB(int j, int i) {
        String temp = arr[j][i];
        arr[j][i] = arr[j+1][i];
        arr[j+1][i] = temp;
        find();
        temp = arr[j][i];
        arr[j][i] = arr[j+1][i];
        arr[j+1][i] = temp;
    }

    private static void find() {
        int countA = 1;
        int countB = 1;
        for(int a=0; a<n; a++){
            for(int b = 0; b<n; b++){
                if(b+1<n) {
                    if (arr[a][b].equals(arr[a][b + 1])) {
                        countA++;
                    } else {
                        max = Math.max(countA, max);
                        countA = 1;
                    }
                    if(arr[b][a].equals(arr[b+1][a])){
                        countB++;
                    }else{
                        max = Math.max(countB, max);
                        countB = 1;
                    }
                }
            }
            max = Math.max(countA, max);
            max = Math.max(countB, max);
            countA = 1;
            countB = 1;
        }
    }

    private static void changeA(int i, int j) {
        String temp = arr[i][j];
        arr[i][j] = arr[i][j+1];
        arr[i][j+1] = temp;
        find();
        temp = arr[i][j];
        arr[i][j] = arr[i][j+1];
        arr[i][j+1] = temp;
    }
}
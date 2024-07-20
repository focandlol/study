package recursion;

import java.util.Scanner;

public class BaekJoon2447__1117 {
    static String[][] arr;
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        int n  = sc.nextInt();

        arr = new String[n][n];

        find(0,0,n,false);
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void find(int x, int y, int n,boolean flag) {

        if(flag){
            for(int i=x; i<x+n; i++){
                for(int j=y; j<y+n; j++){
                    arr[i][j] = " ";
                }
            }
            return;
        }
        if(n == 1){
            arr[x][y] = "*";
            return;
        }

        int size = n/3;
        int count = 0;
        for(int i = x; i<x+n; i+=size){
            for(int j=y; j<y+n; j+=size){
                count++;
                if(count == 5){
                    find(i,j,size,true);
                }else{
                    find(i,j,size,false);
                }
            }
        }
    }
}

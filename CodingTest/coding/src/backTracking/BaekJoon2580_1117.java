package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon2580_1117 {
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        arr = new int[9][9];
        for(int i=0; i<9; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        find(0,0);

    }

    private static void find(int row, int col) {

        if(row == 9){
            find(0, col + 1);
            return;
        }

        if(col == 9) {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<9; i++) {
                for(int j=0; j<9; j++) {
                    sb.append(arr[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        }


        if(arr[row][col] == 0){
            for(int i=1; i<=9; i++){
                if(check(row,col,i)){
                    arr[row][col] = i;
                    find(row + 1,col);
                }
            }
            arr[row][col] = 0;
            return;
        }
        find(row + 1,col);
    }

    private static boolean check(int row, int col, int i) {
        for(int j=0; j<9; j++){
            if(arr[row][j] == i){
                return false;
            }
        }
        for(int j=0; j<9; j++){
            if(arr[j][col] == i){
                return false;
            }
        }
        int x = (row / 3) * 3;
        int y = (col / 3) * 3;

        for(int j=x; j<=x+2; j++){
            for(int k=y; k<=y+2; k++){
                if(arr[j][k] == i){
                    return false;
                }
            }
        }
        return true;
    }
}

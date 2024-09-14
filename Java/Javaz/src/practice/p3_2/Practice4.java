package practice.p3_2;

import java.util.ArrayList;

public class Practice4 {
    public static void main(String[] args) {
        int[][] arr = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(solution(arr));

        arr = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        System.out.println(solution(arr));
    }

    private static ArrayList<Integer> solution(int[][] arr) {
        ArrayList<Integer> res = new ArrayList<>();
        int xStart = 0;
        int xEnd = arr[0].length - 1;
        int yStart = 0;
        int yEnd = arr.length - 1;
        while(xStart <= xEnd && yStart <= yEnd) {
            for(int i = xStart; i <= xEnd; i++) {
                res.add(arr[yStart][i]);
            }
            yStart++;

            for(int i = yStart; i<= yEnd; i++) {
                res.add(arr[i][xEnd]);
            }
            xEnd--;

            if(yStart <= yEnd) {
                for (int i = xEnd; i >= xStart; i--) {
                    res.add(arr[yEnd][i]);
                }
            }
            yEnd--;

            if(xStart <= xEnd) {
                for (int i = yEnd; i >= yStart; i--) {
                    res.add(arr[i][xStart]);
                }
            }
            xStart++;
        }

        return res;
    }
}

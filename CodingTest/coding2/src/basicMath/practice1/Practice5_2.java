package basicMath.practice1;

public class Practice5_2 {
    static int[][] direction = {{0,1},{1,0},{0,-1},{-1,0}};
    static int sum = 0;
    public static void main(String[] args) {
        System.out.println(find(new int[][]{{1}}));
        System.out.println(find(new int[][]{{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}}));
    }

    private static int find(int[][] arr) {
        sum = 0;
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[i].length; j++) {
                if(arr[i][j] == 1) {
                    return re(i,j,arr);
                }
            }
        }
        return 0;
    }

    private static int re(int i, int j, int[][] arr) {
        arr[i][j] = -1;
        for(int k=0; k<4; k++){
            int x = i+direction[k][0];
            int y = j+direction[k][1];
            if(x < 0 || y < 0 || x >= arr.length || y >= arr[0].length || arr[x][y] == 0) {
                sum++;
            }else {
                if(arr[x][y] == 1) {
                    re(x,y,arr);
                }
            }
        }
        return sum;
    }
}

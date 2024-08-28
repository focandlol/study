package basicMath.practice1;

public class Practice5 {
    public static void main(String[] args) {
        System.out.println(find(new int[][]{{1}}));
        System.out.println(find(new int[][]{{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}}));
    }

    private static int find(int[][] arr) {
        int sum = 0;
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[i].length; j++) {
                int count = 4;
                if(arr[i][j] == 1){
                    if(i-1>=0 && arr[i-1][j] == 1){
                        count--;
                    }
                    if(i+1<arr.length && arr[i+1][j] == 1){
                        count--;
                    }
                    if(j-1>=0 && arr[i][j-1] == 1){
                        count--;
                    }
                    if(j+1<arr[0].length && arr[i][j+1] == 1){
                        count--;
                    }
                    sum+=count;
                }
            }
        }
        return sum;
    }
}


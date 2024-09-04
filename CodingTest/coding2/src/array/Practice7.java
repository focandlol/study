package array;

public class Practice7 {
    public static void main(String[] args) {
        int[][] arr = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15}};
        int[][] arr2 = new int[arr[0].length][arr.length];
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[i].length; j++) {
                arr2[j][arr.length -1 - i] = arr[i][j];
            }
        }

        for(int i=0; i<arr2.length; i++) {
            for(int j=0; j<arr2[i].length; j++) {
                System.out.print(arr2[i][j] + " ");
            }
            System.out.println();
        }
    }
}

package binarySearch;

public class Practice3 {
    public static void main(String[] args) {
        int[][] matrix = {{1,3,7,8},{10,11,15,20},{21,30,35,60}};
        System.out.println(solution(matrix,3));
        System.out.println(solution(matrix,13));
        System.out.println(solution(matrix,35));
    }

    private static boolean solution(int[][] matrix, int a) {
        int left = 0;
        int right = matrix.length * matrix[0].length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if(matrix[mid/matrix[0].length][mid%matrix[0].length] == a){
                return true;
            }else if(matrix[mid/matrix[0].length][mid%matrix[0].length] < a){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return false;
    }
}

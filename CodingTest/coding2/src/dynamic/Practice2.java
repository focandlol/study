package dynamic;

public class Practice2 {
    public static void main(String[] args) {
        int[] arr = new int[]{10,20,30,10,50,10};
        System.out.println(solution(arr));
    }

    private static int solution(int[] arr) {
        int count = 1;
        for(int i=1;i<arr.length;i++) {
            if(arr[i] > arr[i-1]) {
                count++;
            }else{
                arr[i] = arr[i-1];
            }
        }
        return count;
    }
}

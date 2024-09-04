package array;

public class Practice1 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9};
        float sumEven = 0;
        int evenCount = 0;
        int oddCount = 0;
        float sumOdd = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                sumEven += arr[i];
                evenCount++;
            }else{
                sumOdd += arr[i];
                oddCount++;
            }
        }

        System.out.println("짝수 평균 : " + sumEven / evenCount);
        System.out.println("홀수 평균 : " + sumOdd / oddCount);
    }
}

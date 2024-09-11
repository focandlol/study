package practice.p1_2;

public class Practice5 {
    public static void main(String[] args) {
        int[] arr = {1,8,6,2,5,4,8,3,7};
        System.out.println(solution(arr));

        arr = new int[]{5,3,9,2,1,1,1,1,1,1,1,1,1,2};
        System.out.println(solution(arr));
    }

    private static int solution(int[] arr) {
        int left = 0;
        int right = arr.length-1;
        int max = 0;

        while(left < right){
            int minHeight = Math.min(arr[left], arr[right]);
            max = Math.max(max,Math.abs(right-left)*minHeight);

            if(minHeight == arr[left]){
                left++;
            }else{
                right--;
            }
        }
        return max;
    }
}

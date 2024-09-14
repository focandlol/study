package practice.p3_2;

public class Practice5 {
    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(solution(height));

        height = new int[]{4,2,0,3,2,5};
        System.out.println(solution(height));
    }

    private static int solution(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        int left = arr[start];
        int right = arr[end];

        int sum = 0;

        while(start < end){
            if(arr[start] > arr[end]){
                end--;
                if(arr[end] < right){
                    sum += right - arr[end];
                }else{
                    right = arr[end];
                }
            }else{
                start++;
                if(arr[start] < left){
                    sum += left - arr[start];
                }else{
                    left = arr[start];
                }
            }
        }
        return sum;
    }
}


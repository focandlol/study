package binarySearch;

public class Practice5 {
    public static void main(String[] args) {
        int[] nums = {7,2,5,10,8};
        System.out.println(solution(nums,2));

        nums = new int[]{1,2,3,4,5};

        System.out.println(solution(nums,2));
    }

    private static int solution(int[] nums, int a) {
        int left = 0;
        int right = 0;
        for(int i = 0; i < nums.length; i++){
            left = Math.max(left,nums[i]);
            right += nums[i];
        }

        while(left < right){
            int mid = left + (right - left)/2;
            int count = 1;
            int sum = 0;
            for(int i=0; i<nums.length; i++){
                if(sum + nums[i] > mid){
                    count++;
                    sum = 0;
                }
                sum += nums[i];
            }

            if(count > a){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return left;
    }
}

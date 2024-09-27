package greedy;

public class Practice1 {
    public static void main(String[] args) {
        int[] nums = {2,3,0,1,4};
        System.out.println(solution(nums));
    }

    private static boolean solution(int[] nums) {
        int pos = 0;

        for(int i=0; i<nums.length; i++) {
            if(pos < i)return false;
            else if(pos >= nums.length-1)return true;
            pos = Math.max(i + nums[i], pos);
        }
        return true;
    }
}

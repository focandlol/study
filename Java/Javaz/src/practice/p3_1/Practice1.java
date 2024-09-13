package practice.p3_1;

public class Practice1 {
    public static void main(String[] args) {
        solution(new int[]{1,1,2});
        solution(new int[]{0,0,1,1,1,2,2,3,3,4});
    }

    private static void solution(int[] nums) {
        int idx = 1;
        for(int i=1; i<nums.length; i++){
            if(nums[i] > nums[idx-1]){
                nums[idx++] = nums[i];
            }
        }

        for(int i=0; i<idx; i++){
            System.out.print(nums[i] + " ");
        }
        System.out.println();

    }
}

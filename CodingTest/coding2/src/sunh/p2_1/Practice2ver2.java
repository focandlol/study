package sunh.p2_1;

import java.util.Stack;

public class Practice2ver2 {
    public static void main(String[] args) {
        int[] nums = {4,3,6,8,7,5,2,1};
        solution(nums);

        nums = new int[]{1,2,5,3,4};
        solution(nums);

//        nums = new int[]{4,3,2,1,1};
//        solution(nums);
    }

    private static void solution(int[] nums) {
        Stack<Integer> stack = new Stack<Integer>();

        int idx = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            if(idx < nums[i]){
                for(int j = idx+1; j<= nums[i]; j++){
                    stack.push(j);
                    sb.append("+").append(" ");
                }
                idx = nums[i];
            }else if(idx > nums[i]){
                if(!stack.isEmpty() && stack.peek() != nums[i]){
                    System.out.println("NO");
                    return;
                }
            }
            stack.pop();
            sb.append("-").append(" ");
        }
        System.out.println(sb);
    }
}

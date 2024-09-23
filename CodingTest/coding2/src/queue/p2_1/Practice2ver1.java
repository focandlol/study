package queue.p2_1;

import java.util.Stack;

public class Practice2ver1 {
    public static void main(String[] args) {
        int[] nums = {4,3,6,8,7,5,2,1};
        solution(nums);

//        nums = new int[]{1,2,5,3,4};
//        solution(nums);

        nums = new int[]{4,3,2,1,1};
        solution(nums);
    }

    private static void solution(int[] nums) {
        Stack<Integer> stack = new Stack<Integer>();
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        for(int i=1; i<=nums[0]; i++){
            stack.push(i);
            sb.append("+").append(" ");
        }

        while(!stack.isEmpty()){
            int cur = stack.pop();
            sb.append("-").append(" ");
            idx++;
            while(!stack.isEmpty() && nums[idx] < cur){
                if(nums[idx] == stack.peek()) {
                    stack.pop();
                    idx++;
                    sb.append("-").append(" ");
                }else{
                    System.out.println("No");
                    System.exit(0);
                }
            }

            if(idx < nums.length) {
                for (int i = cur + 1; i <= nums[idx]; i++) {
                    stack.push(i);
                    sb.append("+").append(" ");
                }
            }
        }
        if(idx == nums.length) {
            System.out.println(sb);
        }else{
            System.out.println("No");
        }
    }
}

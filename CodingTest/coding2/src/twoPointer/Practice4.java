package twoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Practice4 {
    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        System.out.println(solution(nums));
        nums = new int[] {1,-7,6,3,5,2};
        System.out.println(solution(nums));
    }

    private static ArrayList<ArrayList<Integer>> solution(int[] nums) {
        Arrays.sort(nums);
        int start = 0;
        int end = nums.length - 1;

        HashSet<ArrayList<Integer>> set = new HashSet<>();
        for(int i = 0; i < nums.length - 2; i++){
            start = i + 1;
            end = nums.length - 1;
            while(start < end){
                if(nums[i] + nums[start] + nums[end] == 0){
                    set.add(new ArrayList<>(Arrays.asList(nums[i], nums[start], nums[end])));
                    start++;
                    end--;
                }else if(nums[i] + nums[start] + nums[end] > 0){
                    end--;
                }else{
                    start++;
                }
            }
        }
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for (ArrayList<Integer> arr : set) {
            res.add(arr);
        }
        return res;

    }
}

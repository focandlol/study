package twoPointer;

import java.util.Arrays;
import java.util.HashSet;

public class Practice2 {
    public static void main(String[] args) {
        int[] nums1 = {1,2,2,1};
        int[] nums2 = {2,2};
        System.out.println(Arrays.toString(solution(nums1,nums2)));

        int[] nums3 = {4,9,5};
        int[] nums4 = {9,4,9,8,4};
        System.out.println(Arrays.toString(solution(nums3,nums4)));
    }

    private static int[] solution(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int a = 0;
        int b = 0;
        HashSet<Integer> set = new HashSet<>();
        while(a<nums1.length && b<nums2.length){
            if(nums1[a]<nums2[b]){
                a++;
            }else if(nums1[a] > nums2[b]){
                b++;
            }else{
                set.add(nums1[a]);
                a++;
                b++;
            }
        }
        int[] arr = new int[set.size()];
        int idx = 0;
        for (Integer i : set) {
            arr[idx++] = i;
        }
        return arr;
    }
}

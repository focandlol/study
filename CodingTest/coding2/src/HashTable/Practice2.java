package HashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class Practice2 {
    public static void main(String[] args) {
        int[] nums = {7,11,5,3};
        System.out.println(Arrays.toString(solution(nums,10)));

        nums = new int[]{1,2,3};
        System.out.println(Arrays.toString(solution(nums,10)));
    }

    public static int[] solution(int[] numbers, int target){
        Hashtable<Integer,Integer> table = new Hashtable<>();
        int[] result = new int[2];
        for(int i=0;i<numbers.length;i++){
            if(table.containsKey(numbers[i])){
                result[0] = table.get(numbers[i]);
                result[1] = i;
                return result;
            }
            table.put(target - numbers[i],i);
        }
        return null;
    }
}

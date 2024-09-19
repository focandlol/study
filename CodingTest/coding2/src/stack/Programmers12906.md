```
import java.util.*;

public class Solution {
  public int[] solution(int []arr) {
       Stack<Integer> stack = new Stack();
       stack.push(arr[0]);
        
        for(int i=1; i<arr.length; i++){
            if(stack.peek() != arr[i]){
                stack.push(arr[i]);
            }
        }
        
        arr = new int[stack.size()];
        for(int i = arr.length-1; i>=0; i--){
            arr[i] = stack.pop();
        }
        
        return arr;
}
}
```
![programmers12906](https://github.com/user-attachments/assets/22b001a0-b400-4ccd-a8c8-1364b65ace84)
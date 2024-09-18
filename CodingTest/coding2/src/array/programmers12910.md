```
import java.util.ArrayList;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int i=0; i<arr.length; i++){
            if(arr[i] % divisor == 0){
                list.add(arr[i]);
            }
        }
        
        if(list.size() == 0){
            list.add(-1);
        }
        
        return list.stream().sorted().mapToInt(i -> i).toArray();
    }
}
```
![image](https://github.com/user-attachments/assets/e048e710-0556-4512-8969-84ba3250b0a7)

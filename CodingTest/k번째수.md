```
import java.util.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] dap = new int[commands.length];
        
        for(int i=0; i<commands.length; i++){
            ArrayList<Integer> list = new ArrayList<>();
            
            for(int b = commands[i][0]-1; b < commands[i][1]; b++){
                list.add(array[b]);
            }
            
            Collections.sort(list);
            
            dap[i] = list.get(commands[i][2] - 1);
        }
        
        return dap;
    }
}
```

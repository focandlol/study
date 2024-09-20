```
import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        LinkedList<Integer> list = new LinkedList<>();
        for(int i=0; i<priorities.length; i++){
            list.add(priorities[i]);
        }
        int max = Collections.max(list);
        int count = 1;

        while(list.size() > 1){
            if(list.getFirst() == max){
                if(location == 0){
                    break;
                }
                list.remove();
                count++;
                max = Collections.max(list);
            }else{
                list.add(list.remove());
            }
            location--;
            if(location < 0){
                location = list.size()-1;
            }
        }
        return count;
    }
}
```
![image](https://github.com/user-attachments/assets/59424a7c-42c1-4585-a133-1d7fa595396a)

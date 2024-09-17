```
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<progresses.length; i++){
            queue.add((int)Math.ceil((100.0 - progresses[i]) / speeds[i]));
        }
        
        while(!queue.isEmpty()){
            int a = queue.poll();
            int count = 1;
            while(!queue.isEmpty() && queue.peek() <= a){
                queue.poll();
                count++;
            }
            list.add(count);
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
}
```

### 작업이 이미 다 들어와 있는 상태가 아닌 순차적으로 들어온다고 생각하고 풀어야 함

```
import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        
        int dap = 0;
        int time = 0;
        int idx = 0;
        
        while(true){
            while(idx < jobs.length && jobs[idx][0] <= time){
                pq.add(jobs[idx]);
                idx++;
            }
            
            if(pq.isEmpty()){
                time = jobs[idx][0];
            }else{
                int[] a = pq.poll();
                dap += a[1] + time - a[0];
                time += a[1];
            }
            if(idx >= jobs.length && pq.isEmpty()){
                break;
            }
        }
        return dap / jobs.length;
    }
}
```
![image](https://github.com/user-attachments/assets/bef1684b-e2bd-4988-9acf-763d27d7c42b)

### 작업이 순차적으로 들어온다고 생각해야 했지만, 이미 모든 작업이 들어와 있다고 생각하는 잘못을 했다.
### 문제를 제대로 파악하니 생각보다 쉽게 풀렸다.
```
import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        
        int dap = 0;
        int time = 0;
        int idx = 0;
        int count = 0;
        
        while(count < jobs.length){
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
                count++;
            }
        }
        return dap / jobs.length;
    }
}
```
![image](https://github.com/user-attachments/assets/f4b57175-a75b-4edc-b8be-e68f0b282d94)

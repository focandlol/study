```
import java.util.HashMap;
class Solution {
    public int solution(String[][] clothes) {
        HashMap<String,Integer> map = new HashMap<>();
        for(int i=0; i<clothes.length; i++){
            if(map.containsKey(clothes[i][1])){
                map.replace(clothes[i][1],map.get(clothes[i][1]) + 1);
            }else{
                map.put(clothes[i][1],1);
            }
        }
        
        int sum = 1;
        for(String key : map.keySet()){
            sum *= (map.get(key)+1);
        }
        return sum-1;
    }
}
```
![image](https://github.com/user-attachments/assets/ee0ad0ac-af8e-4cd8-bd22-d7922cc9407c)

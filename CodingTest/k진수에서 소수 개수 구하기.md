```
class Solution {
    public int solution(int n, int k) {
        String s = Integer.toString(n,k);
        
        String[] split = s.split("0");
        int count = 0;
        
        for(String sp : split){
            if("".equals(sp)) continue;
            
            if(check(Long.parseLong(sp))) count++;
        }
        
        return count;
        
    }
    
    public boolean check(long l){
        if(l <= 1) return false;
        
        for(long i = 2; i<=Math.sqrt(l); i++){
            if(l % i == 0) return false;
        }
        
        return true;
    }
}
```

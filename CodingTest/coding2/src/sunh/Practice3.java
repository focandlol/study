package sunh;

public class Practice3 {
    public static void main(String[] args) {
        int[] arr = {3,2,1,-3,-1};
        solution(arr);

        arr = new int[]{3,2,-1,-2};
        solution(arr);
    }

    private static void solution(int[] arr) {
        boolean[] pop = new boolean[arr.length];
        StringBuilder sb = new StringBuilder();
        sb.append(1).append(" ");
        pop[0] = true;
        int idx = 0;
        for (int i = 1; i < arr.length; i++) {
            int count = 0;
            int a = arr[idx];
            if(a < 0){
                while(count < Math.abs(a)){
                    idx = (idx - 1 + arr.length) % arr.length;
                    count++;
                    if(pop[idx]){
                        count--;
                    }
                }
                pop[idx] = true;
                sb.append(idx+1).append(" ");
            }else{
                while(count < Math.abs(a)){
                    idx = (idx + 1 + arr.length) % arr.length;
                    count++;
                    if(pop[idx]){
                        count--;
                    }
                }
                pop[idx] = true;
                sb.append(idx+1).append(" ");
            }
        }
        System.out.println(sb);
    }
}

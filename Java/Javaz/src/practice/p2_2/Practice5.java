package practice.p2_2;

public class Practice5 {
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        System.out.println(solution(arr));
        arr = new int[]{3,2,1};
        System.out.println(solution(arr));
        arr = new int[]{1,0,2};
        System.out.println(solution(arr));
        arr = new int[]{1,2,2};
        System.out.println(solution(arr));
        arr = new int[]{1,3,5,3,1,3,5,7,5,3,1,0};
        System.out.println(solution(arr));
    }

    private static int solution(int[] arr) {
        int up = 1;
        int down = 0;
        int peak = 0;
        int dap = 1;
        for(int i=1; i<arr.length; i++) {
            if(arr[i] > arr[i-1]){
                up+=1;
                dap += up;
                peak = up;
                down = 0;
            }else if(arr[i] < arr[i-1]){
                down+=1;
                dap += down;
                up = 1;

                if(peak <= down){
                    dap += 1;
                }
            }else{
                up = 1;
                down = 0;
                peak = 0;
                dap += 1;
            }
        }
        return dap;
    }
}

package algo;

public class Practice2 {
    public static void main(String[] args) {
        int n=4;
        int[] plates ={7,9,7,30,2,7,9,25};
        int types = 30;
        int coupon = 30;
        System.out.println(solution(n,plates,types,coupon));
    }

    private static int solution(int n, int[] plates, int types, int coupon) {
        int[] arr = new int[types+1];
        int count = 0;

        for(int i=0; i<n; i++){
            if(arr[plates[i]]==0){
                count++;
            }
            arr[plates[i]]++;
        }

        int max = count;

        for(int i=1; i<plates.length; i++){

            if(max <= count){
                if(arr[coupon] == 0){
                    max = count+1;
                }else{
                    max = count;
                }

            }

            int p1 = i-1;
            int p2 = (i+n-1)%plates.length;

            arr[plates[p1]]--;
            if(arr[plates[p1]] == 0){
                count--;
            }
            arr[plates[p2]]++;
            if(arr[plates[p2]] == 1){
                count++;
            }
        }
        return max;
    }
}

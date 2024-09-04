package array;

public class Practice4 {
    public static void main(String[] args) {
        int[] arr = {3,1,2,6,2,2,5,1,9,10,1,11};

        int i=0;
        StringBuilder sb = new StringBuilder();
        while(i <= arr.length-1) {
            if(i == 0){
                if(arr[i] > arr[i+1]){
                    sb.append(arr[i]).append(" ");
                    i = i+1;
                }
            }else if(i == arr.length-1){
                if(arr[i] > arr[i-1]){
                    sb.append(arr[i]).append(" ");
                    i = i+1;
                }
            }else{
                if(arr[i] > arr[i-1] && arr[i] > arr[i+1]){
                    sb.append(arr[i]).append(" ");
                    i = i+1;
                }
            }
            i++;
        }
        System.out.println(sb);
    }
}

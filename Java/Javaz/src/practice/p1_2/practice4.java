package practice.p1_2;

public class practice4 {
    public static void main(String[] args) {
        int n = 5;
        int num = 2*n-1;
        int mid = (num+1)/2;
        for(int i=0; i<n; i++){
            for(int j=1; j<mid-i; j++){
                System.out.print(" ");
            }
            for(int j=mid-i; j<=mid+i; j++){
                System.out.print("*");
            }
            System.out.println();
        }

        for(int i=n-2; i>=0; i--){
            for(int j=1; j<mid-i; j++){
                System.out.print(" ");
            }
            for(int j=mid-i; j<=mid+i; j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

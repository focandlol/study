package basicMath.practice1;

public class Practice6 {
    public static void main(String[] args) {
        System.out.println(find(0));
        System.out.println(find(2));
        System.out.println(find(5));
        System.out.println(find(7));
    }

    private static int find(int i) {
        int sum = 0;

        if(i <= 1){
            return 1;
        }

        for(int j=0; j<i; j++){
            sum += find(j) * find(i-j-1);
        }
        return sum;
    }
}

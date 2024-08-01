import java.util.Scanner;

public class BaekJoon11444_kkm {
    static long[][] arr = {{1,1},{1,0}};
    static int mod = 1000000007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();

        long[][] longs = find(arr, n);
        System.out.println(longs[1][0]);
    }

    private static long[][] find(long[][] arr, long n) {
        if(n == 1){
            return arr;
        }

        long[][] a = find(arr, n / 2);

        a = multiple(a,a);

        if(n % 2 == 1){
            a = multiple(arr,a);
        }
        return a;
    }

    private static long[][] multiple(long[][] arr, long[][] arr2) {

        long[][] a = new long[2][2];

        a[0][0] = (arr[0][0] * arr2[0][0] + arr[1][0] * arr2[0][1]) % mod;
        a[1][0] = (arr[0][0] * arr2[1][0] + arr[1][0] * arr2[1][1]) % mod;
        a[0][1] = (arr[0][1] * arr2[0][0] + arr[1][1] * arr2[0][1]) % mod;
        a[1][1] = (arr[0][1] * arr2[1][0] + arr[1][1] * arr2[1][1]) % mod;

        return a;
    }
}

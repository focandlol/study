package Math.p2;

public class Practice4 {
    static int sosu = 4;
    static final int INF = 1000000007;
    public static void main(String[] args) {
        System.out.println(find(1));
        System.out.println(find(2));
        System.out.println(find(3));
        System.out.println(find(4));
        System.out.println(find(50));
    }

    private static long find(int i) {
        if(i == 1) return 5;
        int a = i/2;
        long ja =  recur(5,i-a);

        long so = recur(4,a);

        return ja*so%INF;
    }

    private static long recur(int c,int i) {
        if(i == 1)return c;

        long recur = recur(c,i / 2);

        if(i % 2 == 1){
            return ((recur*recur)%INF)*c%INF;
        }else{
            return recur*recur%INF;
        }
    }
}

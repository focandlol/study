package practice.p1_1;

public class Practice1 {
    public static void main(String[] args) {
        soolution(12345);
        soolution(-12345);
        soolution(100);
        soolution(0);
    }

    private static void soolution(int i) {
        boolean isMinus = false;
        if(i < 0){
            i *= -1;
            isMinus = true;
        }

        int dap = 0;

        while(i > 0){
            int a = i % 10;
             i /= 10;
             dap = dap * 10 + a;
        }

        if(isMinus){
            System.out.println(-dap);
        }else{
            System.out.println(dap);
        }

    }
}

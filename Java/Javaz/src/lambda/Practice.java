package lambda;

interface ComputerTool {
    public abstract int compute(int x,int y);
}

public class Practice{
    public static void main(String[] args) {
        ComputerTool computerTool = new ComputerTool() {

            @Override
            public int compute(int x, int y) {
                return x + y;
            }
        };

        System.out.println(computerTool.compute(10,20));

        ComputerTool computerTool2 = (x,y) -> {return x+y;};
        System.out.println(computerTool2.compute(10,20));

    }
}

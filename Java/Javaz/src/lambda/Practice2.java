package lambda;

interface CompareTool{
    public int compare(int a, int b);
}
public class Practice2 {
    public static void main(String[] args) {

        CompareTool compareTool = new CompareTool() {
            public int compare(int a, int b) {
                return a > b ? a : b;
            }
        };
        System.out.println(compareTool.compare(1, 2));

        CompareTool compareTool2 = (a,b) -> a > b ? a : b;
        System.out.println(compareTool2.compare(1, 2));
    }

}

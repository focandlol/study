package basicMath.practice1;

public class Practice8 {
    public static void main(String[] args) {
       System.out.println(find("x+5-3+x=6+x-2"));
       System.out.println(find("x=x"));
       System.out.println(find("2x=x"));
    }

    private static String find(String s) {
        String[] split = s.split("=");
        int[] r1 = eval(split[0]);
        int[] r2 = eval(split[1]);

        if(r1[0] == r2[0] && r1[1] == r2[1]) {
            return "Infinite solutions";
        }else if(r1[0] == r2[0]) {
            return "No solution";
        }else{
            return "x=" + (r2[1] - r1[1]) / (r1[0] - r2[0]);
        }
    }

    private static int[] eval(String s) {
        String[] split = s.split("(?=[+-])");
        int[] result = new int[2];
        for(int i=0; i<split.length; i++) {
            if(split[i].equals("+x") || split[i].equals("x")) {
                result[0]++;
            }else if(split[i].equals("-x")) {
                result[0]--;
            }else if(split[i].contains("x")) {
                result[0] += Integer.parseInt(split[i].substring(0,split[i].length()-1));
            }else{
                result[1] += Integer.parseInt(split[i]);
            }
        }
        return result;
    }
}

package Math;

import java.util.ArrayList;

public class Practice1 {
    public static void main(String[] args) {
        System.out.println(solution(1));
        System.out.println(solution(2));
        System.out.println(solution(3));
        System.out.println(solution(4));
        System.out.println(solution(5));
    }

    private static ArrayList<ArrayList<Integer>> solution(int num) {
        ArrayList<ArrayList<Integer>> dap = new ArrayList<>();
        for(int i=0; i<num; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for(int j=0; j<i+1; j++) {
                if(j == 0 || j == i){
                    list.add(1);
                }else{
                    list.add(dap.get(i-1).get(j-1) + dap.get(i-1).get(j));
                }
            }
            dap.add(list);
        }
        return dap;
    }
}

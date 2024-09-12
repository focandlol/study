package practice.p2_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Practice3 {
    public static void main(String[] args) {
        System.out.println(solution("aba","L B"));
        System.out.println(solution("abcd","P x L P y"));
        System.out.println(solution("abc","L L L P x L B P y"));
        System.out.println(solution("a","B B L L D D P a P b P c"));

    }

    private static String solution(String s, String cmd) {
        String[] split = cmd.split(" ");
        List<String> list = new ArrayList<String>(Arrays.asList(s.split("")));
        int cursor = s.length();
        for (int i=0; i<split.length; i++) {
            if(split[i].equals("L") && cursor != 0){
                cursor--;
            }else if(split[i].equals("D") && cursor != list.size()){
                cursor++;
            }else if(split[i].equals("B") && cursor != 0){
                list.remove(cursor-1);
                cursor--;
            }else if(split[i].equals("P")){
                String a = split[i+1];
                list.add(cursor++,a);
            }
        }
        return String.join("",list);
    }
}

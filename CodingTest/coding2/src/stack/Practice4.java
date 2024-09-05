package stack;

import java.util.Stack;

public class Practice4 {
    public static void main(String[] args) {
        String s1 = "tree";
        String s2 = "th#ree";
        System.out.println(stringCompare(s1,s2));

        s1 = "ab#a";
        s2 = "aab#";
        System.out.println(stringCompare(s1,s2));

        s1 = "wo#w";
        s2 = "ww#o";
        System.out.println(stringCompare(s1,s2));
    }

    private static boolean stringCompare(String s1, String s2) {
        String a = find(s1);
        String b = find(s2);
        return a.equals(b);
    }

    private static String find(String s1) {
        Stack<String> stack = new Stack();
        String[] split = s1.split("");
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals("#")) {
                if (!stack.empty()) {
                    stack.pop();
                }
            }else{
                stack.push(split[i]);
            }
        }

//        StringBuffer sb = new StringBuffer();
//        for (String s : stack) {
//            sb.append(s);
//        }
//        return sb.toString();
        return String.valueOf(stack);
    }
}

package basic.structure1;

import java.util.Scanner;
import java.util.Stack;

public class Boj17413 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length(); i++) {
            if(!stack.isEmpty() && stack.peek() == '<'){
                if(s.charAt(i) == '>') stack.pop();
                sb.append(s.charAt(i));
                continue;
            }

            if(s.charAt(i) == ' '){
                while(!stack.isEmpty()){
                    sb.append(stack.pop());
                }
                sb.append(" ");
                continue;
            }

            if(s.charAt(i) == '<'){
                while(!stack.isEmpty()){
                    sb.append(stack.pop());
                }
                sb.append(s.charAt(i));
            }

            stack.push(s.charAt(i));
        }

        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        System.out.println(sb);
    }
}

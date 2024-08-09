package stack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BaekJoon9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String bomb = br.readLine();

        Stack<Character> stack = new Stack<>();

        for(int i=0; i<s.length(); i++){

            stack.push(s.charAt(i));
            if(stack.size() >= bomb.length()){
                boolean flag = true;
                for(int j=0; j<bomb.length(); j++){
                    if(stack.get(stack.size() - bomb.length() + j) != bomb.charAt(j)){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    for(int j=0; j<bomb.length(); j++){
                        stack.pop();
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        if(stack.size() == 0){
            System.out.println("FRULA");
        }else {
            for (int i = 0; i < stack.size(); i++) {
                sb.append(stack.get(i));
            }
            System.out.println(sb);
        }
    }
}


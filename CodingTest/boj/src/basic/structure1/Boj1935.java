package basic.structure1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Boj1935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String s = br.readLine();
        Stack<Double> stack = new Stack<Double>();
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(c == '+' || c == '-' || c == '*' || c == '/') {
                if(c == '+') stack.push(stack.pop() + stack.pop());
                else if(c == '-') stack.push(- stack.pop() + stack.pop());
                else if(c == '*') stack.push(stack.pop() * stack.pop());
                else if(c == '/') {
                    double a  = stack.pop();
                    double b = stack.pop();
                    stack.push(b/a);
                }
            }else{
                stack.push((double) arr[c - 'A']);
            }
        }

        System.out.printf("%.2f",stack.pop());
    }
}

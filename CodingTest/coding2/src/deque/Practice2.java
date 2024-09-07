package deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class Practice2 {
    public static void main(String[] args) {
        System.out.println(checkPalindrome("a"));
        System.out.println(checkPalindrome("aba"));
        System.out.println(checkPalindrome("abba"));
        System.out.println(checkPalindrome("ababba"));
    }

    private static boolean checkPalindrome(String a) {
        Deque<Character> deque = new ArrayDeque();

        for(int i = 0; i < a.length(); i++) {
            deque.addLast(a.charAt(i));
        }

        boolean isPalindrome = true;
        while(!deque.isEmpty()) {
            Character c = deque.pollFirst();
            Character c1 = deque.pollLast();

            if(!c.equals(c1) && c!= null && c1!=null) {
                isPalindrome = false;
                break;
            }
        }
        return isPalindrome;
    }
}

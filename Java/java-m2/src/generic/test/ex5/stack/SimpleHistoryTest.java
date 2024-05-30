package generic.test.ex5.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimpleHistoryTest {

    public static void main(String[] args) {
        Deque<String> stack = new ArrayDeque<>();
        // 코드 작성

        stack.push("youtube.com");
        stack.push("google.com");
        stack.push("facebook.com");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}

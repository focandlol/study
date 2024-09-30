package pre.hint;

public class Programmers120908 {
    static class Solution {
        static int solution(String str1, String str2) {
            return str1.contains(str2) ? 1 : 2;
        }
    }
    public static void main(String[] args) {
        String str1 = "ab6CDE443fgh22iJKlmn1o";
        String str2 = "6CD";
        System.out.println(Solution.solution(str1, str2));
    }
}

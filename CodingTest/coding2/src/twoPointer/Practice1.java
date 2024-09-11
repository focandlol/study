package twoPointer;

public class Practice1 {
    public static void main(String[] args) {
        System.out.println(solution("ab"));
        System.out.println(solution("aaabbaa"));
        System.out.println(solution("aabcddba"));
        System.out.println(solution("bbabb"));
        System.out.println(solution("ababb"));
    }

    private static String solution(String s) {
        int left = 0;
        int right = s.length() -1;

        while(left < right){
            char leftGap = s.charAt(left);
            char rightGap = s.charAt(right);
            if(leftGap == rightGap){
                left++;
                right--;
                while(leftGap == s.charAt(left)){
                    left++;
                }
                while(rightGap == s.charAt(right)){
                    right--;
                }
            }else{
                break;
            }
        }
        if(left > right){
            return "";
        }else{
            return s.substring(left, right+1);
        }
    }
}

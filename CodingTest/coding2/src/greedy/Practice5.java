package greedy;

public class Practice5 {
    public static void main(String[] args) {
        System.out.println(solution(2736));
    }

    private static int solution(int num) {
        char[] charArray = String.valueOf(num).toCharArray();
        int[] maxArr = new int[charArray.length];
        int max = 0;
        for(int i=charArray.length-1; i>=0; i--) {
            max = Math.max(max, charArray[i] - '0');
            maxArr[i] = max;
        }

        for(int i=0; i<charArray.length-1; i++) {
            if(charArray[i] - '0' < maxArr[i+1]) {
                for(int j = charArray.length-1; j>=1; j--) {
                    if(maxArr[i+1] == charArray[j] - '0') {
                        char temp = charArray[i];
                        charArray[i] = charArray[j];
                        charArray[j] = temp;
                        return Integer.parseInt(String.valueOf(charArray));
                    }
                }
            }

        }
        return num;
    }
}

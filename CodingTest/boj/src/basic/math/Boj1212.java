package basic.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1212 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();  // 8진수 입력
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            // 각 8진수 자리수를 2진수로 변환하고, 3자리로 맞춘 후 추가
            String binary = Integer.toBinaryString(s.charAt(i) - '0'); // 문자 -> 정수 변환
            if (i > 0) {
                // 첫 번째 자리 이후에는 0을 붙여서 3자리로 맞춤
                while (binary.length() < 3) {
                    binary = "0" + binary;
                }
            }
            sb.append(binary);
        }

        // 결과 출력
        // 문자열이 0으로 시작하는 경우(입력이 '0'인 경우 제외) 제거
        if (sb.length() > 0 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        System.out.println(sb.length() == 0 ? "0" : sb.toString());
    }
}

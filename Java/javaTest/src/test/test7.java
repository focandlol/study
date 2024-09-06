package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * 고경민
 */
public class test7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("[로또 당첨 프로그램]");
        System.out.println();

        System.out.print("로또 개수를 입력해 주세요.(숫자 1~10):");
        int num = sc.nextInt();
        HashMap<Character, int[]> map = new HashMap<>();

        sc.close();

        char start = 'A';
        Random r = new Random();
        for (int i = 0; i < num; i++) {
            int[] arr = create(r);
            map.put(start, arr);
            System.out.printf("%c\t%s\n", start, print(arr));
            start++;
        }
        System.out.println();

        int[] dap;
        System.out.println("[로또 발표]");
        dap = create(r);
        System.out.printf("\t%s\n", print(dap));
        System.out.println();

        System.out.println("[내 로또 결과]");
        printResult(map, dap);

    }


    static int[] create(Random random) {
        int[] arr = new int[6];
        for (int i = 0; i < 6; i++) {
            arr[i] = random.nextInt(45) + 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] == arr[j]) {
                    i--;
                    break;
                }
            }
        }
        Arrays.sort(arr);
        return arr;
    }

    static String print(int[] arr) {
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            if (i != 0) {
                sf.append(",");
            }
            if (arr[i] < 10) {
                sf.append("0" + arr[i]);
            } else {
                sf.append(arr[i]);
            }
        }
        return sf.toString();
    }

    static void printResult(HashMap<Character, int[]> map, int[] dap) {
        char al = 'A';
        for (int i = 0; i < map.size(); i++) {
            int[] arr = map.get(al);
            int count = 0;
            for (int j = 0; j < dap.length; j++) {
                int start = 0;
                int end = arr.length - 1;
                while (start <= end) {
                    int mid = (start + end) / 2;
                    if (arr[mid] == dap[j]) {
                        count++;
                        break;
                    } else if (dap[j] < arr[mid]) {
                        end = mid - 1;
                    } else if (dap[j] > arr[mid]) {
                        start = mid + 1;
                    }
                }
            }
            System.out.printf("%c\t%s => %d개 일치\n", al, print(arr), count);
            al++;
        }
    }
}

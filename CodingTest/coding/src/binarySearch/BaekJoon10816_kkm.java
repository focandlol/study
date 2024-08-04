package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon10816_kkm {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int[] have = new int[m];

        Arrays.sort(arr);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int a = Integer.parseInt(st.nextToken());
            //System.out.println(findEnd(a) + " " +  findStart(a));
           sb.append(findEnd(a) - findStart(a)).append(" ");
        }

        System.out.println(sb);



    }

    private static int findStart(int a) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int middle = (left + right) / 2;
            if (a <= arr[middle]) {
                right = middle;
            }
            else {
                left = middle + 1;
            }
        }
        return left;
    }

    private static int findEnd(int a) {
        int start = 0;
        int end = arr.length;

        while (start < end) {
            int mid = (start + end) / 2;
            if (a >= arr[mid]) {
                start = mid + 1;
            }
            else {
                end = mid;
            }
        }
        return start;
    }
}

package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BaekJoon5639 {
    static ArrayList<Integer> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while (true) {
            input = br.readLine();
            if (input == null || input.equals("")) break;
            list.add(Integer.parseInt(input));
        }

        post(0,list.size()-1);
        System.out.println(sb);
    }

    private static void post(int start, int end) {
        if(start>end) return;
        int pos = start+1;
        while(pos <= end && list.get(pos) < list.get(start)){
            pos++;
        }

        post(start + 1, pos - 1);
        post(pos, end);

        sb.append(list.get(start)).append("\n");
    }
}

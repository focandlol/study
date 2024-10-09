package basic.structure1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Boj11656 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();
        String s = br.readLine();
        StringBuilder sb = new StringBuilder(s);

        while (sb.length() > 0) {
            list.add(sb.toString());
            sb.delete(0, 1);
        }

        sb= new StringBuilder();
        Collections.sort(list);
        for (String string : list) {
            sb.append(string).append("\n");
        }
        System.out.println(sb);
    }
}

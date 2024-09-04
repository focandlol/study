package test;

import java.util.Arrays;

public class sk {
    public static void main(String[] args) {
        String[] a = new String[3];

        for(int i=0; i<a.length; i++){
            a[i] = "a\ta\ta\ta\ta\t";
        }
        System.out.println("a\ta\ta\ta\ta\t");
        System.out.println(Arrays.toString(a));
        String[] split = a[1].split("");
        System.out.println(split.length);
        for(int i=0; i<split.length; i++){
            System.out.println(split[i]);
        }

        StringBuilder b = new StringBuilder();
        b.append("[ ");
        for (int i = 0; i<3; i++) {
            b.append(a[i]);
            System.out.println(String.valueOf(a[i]));
            b.append(" ");
        }
        b.append("]");
        System.out.println(b.toString());
    }
}

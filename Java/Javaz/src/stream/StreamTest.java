package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice","Bob","Charlie","David","Edward");

        List<String> filterNames = names.stream().filter(name -> !name.startsWith("C"))
                .collect(Collectors.toList());

        System.out.println(filterNames);

        List<Integer> a = Arrays.asList(-10,40,110,-140);

        Integer i = a.stream().max((x, y) -> Math.abs(x) - Math.abs(y)).orElse(0);
        System.out.println(i);
    }
}

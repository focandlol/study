package generic.test.ex5.map;

import java.util.HashMap;
import java.util.Map;

public class WordFrequencyTest1 {

    public static void main(String[] args) {
        String text = "orange banana apple apple banana apple";
        // 코드 작성

        Map<String, Integer> map = new HashMap<String, Integer>();

        String[] s = text.split(" ");
        for (String word : s) {
            if(map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            }else {
                map.put(word, 1);
            }
        }
        System.out.println("map = " + map);
    }
}

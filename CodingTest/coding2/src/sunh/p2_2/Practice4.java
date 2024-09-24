package sunh.p2_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Practice4 {
    public static void main(String[] args) {

        String[] participant = {"leo","kiki","eden"};
        String[] completion = {"eden","kiki"};
        System.out.println(solution(participant,completion));

        participant = new String[]{"marina","josipa","nikola","vinko","filipa"};
        completion = new String[]{"marina","josipa","nikola","filipa"};
        System.out.println(solution(participant,completion));

        participant = new String[]{"mislav","stanko","mislav","ana"};
        completion = new String[]{"stanko","mislav","ana"};
        System.out.println(solution(participant,completion));
    }

    private static String solution(String[] participant, String[] completion) {
        HashMap<String,Integer> map = new HashMap<>();

        for(int i=0;i<participant.length;i++){
            if(map.containsKey(participant[i])){
                map.replace(participant[i],map.get(participant[i])+1);
            }else{
                map.put(participant[i],1);
            }
        }

        for(int i=0;i<completion.length;i++){
            map.replace(completion[i],map.get(completion[i])-1);
        }

        ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
        entries.sort(Map.Entry.comparingByValue((x,y) -> y-x));

        return entries.get(0).getKey();
    }
}

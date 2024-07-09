import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class BaekJoon2108 {
    static HashMap<Integer,Integer> map = new HashMap<>();
    static int n;
    static StringBuilder sb = new StringBuilder();
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        for(int i=0; i<n; i++) {
            int a = Integer.parseInt(br.readLine());
            if(!map.containsKey(a)) {
                map.put(a, 1);
            }else{
                map.put(a, map.get(a)+1);
            }
            arr[i] = a;
        }
        Arrays.sort(arr);
        average();
        mid();
        many();
        scope();

        System.out.println(sb);


    }

    private static void scope() {
        if(n == 1){
            sb.append(0).append("\n");
        }else{
            sb.append(arr[n-1] - arr[0]).append("\n");
        }
    }

    private static void many() {
        ArrayList<Integer> list = new ArrayList<>(map.values());
        list.sort(Integer::compareTo);
        ArrayList<Integer> keyList = new ArrayList<>(map.keySet());
        keyList.sort(Integer::compareTo);
        int max = list.get(map.size()-1);
        int count = 0;
        if(map.size() == 1){
            sb.append(arr[0]).append("\n");
            return;
        }
        if(list.get(map.size()-1) != list.get(map.size()-2)) {
            for (Integer i : map.keySet()) {
                if(map.get(i) == max){
                    sb.append(i).append("\n");
                    break;
                }
            }
        }else{
            for (Integer i : keyList) {
                if(map.get(i) == max){
                    count++;
                }
                if(count == 2){
                    sb.append(i).append("\n");
                    break;
                }
            }
        }
    }

    private static void mid() {
        sb.append(arr[(n-1)/2]).append("\n");
    }

    private static void average() {
        double count = 0;
        for (Integer i : map.keySet()) {
            if(map.get(i) > 1){
                count+=i*map.get(i);
            }else{
                count += i;
            }
        }
        sb.append(Math.round(count/n)).append("\n");

    }
}

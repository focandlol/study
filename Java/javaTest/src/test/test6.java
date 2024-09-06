package test;

import java.util.*;

/**
 * 고경민
 */
public class test6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("총 진행할 투표수를 입력해 주세요.");
        int total = sc.nextInt();
        System.out.print("가상 선거를 진행할 후보자 인원을 입력해 주세요.");
        int num = sc.nextInt();

        HashMap<Integer, Pe> map = new HashMap<>();
        for (int i = 1; i <= num; i++) {
            System.out.print(i + "번째 후보자이름을 입력해 주세요.");
            map.put(i, new Pe(sc.next()));
        }
        System.out.println();

        Random rand = new Random();
        vote(total, rand, map);
        result(map);


    }

    static void vote(int total, Random rand, HashMap<Integer, Pe> map) {
        for (int i = 1; i <= total; i++) {
            int r = rand.nextInt(map.size()) + 1;
            map.get(r).count++;
            print(i, r, total, map);
        }
    }

    static void print(int i, int r, int total, HashMap<Integer, Pe> map) {
        System.out.printf("[투표진행률]: %.2f%%, %d명 투표 => %s\n", (double) i * 100 / total, i, map.get(r).name);
        for (int j = 1; j <= map.size(); j++) {
            System.out.printf("[기호:%d] %-5s \t %8.2f%% \t (투표수: %d)\n", j, map.get(j).name+":",
                    (double) map.get(j).count * 100.0 / total, map.get(j).count);
        }
        System.out.println();
    }

    static void result(HashMap<Integer, Pe> map) {
        ArrayList<Integer> list = new ArrayList<>(map.keySet());
        list.sort((a, b) -> map.get(b).count - map.get(a).count);

        if (map.get(list.get(0)).count == map.get(list.get(1)).count) {
            System.out.print("[투표결과] 동률 : ");
            for (int i = 0; i < list.size(); i++) {
                if(i != 0) {
                    System.out.print(",");
                }
                if (map.get(list.get(0)).count == map.get(list.get(i)).count) {
                    System.out.print(map.get(list.get(i)).name);
                } else {
                    break;
                }
            }
        } else {
            System.out.printf("[투표결과] 당선인 : %s", map.get(list.get(0)).name);
        }
    }


    static class Pe {
        String name;
        int count;

        public Pe(String name) {
            this.name = name;
        }
    }
}
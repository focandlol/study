package sunh.p2_1;

import java.util.*;

public class Practice3 {
    public static void main(String[] args) {
        String[] genres = {"classic","pop","classic","classic","pop"};
        int[] plays = {500,600,150,800,2500};
        solution(genres,plays);
    }

    private static void solution(String[] genres, int[] plays) {
        HashMap<String, ArrayList<Song>> map = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            if (map.containsKey(genres[i])) {
                ArrayList<Song> cur = map.get(genres[i]);

                boolean flag = false;
                for(int j = 0; j < cur.size(); j++) {
                    if(cur.get(j).play < plays[i] || (cur.get(j).play == plays[i] && cur.get(j).no > i)){
                        cur.add(0,new Song(i,plays[i]));
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    cur.add(new Song(i,plays[i]));
                }
                map.put(genres[i], cur);
            }else{
                Song song = new Song(i,plays[i]);
                map.put(genres[i],new ArrayList<>(Arrays.asList(song)));
            }
        }

        HashMap<String,Integer> sumMap = new HashMap<>();

        for (String s : map.keySet()) {
            int sum = 0;
            ArrayList<Song> songs = map.get(s);
            for (Song song : songs) {
                sum += song.play;
            }
            sumMap.put(s, sum);
        }

        ArrayList<Map.Entry<String, Integer>> sort = new ArrayList<>(sumMap.entrySet());
        sort.sort(Map.Entry.comparingByValue((x,y) -> y-x));

        for (Map.Entry<String, Integer> a : sort) {
            ArrayList<Song> songs = map.get(a.getKey());
            for(int i=0; i<songs.size(); i++) {
                System.out.print(songs.get(i).no+" ");
                if(i == 1){
                    break;
                }
            }
        }

    }

    static class Song{
        int no;
        int play;

        public Song(int no, int play) {
            this.no = no;
            this.play = play;
        }
    }
}

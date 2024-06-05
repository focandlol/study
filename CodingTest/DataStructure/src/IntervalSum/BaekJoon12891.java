package IntervalSum;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BaekJoon12891 {
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<String> compare = new ArrayList<>();
        long length = Long.parseLong(st.nextToken());
        long pwLength = Long.parseLong(st.nextToken());
        int[] aaa = new int[4];
        long count = 0;
        String[] split = br.readLine().split("");
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++){
            aaa[i] = Integer.parseInt(st.nextToken());
        }


        int start = 0;
        int end = 0;
        while(end < length){
            if(end - start + 1 < pwLength){
                end += 1;
            }
            else if(end - start + 1 == pwLength){
                int aCount = 0;
                int cCount = 0;
                int gCount = 0;
                int tCount = 0;
                String com = "";
                for(int i=start; i<=end; i++){
                    if(split[i].equals("A")){
                        aCount+=1;
                    }else if(split[i].equals("C")){
                        cCount+=1;
                    }else if(split[i].equals("G")){
                        gCount+=1;
                    }else{
                        tCount+=1;
                    }
                    com = com.concat(split[i]);
                }
                if(aCount >= aaa[0] && cCount >= aaa[1] && gCount >= aaa[2] && tCount >= aaa[3]){
                    boolean wi = false;
                    for(int i=0; i<compare.size(); i++){
                        if(compare.get(i).equals(com)){
                            wi = true;
                            break;
                        }
                    }
                    if(wi == false){
                        count+=1;
                        compare.add(com);
                    }
                }
                start = end;
            }
            else if(end - start + 1 > pwLength){
                for(int i=0; i<compare.size(); i++){
                    if(compare.get(i).equals(split)){}
                }
                start += 1;
            }
        }
        System.out.println(count);

    }
}

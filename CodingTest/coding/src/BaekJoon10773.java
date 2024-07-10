import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class BaekJoon10773 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for(int i=0; i<k; i++){
            int a = Integer.parseInt(br.readLine());
            if(a == 0){
                deque.pop();
            }else{
                deque.push(a);
            }
        }

        int count = 0;
        int size = deque.size();
        for(int i=0; i<size; i++){
            count += deque.pop();
        }

        System.out.println(count);
    }
}

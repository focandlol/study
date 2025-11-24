```
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Double> score = new ArrayList<>();
        double sum = 0;
        
        long next = sc.nextInt();
        for(int i=0; i<next; i++) {
            score.add(sc.nextDouble());
        }
        
        Double max = score.stream().mapToDouble(a -> a).max().getAsDouble();
        
        for (Double v : score) {
            sum += v/max*100;
        }

        System.out.println(sum/score.size());
    }
}
```

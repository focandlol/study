package mission1;

import java.util.*;

/**
 * 고경민
 */
public class Mission2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<double[]> list = new ArrayList<>();
        double distance = 0;
        System.out.println("내 좌표 x값을 입력해 주세요.");
        int mx = sc.nextInt();
        System.out.println("내 좌표 y값을 입력해 주세요.");
        int my = sc.nextInt();

        boolean flag = true;
        for(int i=1; i<=10; i++) {
            System.out.println(i+"/10 번째 입력");
            System.out.println("임의의 좌표 x값을 입력해 주세요.");
            int x = sc.nextInt();
            System.out.println("임의의 좌표 y값을 입력해 주세요.");
            int y = sc.nextInt();

            for(int j=1; j<list.size(); j++) {
                if (list.get(j)[0] == x && list.get(j)[1] == y) {
                    System.out.println("동일한 좌표값이 이미 존재합니다. 다시 압혁해 주세요.");
                    i--;
                    flag = false;
                    break;
                }
            }
            if(flag) {
                distance = Math.sqrt(Math.pow(mx - x, 2) + Math.pow(my - y, 2));
                list.add(new double[]{x, y, distance});
            }
            flag = true;
        }

        int min = 0;
        for (int i=0; i<list.size(); i++) {
            double[] arr = list.get(i);
            if(list.get(min)[2] > arr[2]) min = i;
            System.out.printf("(%d, %d) => %.6f\n", (int)arr[0], (int)arr[1], arr[2]);
        }
        System.out.println("제일 가까운 좌표:");
        System.out.printf("(%d, %d) => %.6f", (int)list.get(min)[0], (int)list.get(min)[1],list.get(min)[2]);
    }
}
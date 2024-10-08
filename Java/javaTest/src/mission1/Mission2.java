package mission1;

import java.util.*;

public class Mission2 {
    static class Dis{
        int x;
        int y;
        double distance;

        public Dis(int x, int y, double distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Dis dis = (Dis) o;
            return x == dis.x && y == dis.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Dis> list = new ArrayList<>();
        double distance = 0;
        System.out.println("내 좌표 x값을 입력해 주세요.");
        int mx = sc.nextInt();
        System.out.println("내 좌표 y값을 입력해 주세요.");
        int my = sc.nextInt();

        for(int i=1; i<=10; i++) {
            System.out.println(i+"/10 번째 입력");
            System.out.println("임의의 좌표 x값을 입력해 주세요.");
            int x = sc.nextInt();
            System.out.println("임의의 좌표 y값을 입력해 주세요.");
            int y = sc.nextInt();

            distance = Math.sqrt(Math.pow(mx-x,2) + Math.pow(my-y,2));
            if(list.contains(new Dis(x,y,distance))) {
                System.out.println("동일한 좌표값이 이미 존재합니다. 다시 압혁해 주세요.");
                i--;
                continue;
            }
            list.add(new Dis(x,y,distance));
        }

        int min = 0;
        for (int i=0; i<list.size(); i++) {
            Dis dis = list.get(i);
            if(list.get(min).distance > dis.distance) min = i;
            System.out.printf("(%d, %d) => %.6f\n", dis.x, dis.y, dis.distance);
        }
        System.out.println("제일 가까운 좌표:");
        System.out.printf("(%d, %d) => %.6f", (int)list.get(min).x, (int)list.get(min).y,list.get(min).distance);

    }
}

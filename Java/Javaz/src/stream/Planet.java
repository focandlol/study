package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class SpaceObject_T {
    String type;
    int dist;

    SpaceObject_T(String type, int dist) {
        this.type = type;
        this.dist = dist;
    }
}

public class Planet {
    public static void solution(List<SpaceObject_T> arr) {
        // 풀이 작성
        List<SpaceObject_T> planet = arr.stream().filter(a -> a.type.equals("planet"))
                .sorted((x,y) -> Math.abs(x.dist) - Math.abs(y.dist))
                .limit(3)
                .collect(Collectors.toList());

        for (SpaceObject_T spaceObjectT : planet) {
            System.out.println(spaceObjectT.dist);
        }

    }

    public static void main(String[] args) {
        List<SpaceObject_T> arr = new ArrayList<>();
        arr.add(new SpaceObject_T("planet", 10));
        arr.add(new SpaceObject_T("star", -3));
        arr.add(new SpaceObject_T("milkyway", 2));
        arr.add(new SpaceObject_T("planet", 5));
        arr.add(new SpaceObject_T("planet", -7));
        arr.add(new SpaceObject_T("star", 4));
        arr.add(new SpaceObject_T("planet", 6));
        arr.add(new SpaceObject_T("planet", -1));
        arr.add(new SpaceObject_T("star", 8));
        arr.add(new SpaceObject_T("milkyway", -9));

        solution(arr);

    }
}

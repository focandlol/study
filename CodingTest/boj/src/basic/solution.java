package basic;

import java.util.*;

public class solution {
    public static void main(String[] args) {
        solution(new int[][]{{1, 1}, {1, 2}, {1, 3}}, new int[][]{{2,1}, {2,3}});
    }
    static int solution(int[][] points, int[][] routes) {
        int count = 0;
        Map<String, Integer> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        for (int i = 0; i < routes.length; i++) {
            int x = points[routes[i][0] - 1][0];
            int y = points[routes[i][0] - 1][1];
            System.out.println(x + " " + y);
            queue.add(new Node(x, y, i, 1,0));
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            map.clear();

            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                StringBuilder sb = new StringBuilder();
                sb.append(cur.x);
                sb.append(cur.y);
                sb.append(cur.sequence);
                map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);

                if (map.get(sb.toString()) == 2) count++;



                int nextX = cur.x, nextY = cur.y;
                int nextK = cur.k;



                int targetX = points[routes[cur.robot][cur.k] - 1][0];
                int targetY = points[routes[cur.robot][cur.k] - 1][1];

                if (cur.x != targetX) {
                    nextX += (targetX > cur.x) ? 1 : -1;
                } else if (cur.y != targetY) {
                    nextY += (targetY > cur.y) ? 1 : -1;
                } else {
                    nextK = cur.k + 1;
                    if(nextK < routes[cur.robot].length){
                        targetX = points[routes[cur.robot][nextK] - 1][0];
                        targetY = points[routes[cur.robot][nextK] - 1][1];
                        if (cur.x != targetX) {
                            nextX += (targetX > cur.x) ? 1 : -1;
                        } else if (cur.y != targetY) {
                            nextY += (targetY > cur.y) ? 1 : -1;
                        }
                    }
                }

                //System.out.println("x = " + nextX + "y = " + nextY + "sequence = " + cur.sequence + 1);
                if (nextK < routes[cur.robot].length) {
                    queue.add(new Node(nextX, nextY, cur.robot, nextK,cur.sequence + 1));
                }
            }


        }

        System.out.println(count);
        return -1;
    }

    static class Node {
        int x, y, robot, k,sequence;

        public Node(int x, int y, int robot, int k,int sequence) {
            this.x = x;
            this.y = y;
            this.robot = robot;
            this.k = k;
            this.sequence = sequence;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y && sequence == node.sequence;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, sequence);
        }
    }
}

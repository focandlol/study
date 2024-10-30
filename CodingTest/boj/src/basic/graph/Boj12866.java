package basic.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj12866 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        boolean[][] visited = new boolean[1001][1001];
        int sum = A+B+C;

        // 돌 3개가 3으로 나누어지지 않으면 모두 같은 수가 될 수 없으니 0을 출력한다.
        if(sum % 3 != 0) {
            System.out.println(0);
            System.exit(0);
        }

        Queue<Group> q = new LinkedList<>();

        // 2개의 돌의 경우를 Queue에 넣고 시작(나머지 하나는 sum에서 빼면 된다.)
        q.offer(new Group(A, B));
        visited[A][B] = true;
        visited[B][A] = true;

        boolean flag = false;
        while(!q.isEmpty()){
            Group g = q.poll();
            int a = g.A;
            int b = g.B;
            int c = sum - a - b;

            // 모두 같아진 경우 멈춘다.
            if(a == b && b == c) {
                flag = true; break;
            }

            // a, b, c가 각각 다른 경우 모든 경우에 대해서 개수를 조정하고 다음 탐색을 위해 Queue에 넣는다.
            if(a != b){
                // 더 큰 수를 - 연산 하고 작은 수를 2배한다.
                int na = a > b ? a-b : 2*a;
                int nb = b > a ? b-a : 2*b;
                if(na <= 1000 && nb <= 1000 && !visited[na][nb]){
                    visited[na][nb] = true;
                    visited[nb][na] = true;
                    q.offer(new Group(na, nb));
                }
            }

            if(a != c){
                int na = a > c ? a-c : 2*a;
                int nc = c > a ? c-a : 2*c;
                if(na <= 1000 && nc <= 1000 && !visited[na][nc]) {
                    visited[na][nc] = true;
                    visited[nc][na] = true;
                    q.offer(new Group(na, nc));
                }
            }

            if(b != c){
                int nb = b > c ? b-c : 2*b;
                int nc = c > b ? c-b : 2*c;
                if(nb <= 1000 && nc <= 1000 && !visited[nb][nc]) {
                    visited[nb][nc] = true;
                    visited[nc][nb] = true;
                    q.offer(new Group(nb, nc));
                }
            }
        }


        System.out.println(flag ? 1 : 0);
        br.close();
    }
}
class Group{
    int A;
    int B;
    public Group(int A, int B){
        this.A=A;
        this.B=B;
    }
}

package sunh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Practice5 {
    public static void main(String[] args) {
        int n = 6;
        int k = 3;
        int l = 3;

        ArrayList<ArrayList> apples = new ArrayList<>();

        apples.add(new ArrayList<>(Arrays.asList(3,4)));
        apples.add(new ArrayList<>(Arrays.asList(2,5)));
        apples.add(new ArrayList<>(Arrays.asList(5,3)));

        Queue<ArrayList> moves = new LinkedList<>();
        moves.add(new ArrayList(Arrays.asList(3,'D')));
        moves.add(new ArrayList(Arrays.asList(15,'L')));
        moves.add(new ArrayList(Arrays.asList(17,'D')));
        System.out.println(solution(n,k,l,apples,moves));

        apples.clear();
        moves.clear();
        n = 10;
        k = 4;
        l = 4;
        apples.add(new ArrayList<>(Arrays.asList(1,2)));
        apples.add(new ArrayList<>(Arrays.asList(1,3)));
        apples.add(new ArrayList<>(Arrays.asList(1,4)));
        apples.add(new ArrayList<>(Arrays.asList(1,5)));

        moves.add(new ArrayList(Arrays.asList(8,'D')));
        moves.add(new ArrayList(Arrays.asList(10,'D')));
        moves.add(new ArrayList(Arrays.asList(11,'D')));
        moves.add(new ArrayList(Arrays.asList(13,'L')));
        System.out.println(solution(n,k,l,apples,moves));

        //System.out.println(-1 % 4);
    }

    private static Integer solution(int n, int k, int l, ArrayList<ArrayList> apples, Queue<ArrayList> moves) {
        int[][] arr = new int[n+1][n+1];
        for (ArrayList apple : apples) {
            arr[(int) apple.get(0)][(int) apple.get(1)] = 1;
        }

        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};
        int dir = 0;
        int dap = 0;
        int x = 1;
        int y = 1;
        Queue<ArrayList<Integer>> snake = new LinkedList<>();
        snake.add(new ArrayList(Arrays.asList(1,1)));

        while(true){
            dap++;

            x = x + dx[dir];
            y = y + dy[dir];

            if(x >= 1 && y >= 1 && x <= n && y <=n){
                ArrayList<Integer> cur = new ArrayList(Arrays.asList(x,y));
                if(snake.contains(cur)){
                    return dap;
                }
                snake.add(cur);

                if(arr[x][y] == 1){
                    arr[x][y] = 0;
                }else if(arr[x][y] == 0){
                    snake.poll();
                }
                if(moves.size() > 0 && (int)moves.peek().get(0) == dap){
                    if((char)moves.peek().get(1) == 'D'){
                        dir = (dir + 1) % dx.length;
                    }else if((char)moves.peek().get(1) == 'L'){
                        dir = (dir -1 + dx.length) % dx.length;
                    }
                    moves.poll();
                    //System.out.println(dir);
                }

            }else{
                return dap;
            }

        }
    }
}

package Math;

public class Practice5 {
    public static void main(String[] args) {
        int[][] grid = {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
        System.out.println(solution(grid));
    }

    private static int solution(int[][] grid) {
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};

        int count = 0;

        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                if(grid[i][j]==1) {
                    for(int k=0; k<4; k++) {
                        int x = i+dx[k];
                        int y = j+dy[k];
                        if(x < 0 || x >=grid.length || y <0 || y >= grid[0].length || grid[x][y]==0) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}

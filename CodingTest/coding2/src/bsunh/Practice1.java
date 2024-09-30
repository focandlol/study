package bsunh;

public class Practice1 {
    static boolean isFlag = false;
    static int[] dx = {0,-1,0,1};
    static int[] dy = {1,0,-1,0};
    public static void main(String[] args) {
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        System.out.println(solution(board,"ABCCED"));
        isFlag = false;
        board = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        System.out.println(solution(board,"SEE"));
        isFlag = false;
        board = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        System.out.println(solution(board,"ABCB"));
    }

    private static boolean solution(char[][] board, String word) {
        int[][] visited = new int[board.length][board[0].length];
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                dfs(board,i,j,0,word,visited);
                if(isFlag) return true;
            }
        }
        return false;
    }

    private static void dfs(char[][] board, int x, int y, int i, String word, int[][] visited) {


        if(i == word.length()) {
            isFlag = true;
           return;
        }

        if(board[x][y] != word.charAt(i)) {
            return;
        }

        visited[x][y] = 1;
        System.out.print(board[x][y] + " ");

        for(int k=0; k<4; k++){
            int xx = x + dx[k];
            int yy = y + dy[k];

            if(xx >= 0 && xx < board.length && yy >= 0 && yy < board[0].length && visited[xx][yy] == 0) {
                dfs(board,xx,yy,i+1,word,visited);
            }
        }
        visited[x][y] = 0;
    }
}

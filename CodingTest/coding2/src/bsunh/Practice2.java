package bsunh;

public class Practice2 {
    static boolean isFlag = false;
    static int[] dx = {0,-1,0,1};
    static int[] dy = {1,0,-1,0};
    public static void main(String[] args) {
        char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        solution(board);

        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        board = new char[][]{{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','O','X'}};
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        solution(board);

        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void solution(char[][] board) {
        int visited[][] = new int[board.length][board[0].length];
//        for(int i=0; i<board.length; i++) {
//            for(int j=0; j<board[i].length; j++) {
//                if(board[i][j] == 'O' && visited[i][j] == 0) {
//                    dfs(board,visited,i,j);
//                    if(!isFlag){
//                        for(int k=0; k< board.length; k++) {
//                            for(int l=0; l<board[0].length; l++) {
//                                if(visited[k][l] == 1) {
//                                    board[k][l] = 'X';
//                                }
//                            }
//                        }
//                    }
//                    isFlag = false;
//                }
//            }

            for(int i=0; i<board.length; i++)   {
                if(board[i][0] == 'O' && visited[i][0]==0){
                    dfs(board,visited,i,0);
                }
                if(board[i][board[0].length-1] == 'O' && visited[i][board[0].length-1]==0){
                    dfs(board,visited,i,board[0].length-1);
                }
            }

            for(int i=0; i<board[0].length; i++){
                if(board[0][i] == 'O' && visited[0][i]==0){
                    dfs(board,visited,0,i);
                }
                if(board[board.length-1][i] == 'O' && visited[board.length-1][i]==0){
                    dfs(board,visited,board.length-1,i);
                }
            }

            for(int i=0; i<board.length; i++){
                for(int j=0; j<board[i].length; j++){
                    if(board[i][j] == 'O' && visited[i][j] != 1){
                        board[i][j] = 'X';
                    }
                }
            }
    }

    private static void dfs(char[][] board, int[][] visited, int x, int y) {
//        if(x == 0 || x == board.length-1 || y == 0 || y == board[0].length-1) {
//            isFlag = true;
//        }

        visited[x][y] = 1;

        for(int i=0; i<4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            if(newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length && visited[newX][newY] == 0
            && board[newX][newY] == 'O') {
                dfs(board,visited,newX,newY);
            }
        }

    }
}

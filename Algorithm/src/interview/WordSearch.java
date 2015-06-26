package interview;

public class WordSearch{
	
	// 两次AC, DFS的题现在还是可以的；
    
	private int N;
	private int M;
	private int dir[][] = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	
	public boolean exist(char[][] board, String word) {
		N = board.length;
		if (N == 0){
			return false;
		}
		M = board[0].length;
		boolean flag[][] = new boolean[N+1][M+1];
		for (int i = 0; i < N; i++){
			for (int j = 0; j < M; j++){
				if (board[i][j] == word.charAt(0)){
					flag[i][j] = true;
					if (search(board, flag, i ,j ,word, 1)){ // 这里传1而不是0；
						return true;
					}
					flag[i][j] = false; // 如果当前走到这里一定要回溯；
				}
			}
		}
		return false;
    }
	
	public boolean search(char[][] board, boolean[][] flag, int x, int y, String word, int index){
		if (index == word.length()){
			return true;
		}
		for (int i = 0; i < 4; i++){
			int row = x + dir[i][0];
			int col = y + dir[i][1];
			if (row >= 0 && row < N && col >= 0 && col < M && word.charAt(index) == board[row][col] && !flag[row][col]){
				flag[row][col] = true;
				boolean res = search(board, flag, row, col, word, index+1);
				if (res){
					return true;
				}
				flag[row][col] = false;
			}
		}
		// 四个方向都走不通的话说明当前节点就是走不通的；
		return false;
	}
}
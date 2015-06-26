package interview;

public class SudokuSolver {
	private int M;
	private int N;
	
	// 二维dfs其实和一维差不多；
	// 关键在 i*row + j ，将两个参数合并成一个参数进行传递；二维数组常用的技巧之一；
	// 还是AC的有点费劲，很多细节出错；
	public void solveSudoku(char[][] board) {
		if (board == null){
			return;
		}
		M = board.length;
		N = board[0].length;
        dfs(0,board);
    }
	
	public boolean dfs(int index, char[][] board){
		if (index == M*N){
			return true;
		}
		int row = index / M;
		int col = index % M;
		if (board[row][col] != '.'){  // 如果是!=，说明已经填上数字了，就不用重复计算了；
			return dfs(index+1, board);
		}else{
			for (int i = 1; i <= 9; i++){
				if (check(i, index, board)){
					board[row][col] = (char) (i+'0');
					boolean flag = dfs(index+1, board);
					if (flag){
						return true;
					}
					board[row][col] = '.';
				}
			}
			return false;
		}
	}
	
	boolean check(int data, int index, char[][] board){
		int row = index / M;
		int col = index % M;
		for (int i = 0; i < M; i++){
			if (board[i][col] == data+'0'){
				return false;
			}
		}
		for (int i = 0; i < N; i++){
			if (board[row][i] == data+'0'){
				return false;
			}
		}
		// 小的三角形的技巧还是没有掌握；
		int beginx = row / 3 * 3;
		int beginy = col / 3 * 3;
		for (int i = beginx; i < beginx + 3; i++) {
			for (int j = beginy; j < beginy + 3; j++) {
				if (data+'0' == board[i][j]){
					return false;
				}
			}
		}
		return true;
	}
}




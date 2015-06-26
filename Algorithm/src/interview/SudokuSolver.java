package interview;

public class SudokuSolver {
	private int M;
	private int N;
	
	// ��άdfs��ʵ��һά��ࣻ
	// �ؼ��� i*row + j �������������ϲ���һ���������д��ݣ���ά���鳣�õļ���֮һ��
	// ����AC���е�Ѿ����ܶ�ϸ�ڳ���
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
		if (board[row][col] != '.'){  // �����!=��˵���Ѿ����������ˣ��Ͳ����ظ������ˣ�
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
		// С�������εļ��ɻ���û�����գ�
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




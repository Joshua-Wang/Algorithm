package interview;

import java.util.ArrayList;

import org.junit.Test;

public class NQueues {
	
	public ArrayList<String[]> solveNQueens(int n) {
		ArrayList<String[]> res = new ArrayList<String[]>();
		boolean flag[][] = new boolean[n][n];
		String [] temp = new String[n];
		dfs(res, temp, 0,n, flag);
		return res;
    }
	
	public void dfs(ArrayList<String[]> res, String[] temp, int index, int n, boolean[][] flag){
		if (index == n){
			String [] newTemp = new String[n];
			System.arraycopy(temp, 0, newTemp, 0, n); // 这里要重新copy一份数组出来；System.arraycopy();
			res.add(newTemp);
			return ;
		}
		for (int i = 0; i < n; i++){ // 放在第i列；
			if (check(index, i, flag)){
				flag[index][i] = true;
				char[] line = new char[n];
				for (int j = 0; j < n; j++){
					if (j == i){
						line[j] = 'Q';
					}else{
						line[j] = '.';
					}
				}
				temp[index] = new String(line, 0, n); // char[] to String ,String to char[]
				dfs(res, temp, index+1, n, flag);
				flag[index][i] = false;
			}
		}
	}
	
	public boolean check(int row, int col , boolean[][] flag){
		int len = flag.length;
		for (int i = 0; i < len;  i++){
			if (flag[i][col]){
				return false;
			}
		}
		for (int i = 0; i < row; i++){
			for (int j = 0; j < len; j++){
				if (flag[i][j]){
					if ((i - row == j - col) || (i - row == col -j)){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	@Test
	public void test(){
		solveNQueens(4);
		System.out.println(123);
	}
	
}
















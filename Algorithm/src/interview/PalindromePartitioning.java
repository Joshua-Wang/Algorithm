package interview;

import java.util.ArrayList;

import org.junit.Test;

public class PalindromePartitioning {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// 开始思路还是没有。。。
	// http://blog.csdn.net/doc_sgl/article/details/13418125 双DP问题
	// 这题还是挺不好做的，边界很容易弄错，
	// 判断是否是回文的dp转移方程： dp[i][j] = s.charAt(i) == s.charAt(j) && (j-i < 2 || dp[i+1][j-1]);
	public int minCut(String s) {
		if (s == null || s == ""){
			return 0;
		}
		int len = s.length();
		int dp[] = new int[len+1];
		dp[len] = 0;
		boolean flag[][] = new boolean[len][len];
		for (int i = 0; i < len; i++){
			dp[i] = len;
		}
		for (int  i = len-1; i >= 0; i--){
			for (int j = i; j < len; j++){
				if (s.charAt(i) == s.charAt(j) && (j-i<2 || flag[i+1][j-1])){
					flag[i][j] = true;
					dp[i] = Math.min(dp[i], dp[j+1] + 1);
				}
			}
		}
		return dp[0] - 1;
    }
	
	// 一次AC
	public ArrayList<ArrayList<String>> partition(String s) {
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		if (s == null || s.equals("")){
			return res;
		}
		ArrayList<String> line = new ArrayList<String>();
		dfs(s, res, line, 0);
		return res;
    }
	
	public void dfs(String s, ArrayList<ArrayList<String>> res, ArrayList<String> line, int index){
		if (index == s.length()){
			ArrayList<String> newLine = new ArrayList<String>(line);
			res.add(newLine);
			return ;
		}
		for (int i = index; i < s.length(); i++){
			if (isPalindrome(s,index, i)){
				line.add(s.substring(index,i+1));
				dfs(s, res, line, i+1);
				line.remove(line.size()-1);
			}
		}
	}
	
	public boolean isPalindrome(String s, int begin, int end){
		if (begin > end){
			return false;
		}
		while (begin <= end){
			if (s.charAt(begin) != s.charAt(end)){
				return false;
			}
			begin++;
			end--;
		}
		return true;
	}
	
	@Test
	public void test(){
		System.out.println(partition("aabbb"));
	}
}



















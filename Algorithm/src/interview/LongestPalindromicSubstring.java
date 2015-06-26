package interview;

public class LongestPalindromicSubstring {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// 如果枚举子串的首尾，需要O(n^3)，利用动态规划可以降到O(n^2)；
	// 和上一题的思路类似，其实就是 dp[i][j] = s.charAt(i) == s.charAt(j) && (j-i < 2 || dp[i+1][j-1]);
	// 一次AC 
	public String longestPalindrome(String s) {
        if (s == null || s == ""){
        	return s;
        }
        int len = s.length();
        boolean dp[][] = new boolean[len][len];
        int begin = 0, end = 0, res = 0;
        for (int i = len-1; i >= 0; i--){
        	for (int j = i; j < len; j++){
        		if (s.charAt(i) == s.charAt(j) && (j-i<2 || dp[i+1][j-1])){
        			dp[i][j] = true;
        		}
        		if (dp[i][j]){
        			if (j - i > res){
        				res = j - i;
        				begin = i;
        				end = j;
        			}
        		}
        	}
        }
        return s.substring(begin,end + 1);
    }
}

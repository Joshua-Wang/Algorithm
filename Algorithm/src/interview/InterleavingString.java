package interview;

public class InterleavingString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
	}
	
	// 动态规划，动态规划的题就是不好做啊，没思路的好根本不好想；
	public boolean isInterleave(String s1, String s2, String s3) {
		int len1 = s1.length();
		int len2 = s2.length();
		int len3 = s3.length();
		if (len3 != len1+len2){
			return false;
		}
		boolean dp[][] = new boolean[len1+1][len2+1];
		dp[0][0] = true;
		for (int i = 1; i <= len1; i++){
			dp[i][0] = dp[i-1][0] && (s1.charAt(i-1) == s3.charAt(i-1));
		}
		for (int i = 1; i <= len2; i++){
			dp[0][i] = dp[0][i-1] && (s2.charAt(i-1) == s3.charAt(i-1));
		}
		for (int i = 1; i <= len1; i++){
			for ( int j = 1; j <= len2; j++){
				dp[i][j] = (dp[i-1][j] && (s3.charAt(i+j-1) == s1.charAt(i-1))) || (dp[i][j-1] && (s3.charAt(i+j-1) == s2.charAt(j-1)));
			}
		}
		return dp[len1][len2];
	}

}

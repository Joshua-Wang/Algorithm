package interview;

import java.util.Set;

public class WordBreak {
	// һ��AC, ľ������~
	public boolean wordBreak(String s, Set<String> dict){
		if (s == null || dict == null){
			return false;
		}
		int len = s.length();
		boolean dp[] = new boolean[len+1];
		dp[0] = true;
		for (int i = 1; i <= len; i++){
			for (int j = 0; j < i; j++){
				if (dp[j] && dict.contains(s.subSequence(j, i))){
					dp[i] = true;
				}
			}
		}
		return dp[len];
	}
}

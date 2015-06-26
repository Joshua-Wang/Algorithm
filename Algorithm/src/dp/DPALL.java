package dp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;

import org.junit.Test;

public class DPALL {

	// 遇到偶数就除以二，遇到基数就加一或减一，求一个数变为1的最小步数，dp问题
	// 基于循环的不好做，因为这里需要重复计算；
	public int mimmumStep(int n){
		if (n == 1){
			return 0;
		}
		if (n == 2){
			return 1;
		}
		if (n%2 == 1){
			return Math.min(mimmumStep(n-1), mimmumStep(n+1)) + 1;
		}else{
			return mimmumStep(n/2)+1;
		}
	}
	
	// 用到了dp的思想；O(n)的时间，只扫了一遍就找出来了；
	public int longestSubWithoutRepeat(String s){	
		int idx = -1; // idx 记录的当前子串的起始位置；
		int max = -999999;
		int pos[] = new int[256]; // 因为char转换为int 是不会超过256的；
		for (int i = 0; i < pos.length; i++){
			pos[i] = -1;
		}
		for (int i = 0; i < s.length(); i++){
			// 遇到重复元素时，子串只能从重复元素下一个开始，idx更新为i上次出现的地址；
			if (pos[s.charAt(i)] > idx){
				idx = pos[s.charAt(i)];  
			}
			pos[s.charAt(i)] = i;
			if (i-idx > max){
				max = i - idx;
			}
		}
		return max;
	}
	
	// 计算两个字符串之间的最短距离，允许增删改操作；
	public int editDistance(String s1, String s2){
		int m = s1.length();
		int n = s2.length();
		int dp[][] = new int[m][n];
		// 先把数组两边填好；
		for (int i = 0; i < m; i++){
			dp[i][0] = i;
		}
		for (int j = 0; j < n; j++){
			dp[0][j] = j;
		}
		for (int i = 1; i < m; i++){
			for (int j = 1; j < n; j++){
				if (s1.charAt(i) == s2.charAt(j)){
					dp[i][j] = dp[i-1][j-1];
				}else{
					dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1;
				}
			}
		}
		return dp[m-1][n-1];
	}
	
	public int min(int a, int b, int c){
		int res = 999999;
		if (a < res){
			res = a;
		}
		if (b < res){
			res = b;
		}
		if (c < res){
			res = c;
		}
		return res;
	}
	
	// 在s中能找到多少个t的序列；
	// http://blog.csdn.net/worldwindjp/article/details/19770281 
	// 对于dp来说，除了地推公式，初始化也很重要
	public int distinctSequence(String s, String t){
		int lens = s.length();
		int lent = t.length();
		if (lens < lent ){ 
			return 0;
		}
		int dp[][] = new int[lens][lent]; // Java 数组默认初始化为0
		dp[0][0] = 1;                     // dp[0][0] = 1很重要
		for (int i = 1; i < lens; i++){
			for (int j = 0; j < lent; j++){
				if (j == 0){
					dp[i][j] = 1;
					continue;
				}
				if (s.charAt(i) == t.charAt(j)){
					// 此时，当前字符在s中可以保留也可以删掉；保留的话就是在剩余i-1中找j-1，就是dp[i-1][j-1]
					dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
				}else{
					// 此时当前字符只能删掉，只能在i-1中来找j个字符；
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		return dp[lens-1][lent-1];
	}

	// 经典dp， Word Break 
	public boolean wordBreak(String s, Set<String> dict){
		if (s == null){
			return true;
		}
		int len = s.length();
		boolean dp[] = new boolean[len+1];
		dp[0] = true;
		for(int i = 1; i <= len; i++){
			for (int j = i-1; j >= 0; j--){
				if (dp[j] && dict.contains(s.substring(j, i-j))){
					dp[i] = true;
					break;
				}
			}
		}
		return dp[len];
	}
	
	// 最长递增子序列
	// 叠罗汉问题的子问题，面试金典265页，先按身高排序，再在体重中找最长递增子序列
	public int maxAscendSub(int A[]){
		int len = A.length;
		int dp[] = new int[len];
		dp[0] = 1;
		for (int i = 1; i < len; i++){
			int tmp = 0;
			for (int j = 0; j < i; j++){
				if (A[i] > A[j] && dp[j] > tmp){
					tmp = A[j];
				}
			}
			dp[i] = tmp + 1;
		}
		return dp[len-1];
	}
	
	// 叠罗汉问题具体实现，需要打印出结果；
	// DP:就是用前面的结果来计算后面的结果；
	public void glareProblem(Instance[] data){
		int len = data.length;
		Arrays.sort(data, new InstanceComparator());
		int dp[] = new int[len];
		int res = Integer.MIN_VALUE;
		dp[0] = 1;
		for(int i = 1; i < len; i++){
			int tmp = 0;
			for (int j = 0; j < i; j++){
				if (data[j].weight < data[i].weight){
					if (dp[j] > tmp){
						tmp = dp[j];
					}
				}
			}
			dp[i] = tmp+1;
			if (dp[i] > res){
				res = dp[i];
			}
		}
		for (int i = 0; i < len; i++){
			System.out.println(dp[i]);
		}
		System.out.println(res);
		// 如何输出结果还没想出来；
	}
	
	@Test
	public void testGlareProblem(){
		Instance[] test = new Instance[6];
		test[0] = new Instance(175,120);
		test[1] = new Instance(170,125);
		test[2] = new Instance(172,110);
		test[3] = new Instance(178,140);
		test[4] = new Instance(179,123);
		test[5] = new Instance(180,145);
		glareProblem(test);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DPALL dpTest = new DPALL();
		// System.out.println(dpTest.mimmumStep(1000));
		String s = "hewefhjkellolo";
		System.out.println(dpTest.longestSubWithoutRepeat(s));
		String s1 = "rabbbit";
		String s2 = "rabbit";
		System.out.println(dpTest.editDistance(s1, s2));
		System.out.println(dpTest.distinctSequence(s1, s2));
	}
}


class Instance{
	public int height;
	public int weight;
	public Instance(int h, int w){
		height = h;
		weight = w;
	}
}

class InstanceComparator implements Comparator{
	public final int compare(Object obj1, Object obj2){
		if (((Instance)obj1).height > ((Instance)obj2).height){
			return 1;
		}else if (((Instance)obj1).height < ((Instance)obj2).height){
			return -1;
		}else{
			return 0;
		}
	}
}



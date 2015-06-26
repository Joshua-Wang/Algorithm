package dp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;

import org.junit.Test;

public class DPALL {

	// ����ż���ͳ��Զ������������ͼ�һ���һ����һ������Ϊ1����С������dp����
	// ����ѭ���Ĳ���������Ϊ������Ҫ�ظ����㣻
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
	
	// �õ���dp��˼�룻O(n)��ʱ�䣬ֻɨ��һ����ҳ����ˣ�
	public int longestSubWithoutRepeat(String s){	
		int idx = -1; // idx ��¼�ĵ�ǰ�Ӵ�����ʼλ�ã�
		int max = -999999;
		int pos[] = new int[256]; // ��Ϊcharת��Ϊint �ǲ��ᳬ��256�ģ�
		for (int i = 0; i < pos.length; i++){
			pos[i] = -1;
		}
		for (int i = 0; i < s.length(); i++){
			// �����ظ�Ԫ��ʱ���Ӵ�ֻ�ܴ��ظ�Ԫ����һ����ʼ��idx����Ϊi�ϴγ��ֵĵ�ַ��
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
	
	// ���������ַ���֮�����̾��룬������ɾ�Ĳ�����
	public int editDistance(String s1, String s2){
		int m = s1.length();
		int n = s2.length();
		int dp[][] = new int[m][n];
		// �Ȱ�����������ã�
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
	
	// ��s�����ҵ����ٸ�t�����У�
	// http://blog.csdn.net/worldwindjp/article/details/19770281 
	// ����dp��˵�����˵��ƹ�ʽ����ʼ��Ҳ����Ҫ
	public int distinctSequence(String s, String t){
		int lens = s.length();
		int lent = t.length();
		if (lens < lent ){ 
			return 0;
		}
		int dp[][] = new int[lens][lent]; // Java ����Ĭ�ϳ�ʼ��Ϊ0
		dp[0][0] = 1;                     // dp[0][0] = 1����Ҫ
		for (int i = 1; i < lens; i++){
			for (int j = 0; j < lent; j++){
				if (j == 0){
					dp[i][j] = 1;
					continue;
				}
				if (s.charAt(i) == t.charAt(j)){
					// ��ʱ����ǰ�ַ���s�п��Ա���Ҳ����ɾ���������Ļ�������ʣ��i-1����j-1������dp[i-1][j-1]
					dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
				}else{
					// ��ʱ��ǰ�ַ�ֻ��ɾ����ֻ����i-1������j���ַ���
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		return dp[lens-1][lent-1];
	}

	// ����dp�� Word Break 
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
	
	// �����������
	// ���޺�����������⣬���Խ��265ҳ���Ȱ�������������������������������
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
	
	// ���޺��������ʵ�֣���Ҫ��ӡ�������
	// DP:������ǰ��Ľ�����������Ľ����
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
		// �����������û�������
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



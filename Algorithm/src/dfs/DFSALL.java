package dfs;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class DFSALL {

	/**
	 * @param args
	 */
	private boolean subsetFlag[] = new boolean[100];
	private boolean permutationFlag[] = new boolean[100];
	
	public void printSubset(int[] data){
		for (int i = 0; i < data.length; i++){
			subsetFlag[i] = false;
		}
		findSubset(data, 0, data.length);
	}
	
	public void findSubset(int[] data, int index, int length){
		if (index == length){
			for (int i = 0; i < length; i++){
				if (subsetFlag[i]){
					System.out.print(data[i] + " ");
				}
			}
			System.out.println();
			return ;   // û��return�ͻ�ջ�����
		}
		subsetFlag[index] = true;  // ����ǰ��data[index],Ȼ��ݹ鴦��index+1;
		findSubset(data, index+1, length);
		subsetFlag[index] = false;
		findSubset(data, index+1, length);
	}
	
	public void printPermutation(int[] data){
		int[] permutation = new int[data.length];
		for (int i = 0; i < data.length; i++){
			permutationFlag[i] = false;
		}
		findPermutation(data, permutation, 0, data.length);
	}
	
	// д�ݹ麯��ע���������⣺�ݹ��������ƺ�ÿһ��������target[index]�Լ�����ȡֵ��Χ��
	public void findPermutation(int[] data, int[] permutation,  int index, int length){
		if (index == length){
			for (int i = 0; i < length; i++){
					System.out.print(permutation[i] + " ");
			}
			System.out.println();
			return ;   // û��return�ͻ�ջ�����
		}
		for (int i = 0; i < length; i++){
			if (permutationFlag[i]){
				continue;
			}
			permutationFlag[i] = true;
			// ������permutation[index] ������permutation[i]����Ϊ��һ�δ�����ǵ�indexԪ�أ�������ȡֵ��Χ��i
			permutation[index] = data[i]; 
			findPermutation(data, permutation, index+1, data.length);
			permutationFlag[i] = false;
		}
	}
	
	// ���͵����ѻ���dp���Խ����
	public int uniquePath(int m, int n){
		if (m < 1 || n < 1){
			return 0;
		}
		if (m == 1 || n == 1){
			return 1;
		}
		return uniquePath(m, n-1) + uniquePath(m-1, n);
	}
	
	// ArrayList ���Ƕ�̬���飬 ������res.remove(res.size()-1)��ģ��vector.pop_back()�ķ�����
	// ������ɣ�������һ���Ⱥţ�
	// ������ÿ���������ö�飻
	public void combinationSum(int[] data, int target){
		ArrayList res = new ArrayList();
		Arrays.sort(data);
		combination(data, target, res);
	}
	
	public void combination(int[] data, int left, ArrayList res){
		if (left < 0){
			return ;
		}
		if (left == 0){
			for (int i = 0; i < res.size(); i++){
				System.out.print(res.get(i));
			}
			System.out.println();
			return ;
		}
		for (int i = 0; i < data.length; i++){
			if (data[i] <= left){ 
				res.add(data[i]);
				combination(data, left-data[i], res);
				res.remove(res.size()-1);
			}
		}
	}
	
	// ������ÿ����ֻ����һ�飻����һ����������Ϊÿ����ֻ����һ�飬���Զ���һ���������޶���ÿ�㺯������ȡֵ�ķ�Χ��
	public void combinationSum2(int[] data, int target){
		ArrayList res = new ArrayList();
		Arrays.sort(data);
		combination2(data, target, 0, res);
	}
	
	public void combination2(int[] data, int left, int start,  ArrayList res){
		if (left < 0){
			return ;
		}
		if (left == 0){
			for (int i = 0; i < res.size(); i++){
				System.out.print(res.get(i));
			}
			System.out.println();
			return ;
		}
		for (int i = start; i < data.length; i++){
			if (data[i] <= left){ 
				res.add(data[i]);
				combination2(data, left-data[i],i+1, res); //��ǰʹ���˵�i��Ԫ�أ�������һ��ֻ�ܴ�i+1��ʼѡ��
				res.remove(res.size()-1);
			}
		}
	}
	
	public void palindromePartition(String s){
		ArrayList<String> res = new ArrayList<String>();
		partition(s,0,res);
	}
	
	// DFS�������Ȱ�������д��
	public void partition(String s, int index, ArrayList<String> res){
		if (index == s.length()){
			for (int i = 0; i < res.size(); i++){
				System.out.print(res.get(i)+" ");
			}
			System.out.println();
			return ;
		}
		// ��ǰ�׶λ��ĵ�ȡֵ��Χ��index��i
		for (int i = index; i < s.length(); i++){
			if (isPalindrome(s,index,i)){
				res.add(s.substring(index, i+1));
				partition(s,i+1,res);
				res.remove(res.size()-1); //  ������Ҫ���ݣ���Ϊ��i��ʼ���Ѿ��������ˣ� Ҫ������һ���ˣ�
			}
		}
	}
	
	public boolean isPalindrome(String s, int left, int right){
		while (left <= right){
			if (s.charAt(left) != s.charAt(right)){
				return false;
			}else{
				left++;
				right--;
			}
		}
		return true;
	}
	
	public int palindromePartition2(String s){
		
		return 0;
	}
	
	// �������кϷ�����������
	public ArrayList<String> generateParenthesis(int n){
		ArrayList<String> res = new ArrayList<String>();
		String tmp = "";
		generate(n,n,res,tmp);
		return res;
	}
	
	public void generate(int l, int r, ArrayList<String> res, String tmp){
		if (l == 0 && r == 0){
			res.add(tmp);
			return ;
		}
		if (l > 0){
			generate(l-1, r, res, tmp+"(");
		}
		if (r > 0 && r > l){
			generate(l, r-1, res, tmp+")");
		}
	}
	
	@Test
	public void testGenerateParent(){
		ArrayList<String> res = generateParenthesis(5);
		for (int i = 0; i < res.size(); i++){
			System.out.println(res.get(i));
		}
	}
	
	// �Ż�����������
	public void printCombination(ArrayList<ArrayList<String>> dict){
		String res = "";
		dfs(dict, 0, res);
	}
	
	public void dfs(ArrayList<ArrayList<String>> dict, int line, String res){
		int len = dict.size();
		if (line == len){
			System.out.println(res);
			return;
		}
		int lineSize = dict.get(line).size();
		for (int i = 0; i < lineSize; i++){
			if (line == 0){
				dfs(dict, line+1, dict.get(line).get(i));
			}else{
				dfs(dict, line+1, res+"^"+dict.get(line).get(i));
			}
		}
	}
	
	@Test
	public void testPrintCombination(){
		ArrayList<String> str1 = new ArrayList<String>();
		str1.add("en");
		str1.add("cn");
		ArrayList<String> str2 = new ArrayList<String>();
		str2.add("chind");
		str2.add("japan");
		ArrayList<String> str3 = new ArrayList<String>();
		str3.add("dd");
		str3.add("cc");
		ArrayList<ArrayList<String>> dict = new ArrayList<ArrayList<String>>();
		dict.add(str1);
		dict.add(str2);
		dict.add(str3);
		printCombination(dict);
	}
	
	@Test
	public void testRestorIpAddress(){
		String iptest = "25525511135";
		ArrayList<String> res = restoreIpAddresses(iptest);
		for (int i = 0; i < res.size(); i++){
			System.out.println(res.get(i));
		}
	}
	
	// �Լ�AC�ģ�Ҳ���Ǻ��ѣ� ����Ҫע��߽磬�����ϵ�Ҫֱ�ӷ��أ�
	// ������������еģ�������ʱ��������������⣬���ߵ�һ��˼·���ԣ���ǡǡ����ֻ��һ�εĻ��᣻
	public ArrayList<String> restoreIpAddresses(String s) { 
		ArrayList<String> res = new ArrayList<String>();
		if (s == null || s.length() > 12 || s.length() < 4){ // Ҫע����д�߽���������������Ͼ�ֱ�ӷ����ˣ�
			return res;
		}
		int[] pos = new int[3];
		search(res, s, pos, 0, 0);
		return res;
	}
	
	public void search(ArrayList<String> res, String s, int[] pos, int start, int step){
		if (step == 3){
			if (validIP(s.substring(start))){
				String ipStr = "";
				ipStr += s.substring(0, pos[0]) + "." + s.substring(pos[0], pos[1]) + "." 
							+ s.substring(pos[1], pos[2]) + "." + s.substring(pos[2]);
				res.add(ipStr);
			}
			return ;
		}
		for (int i = start+1; i < s.length(); i++){
			if (validIP(s.substring(start,i))){
				pos[step] = i;
				search(res, s, pos, i, step+1);
			}
		}
	}
	
	public boolean validIP(String s){
		if (s == null || s.length() < 1 || s.length() > 3){
			return false;
		}
		if (s.length() != 1 && s.charAt(0) == '0'){
			return false;
		}
		int num = Integer.parseInt(s);
		if (num >= 0 && num <= 255){
			return true;
		}else{
			return false;
		}
	}
	
	public void solveSudoku(char[][] board) {
		 
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DFSALL test = new DFSALL();
		int data[] = {1,2,3,4,5};
		// test.printSubset(data);
		// test.printPermutation(data);
		// int step = test.uniquePath(3, 7);
		// System.out.println(step);
		// System.out.println("test");
		String str = "aab";
		test.palindromePartition(str);
	}

}




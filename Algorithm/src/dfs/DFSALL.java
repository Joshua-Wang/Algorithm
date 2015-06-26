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
			return ;   // 没有return就会栈溢出；
		}
		subsetFlag[index] = true;  // 处理当前的data[index],然后递归处理index+1;
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
	
	// 写递归函数注意两个问题：递归参数的设计和每一个处理中target[index]以及他的取值范围；
	public void findPermutation(int[] data, int[] permutation,  int index, int length){
		if (index == length){
			for (int i = 0; i < length; i++){
					System.out.print(permutation[i] + " ");
			}
			System.out.println();
			return ;   // 没有return就会栈溢出；
		}
		for (int i = 0; i < length; i++){
			if (permutationFlag[i]){
				continue;
			}
			permutationFlag[i] = true;
			// 这里是permutation[index] 而不是permutation[i]，因为这一次处理的是第index元素，而他的取值范围是i
			permutation[index] = data[i]; 
			findPermutation(data, permutation, index+1, data.length);
			permutationFlag[i] = false;
		}
	}
	
	// 典型的深搜或者dp可以解决；
	public int uniquePath(int m, int n){
		if (m < 1 || n < 1){
			return 0;
		}
		if (m == 1 || n == 1){
			return 1;
		}
		return uniquePath(m, n-1) + uniquePath(m-1, n);
	}
	
	// ArrayList 就是动态数组， 可以用res.remove(res.size()-1)来模拟vector.pop_back()的方法；
	// 独立完成，就少了一个等号；
	// 数组中每个数可以用多遍；
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
	
	// 数组中每个数只能用一遍；多了一个参数，因为每个数只能用一遍，所以多了一个参数，限定了每层函数可以取值的范围；
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
				combination2(data, left-data[i],i+1, res); //当前使用了第i个元素，所以下一次只能从i+1开始选；
				res.remove(res.size()-1);
			}
		}
	}
	
	public void palindromePartition(String s){
		ArrayList<String> res = new ArrayList<String>();
		partition(s,0,res);
	}
	
	// DFS的问题先把主函数写好
	public void partition(String s, int index, ArrayList<String> res){
		if (index == s.length()){
			for (int i = 0; i < res.size(); i++){
				System.out.print(res.get(i)+" ");
			}
			System.out.println();
			return ;
		}
		// 当前阶段回文的取值范围是index到i
		for (int i = index; i < s.length(); i++){
			if (isPalindrome(s,index,i)){
				res.add(s.substring(index, i+1));
				partition(s,i+1,res);
				res.remove(res.size()-1); //  这里需要回溯；因为从i开始的已经处理完了， 要处理下一个了；
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
	
	// 生成所有合法的括号序列
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
	
	// 雅虎北研面试题
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
	
	// 自己AC的，也不是很难， 但是要注意边界，不符合的要直接返回；
	// 编程能力还是有的，但是有时候题意理解有问题，或者第一次思路不对，但恰恰面试只有一次的机会；
	public ArrayList<String> restoreIpAddresses(String s) { 
		ArrayList<String> res = new ArrayList<String>();
		if (s == null || s.length() > 12 || s.length() < 4){ // 要注意先写边界条件，如果不符合就直接返回了；
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




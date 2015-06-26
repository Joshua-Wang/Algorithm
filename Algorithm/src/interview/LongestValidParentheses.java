package interview;

import java.util.BitSet;
import java.util.Stack;

import org.junit.Test;

public class LongestValidParentheses {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "()";
		System.out.println(new LongestValidParentheses().longestValidParentheses(s));
	}
	
	// 这题思路有点特别，不是很好想；
	// 依然不是自己AC的，好好研究；
	public int longestValidParentheses2(String s) {
		Stack<Integer> ss = new Stack<Integer>();
		int len = s.length();
		int last = -1; // 合法的序列的其实位置；
		int res = 0;
		for (int i = 0; i < len; i++){
			if (s.charAt(i) == '('){
				ss.push(i);
			}else if (s.charAt(i) == ')'){
				if (ss.isEmpty()){
					// 右括号多，新的序列必须从后面开始
					last = i;
				}else{
					ss.pop();
					if (ss.isEmpty()){
						// 一个完整的括号对序列，这里必须要减last；左括号和右括号一样多
						res = Math.max(res, i - last);
					}else{
						// 左括号多，
						res = Math.max(res, i - ss.peek()); 
					}
					
				}
			}
		}
		return res;
    }
	
	@Test
	public void test(){
		String s = "()";
		System.out.println(longestValidParentheses(s));
	}
	
	// 这题这么做，so easy 啊~~~
	public int longestValidParentheses(String s) {
		int flag[] = new int[s.length()];
		Stack<Integer> ss = new Stack<Integer>();
		for (int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			if (c == '('){
				ss.push(i);
			}else{
				if (ss.isEmpty()){
					continue;
				}
				int index = ss.pop();
				flag[index] = 1;
				flag[i] = 1;
			}
		}
		int count = 0;
		int res = 0;
		for (int i = 0; i < s.length(); i++){
			if (flag[i] == 1){
				count++;
			}else{
				res = Math.max(res, count);
				count = 0;
			}
		}
		res = Math.max(res, count);
		return res;
	}
		
	
	
	
	
	
	
	
	
	
}

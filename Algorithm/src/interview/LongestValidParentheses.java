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
	
	// ����˼·�е��ر𣬲��Ǻܺ��룻
	// ��Ȼ�����Լ�AC�ģ��ú��о���
	public int longestValidParentheses2(String s) {
		Stack<Integer> ss = new Stack<Integer>();
		int len = s.length();
		int last = -1; // �Ϸ������е���ʵλ�ã�
		int res = 0;
		for (int i = 0; i < len; i++){
			if (s.charAt(i) == '('){
				ss.push(i);
			}else if (s.charAt(i) == ')'){
				if (ss.isEmpty()){
					// �����Ŷ࣬�µ����б���Ӻ��濪ʼ
					last = i;
				}else{
					ss.pop();
					if (ss.isEmpty()){
						// һ�����������Ŷ����У��������Ҫ��last�������ź�������һ����
						res = Math.max(res, i - last);
					}else{
						// �����Ŷ࣬
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
	
	// ������ô����so easy ��~~~
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

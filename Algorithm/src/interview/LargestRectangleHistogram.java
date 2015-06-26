package interview;

import java.util.Stack;

public class LargestRectangleHistogram {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// O(n) ʵ�����񷽷���
	// ע�����Ҫ����Ҫ��һ��0����֤ջ���ϵļ���ֱ��Ϊ�գ�
	public int largestRectangleArea(int[] height) {
		int len = height.length;
		if (len == 0){
			return 0;
		}
		int newHeight[] = new int[len+1];
		for (int i = 0; i < len; i++){
			newHeight[i] = height[i];
		}
		Stack<Integer> ss = new Stack<Integer>();
		int i = 0;
		int res = 0;
		while (i <= len){
			if (ss.empty() || newHeight[ss.peek()] <= newHeight[i]){
				ss.push(i);
				i++;
			}else{
				int index = ss.pop();
				int width = ss.empty() ? i : i - ss.peek() - 1;
				res = Math.max(res, newHeight[index] * width);
			}
		}
		return res;
    }

}

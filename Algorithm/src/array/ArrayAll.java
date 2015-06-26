package array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class ArrayAll {

	/**
	 * @param args
	 */
	// HashMap, two sum
	public void twoSum(int num[], int target) {
		Map<Integer, Integer> sumMap = new HashMap<Integer, Integer>();
		int x = 0, y = 0;
		for (int i = 0; i < num.length; i++) {
			if (sumMap.containsKey(num[i])) {
				int index = (int) sumMap.get(num[i]);
				x = num[index];
				y = num[i];
				break;
			} else {
				sumMap.put(target - num[i], i);
			}
		}
		System.out.println(x + "\t" + y);
	}

	// HashSet
	// ���Ǳ���HashSet, ���Ǳ������飬ȥHashSet���ж��Ƿ����
	public int longestConsecutive(int[] num) {
		Set<Integer> mySet = new HashSet<Integer>();
		int max = 1;
		for (int i = 0; i < num.length; i++) {
			mySet.add(num[i]);
		}
		for (int i = 0; i < num.length; i++) {
			int left = num[i] - 1;
			int right = num[i] + 1;
			int count = 1;
			while (mySet.contains(left)) {
				count++;
				mySet.remove(left); // without this will TLE
				left--;
			}
			while (mySet.contains(right)) {
				count++;
				mySet.remove(right); // without this will TLE
				right++;
			}
			if (count > max) {
				max = count;
			}
		}
		return max;
	}
	
	// Best time to buy and sell stock 1 : ���ཻ��һ�Σ���һ��\��һ�Σ��������������
	public int buySellStock(int[] prices){
		if (prices.length == 0){
			return 0;
		}
		int profit = 0;
		int tmp;
		int low = prices[0];
		for (int i = 1; i < prices.length; i++){
			if (prices[i] > low){
				tmp = prices[i] - low;
				if (tmp > profit){
					profit = tmp;
				}
			}else{
				low = prices[i];
			}
		}
		return profit;
		
	}
	
	// Best time to buy and sell stock 2 : ���Խ������޴Σ������������
	// ������������Ʊ�ǲ�һ���ģ� û��ʱ��ĸ����ͬһʱ�����Ѿ�֪�������е���Ϣ
	public int buySellStock2(int[] prices){
		int profit = 0;
		for (int i = 0; i < prices.length-1; i++){
			if (prices[i+1] > prices[i]){
				profit += prices[i+1] - prices[i];
			}
		}
		return profit;
	}
	
	// Best time to buy and sell stock 3 : ���ཻ�����Σ������������
	// dp���⣬���͵ķ��η���
	public int buySellStock3(int[] prices){
		int profit = 0;
		int len = prices.length;
		int leftDP[] = new int[len]; // Java ��������������壬���ܶ���� int leftDP[10];
		int rightDP[] = new int[len];
		leftDP[0] = rightDP[len-1] = 0;
		int minVal = prices[0];
		int maxVal = prices[len-1];
		for (int i = 1; i < len; i++){
			minVal = Math.min(minVal, prices[i]);
			leftDP[i] = Math.max(leftDP[i-1], prices[i]-minVal);
		}
		for (int i = len-2; i >= 0; i--){
			maxVal = Math.max(maxVal, prices[i]);
			rightDP[i] = Math.max(rightDP[i+1], maxVal-prices[i]);
		}
		for (int i = 0; i < len; i++){
			int tmp = leftDP[i] + rightDP[i];
			profit = Math.max(tmp, profit);
		}
		return profit;
	}
	
	public int searchRotatedArray(int A[], int target){
		int l = 0;
		int r = A.length-1;
		while(l <= r){
			int mid = (l + r) / 2;
			if (A[mid] == target){
				return mid;
			}
			if (A[mid] >= A[l]){
				if (target >= A[l] && target <= A[mid]){
					r = mid - 1;
				}else{        // else ҲҪ����
					l = mid + 1;
				}
			}else{
				if (target >= A[mid] && target <= A[r]){
					l = mid + 1;
				}else{
					r = mid -1;
				}
			}
		}
		return -1;
	}
	
	// ���ظ�Ԫ��
	public int searchRotatedArray2(int A[], int target){
		
		return -1;
	}
	
	// ���к��ж�����������������в���ĳԪ��
	// �Ż������������⣺�������븴���ˣ��������ñ�־λ�������ظ�������ֻҪ����ѭ�������Ҳ����ˣ�
	// ���Ҳ���д�ݹ飡��
	public boolean find(int[][] data, int target){
		int row = 0;
		int column = data[0].length-1;
		while(row <= data.length-1 && column >= 0){
			if (target == data[row][column]){
				return true;
			}else if (target < data[row][column]){
				column--;
			}else{
				row++;
			}
		}
		return false;
	}
	
	// ��n�Ľ׳�����м���0
	public int countZeros(int n){
		int count = 0;
		for (int i = 2 ; i <= n; i++){
			count += countFive(i);
		}
		return count;
	}
	
	public int countFive(int n){
		int count = 0;
		while(n % 5 == 0){
			n /= 5;
			count++;
		}
		return count;
	}
	
	@Test
	public void testCountZeros(){
		System.out.println(countZeros(100));
	}
	
	// һ����ά����ȫ����0��1��ɣ���ȫ��1������Ӿ���
	// leetcode ԭ�⣬���ǵڶ���д��ʱ����©���ٳ���
	public int maximalRectangle(char[][] matrix){
		int res = 0;     // ��������ʼ��ΪInteger.MIN_VALUE;
		int row = matrix.length;
		if (row == 0){   // �������е�ʱ�����ȥ�ж��У�
			return 0;
		}
		int column = matrix[0].length;
		int dp[][] = new int[row][column];
		for (int i = 0; i < row; i++){
			dp[i][0] = matrix[i][0] - '0';
		}
		for (int i = 0; i < row; i++){
			for ( int j = 1; j < column; j++){
				dp[i][j] = (matrix[i][j] == '1') ? dp[i][j-1] + 1 : 0;
			}
		}
		
		for (int i = 0; i < row; i++){
			for (int j = 0; j < column; j++){
				if (dp[i][j] == 0){
					continue;
				}
				int width = Integer.MAX_VALUE;
				int k = i;
				while(k >= 0){
					width = Math.min(width, dp[k][j]);
					res = Math.max(res, width * (i-k+1));
					k--;
				}
			}
		}
		return res;
	}
	
	// Leetcode ԭ�⣺ container with most water.
	// �ҳ������㣬��֮�����ʢ����ˮ��
	public int maxArea(int[] height) {
        int res = 0;
        int left = 0, right = height.length-1;
        while(left <= right){
        	res = Math.max(res, Math.min(height[left], height[right])*(right-left));
        	// height��С�����м��ߣ�
        	if (height[left] > height[right]){
        		right--;
        	}else{
        		left++;
        	}
        }
        return res;
    }
	
	// Leetcode ԭ�⣺trapping rain water.�ڶ����Լ�д������
	// �ҳ�����ʢ����ˮ��
	public int trap(int[] A){
		if (A == null || A.length == 0){ // A.length == 0ҲҪ�ж�
			return 0;
		}
		int length = A.length;
		int left[] = new int[length];
		int right[] = new int[length];
		left[0] = right[length-1] = 0;
		for (int i = 1; i < length; i++){
			left[i] = Math.max(left[i-1], A[i-1]);
		}
		for (int i = length-2; i >= 0; i--){
			right[i] = Math.max(right[i+1], A[i+1]);
		}
		int res = 0;
		for (int i = 1; i <= length-2; i++){
			if ((Math.min(left[i], right[i]) - A[i]) > 0){ // ����Ҫ����0������ӣ�
				res += Math.min(left[i], right[i]) - A[i];
			}
		}
		return res;
	}
	
	// leetcode ԭ�⣺ Largest Rectangle in Histogram
	// http://www.cnblogs.com/avril/archive/2013/08/24/3278873.html
	// ֱ�ӱ����ķ���O(n^2)��easy����ջ�������Եõ�O(n)�Ľⷨ
	public int largestRectangleArea(int[] height) {
		
		return 0;
    }
	
	// ��߱���һ�Σ��ұ߱���һ���Ǻܳ��õķ�����
	public int[] computeWithoutDivision(int[] data){
		int[] res = new int[data.length];
		res[0] = 1;
		for(int i = 1; i < data.length; i++){
			res[i] = res[i-1]*data[i-1];
		}
		int reverse = 1;
		for(int i = data.length-2; i >= 0; i--){
			reverse *= data[i+1];    // �ұ߲����������鱣���ˣ� һ����������Ϳ����ˣ�
			res[i] = res[i]*reverse;
		}
		return res;
	}
	
	// ���������⣻ һ�������ҳ�����������������ߵĶ�����С���ұߵĶ�������
	// ��߱���һ�Σ��ұ߱���һ�Σ���¼����ǰΪֹ�����ֵ������Сֵ���񷽷� ����
	public void findAll(int[] data){
		if (data == null || data.length == 0){
			return ;
		}
		int len = data.length;
		int leftMax[] = new int[len];
		int rightMin[] = new int[len];
		leftMax[0] = data[0];
		rightMin[len-1] = data[len-1];
		for (int i = 1; i < len; i++){
			leftMax[i] = Math.max(leftMax[i-1], data[i]);
		}
		for (int i = len-2; i >= 0; i--){
			rightMin[i] = Math.min(rightMin[i+1], data[i]);
		}
		System.out.print(data[0]);
		for (int i = 1; i <= len-2; i++){
			if (data[i] >= leftMax[i-1] && data[i] <= rightMin[i+1]){
				System.out.print(" "+data[i]+" ");
			}
		}
		System.out.println(data[len-1]);
	}
	
	@Test
	public void testFindAll(){
		int data[] = {1,2,3,8,7,3,4,9,10};
		findAll(data);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayAll test = new ArrayAll();
		int array[] = { 1, 3, 5, 7, 9, 6, 8 };
		test.twoSum(array, 11);
		System.out.println(test.longestConsecutive(array));
	}
}














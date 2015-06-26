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
	// 不是遍历HashSet, 还是遍历数组，去HashSet中判断是否存在
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
	
	// Best time to buy and sell stock 1 : 至多交易一次（买一次\卖一次），返回最大利润
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
	
	// Best time to buy and sell stock 2 : 可以交易无限次，返回最大利润
	// 跟正常买卖股票是不一样的， 没有时间的概念，在同一时刻你已经知道了所有的信息
	public int buySellStock2(int[] prices){
		int profit = 0;
		for (int i = 0; i < prices.length-1; i++){
			if (prices[i+1] > prices[i]){
				profit += prices[i+1] - prices[i];
			}
		}
		return profit;
	}
	
	// Best time to buy and sell stock 3 : 至多交易两次，返回最大利润
	// dp问题，典型的分治法；
	public int buySellStock3(int[] prices){
		int profit = 0;
		int len = prices.length;
		int leftDP[] = new int[len]; // Java 数组必须这样定义，不能定义成 int leftDP[10];
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
				}else{        // else 也要处理
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
	
	// 有重复元素
	public int searchRotatedArray2(int A[], int target){
		
		return -1;
	}
	
	// 在行和列都按升序排序的数组中查找某元素
	// 雅虎笔试遇到这题：把问题想复杂了，不用设置标志位，不会重复搜索，只要跳出循环就是找不到了；
	// 而且不用写递归！！
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
	
	// 求n的阶乘最后有几个0
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
	
	// 一个二维数组全部由0和1组成，求全是1的最大子矩阵
	// leetcode 原题，但是第二遍写的时候还是漏洞百出；
	public int maximalRectangle(char[][] matrix){
		int res = 0;     // 不能随便初始化为Integer.MIN_VALUE;
		int row = matrix.length;
		if (row == 0){   // 必须有行的时候才能去判断列；
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
	
	// Leetcode 原题： container with most water.
	// 找出两个点，这之间可以盛最多的水；
	public int maxArea(int[] height) {
        int res = 0;
        int left = 0, right = height.length-1;
        while(left <= right){
        	res = Math.max(res, Math.min(height[left], height[right])*(right-left));
        	// height较小的往中间走；
        	if (height[left] > height[right]){
        		right--;
        	}else{
        		left++;
        	}
        }
        return res;
    }
	
	// Leetcode 原题：trapping rain water.第二遍自己写出来；
	// 找出可以盛多少水；
	public int trap(int[] A){
		if (A == null || A.length == 0){ // A.length == 0也要判断
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
			if ((Math.min(left[i], right[i]) - A[i]) > 0){ // 必须要大于0才能相加；
				res += Math.min(left[i], right[i]) - A[i];
			}
		}
		return res;
	}
	
	// leetcode 原题： Largest Rectangle in Histogram
	// http://www.cnblogs.com/avril/archive/2013/08/24/3278873.html
	// 直接遍历的方法O(n^2)很easy，用栈辅助可以得到O(n)的解法
	public int largestRectangleArea(int[] height) {
		
		return 0;
    }
	
	// 左边遍历一次，右边遍历一次是很常用的方法；
	public int[] computeWithoutDivision(int[] data){
		int[] res = new int[data.length];
		res[0] = 1;
		for(int i = 1; i < data.length; i++){
			res[i] = res[i-1]*data[i-1];
		}
		int reverse = 1;
		for(int i = data.length-2; i >= 0; i--){
			reverse *= data[i+1];    // 右边不用再用数组保存了， 一个额外数组就可以了；
			res[i] = res[i]*reverse;
		}
		return res;
	}
	
	// 宜信面试题； 一个数组找出所有这样的数：左边的都比它小，右边的都比它大；
	// 左边遍历一次，右边遍历一次，记录到当前为止的最大值或者最小值，神方法 啊！
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














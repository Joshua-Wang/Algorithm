package interview;

public class BuyStock3 {
	
	// AC 的不是很顺利，因为这次比上次有个优化，只用了一个数组，右边用一个变量就行了；
	// 右边的遍历记录的最大值，不是最小值，这个要注意；
	public int maxProfit(int[] prices) {
		int len = prices.length;
		if (len <= 1){
			return 0;
		}
		int left[] = new int[len];
		int minValue = prices[0];
		left[0] = 0;
		for (int i = 1; i < len; i++){
			minValue = Math.min(minValue, prices[i]);
			left[i] = Math.max(left[i-1], prices[i] - minValue);
		}
		int rightMax = prices[len-1];
		int rightMaxVal = 0;
		int res = 0;
		for (int i = len-1; i >= 0; i--){
			rightMax = Math.max(prices[i], rightMax);
			rightMaxVal = Math.max(rightMaxVal, rightMax - prices[i]);
			res = Math.max(left[i]+rightMaxVal, res);
		}
		return res;
    }
}

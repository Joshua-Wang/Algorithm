package interview;

public class BuyStock3 {
	
	// AC �Ĳ��Ǻ�˳������Ϊ��α��ϴ��и��Ż���ֻ����һ�����飬�ұ���һ�����������ˣ�
	// �ұߵı�����¼�����ֵ��������Сֵ�����Ҫע�⣻
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

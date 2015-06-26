package interview;

public class Joseph {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(lastRemainning(3,5));
	}
	
	// 0到n-1这n个数字排成一圈，从0开始数1，数到m时删除，求数组中最后一个数字；
	// 递归公式 f(n,m) = (f(n-1,m) + m) % n;
	public static int lastRemainning(int m, int n){
		if (m < 1 || n < 1){
			return -1;
		}
		int last = 0; // i = 1的情况下最后的结果一定是第一个人；所以编号是0；
		for (int i = 2; i <= n; i++){
			last = (last + m) % i;
		}
		return last; // 如果是从1~N编号，那么return last+1;
		
	}
}

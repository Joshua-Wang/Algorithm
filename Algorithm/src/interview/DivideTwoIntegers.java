package interview;

import org.junit.Test;

public class DivideTwoIntegers {


	// ºÃ¶à¿Ó°¡°¡
	public int divide(int dividend, int divisor) {
		long res = 0;
		long a = Math.abs(dividend), b = Math.abs(divisor);
		boolean flag = true;
		if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)){
			flag = false;
		}
		while (a >= b){
			long c = b;
			for (int i = 0; a >= c; i++, c = c << i){
				a -= c;
				res += 1<<i;
			}
		}
		return flag ? (int) res : (int)(-1*res);
	}
	
	@Test
	public void test(){
		System.out.println(divide(-1010369383, -2147483648));
	}
}

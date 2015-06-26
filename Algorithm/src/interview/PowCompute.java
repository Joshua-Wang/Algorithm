package interview;

public class PowCompute {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Integer.MAX_VALUE);
	}

	// 递归
	public double pow(double x, int n) {
        if (n == 0){
        	return 1;
        }
        if (n < 0){
        	// 这里要特别注意，-Integer.MIN_VALUE = Integer.MAX_VALUE+1;
        	if (n == Integer.MIN_VALUE){
        		return 1.0 / (pow(x, Integer.MAX_VALUE)*x);
        	}else{
        		return 1.0 / pow(x, -n);
        	}
        }
        double half = pow(x, n >> 1); // 移位运算比除法运算要快；
        if (n%2 == 1){
        	return half*half*x;
        }else{
        	return half*half;
        }
    }
	
	// 非递归
	public double powRecrusive(double x, int n) {
		if (n == 0){
			return 1;
		}
		if (n < 0){
        	// 这里要特别注意，-Integer.MIN_VALUE = Integer.MAX_VALUE+1;
        	if (n == Integer.MIN_VALUE){
        		return 1.0 / (pow(x, Integer.MAX_VALUE)*x);
        	}else{
        		return 1.0 / pow(x, -n);
        	}
        	
        }
		double res = 1;
		while (n > 0){
			if (n%2 == 1){
				res *= x;
			}
			n = n >> 1;
			x = x*x;
		}
		return res;
	}
}














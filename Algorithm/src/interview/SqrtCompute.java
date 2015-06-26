package interview;

import org.junit.Test;

public class SqrtCompute {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SqrtCompute ins = new SqrtCompute();
		System.out.println(ins.sqrt(2147395599));
	}

	// 这题写了好半天。。
	// 和计算pow的方法类似，都是二分，因为如果要比o(n)小的话就一定要log(n)了，因为不可能o(1);
	// 要注意的地方是mid*mid == x，不一定总是成立，如sqrt(2)要返回1；所以要记录一下中间的mid值
	// 要写成 x / mid > mid ; 而不是 mid * mid < x, 后者或溢出；
	 public int sqrt(int x) {
	       if(x <= 1){
	    	   return x;
	       }
	       int left = 1;
	       int right = x>>1; // 是否从中间开始无所谓，因为二分的话其实就多了一次计算；
	       int notIntRes = 1;
	       while(left <= right){
	    	   int mid = (left+right) / 2;
	    	   if (x / mid > mid){  //记住，用除法；
	    	       notIntRes = mid;
	    		   left = mid+1;
	    	   }else if (x / mid < mid){
	    		   right = mid-1;
	    	   }else{
	    		   return mid;
	    	   }
	       }
	      return notIntRes;
	 }
	 
	 @Test
	 public void testSqrt(){
		 int a = sqrt(4);
		 System.out.println(a);
	 }
}






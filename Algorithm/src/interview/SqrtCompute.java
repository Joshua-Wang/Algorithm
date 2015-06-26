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

	// ����д�˺ð��졣��
	// �ͼ���pow�ķ������ƣ����Ƕ��֣���Ϊ���Ҫ��o(n)С�Ļ���һ��Ҫlog(n)�ˣ���Ϊ������o(1);
	// Ҫע��ĵط���mid*mid == x����һ�����ǳ�������sqrt(2)Ҫ����1������Ҫ��¼һ���м��midֵ
	// Ҫд�� x / mid > mid ; ������ mid * mid < x, ���߻������
	 public int sqrt(int x) {
	       if(x <= 1){
	    	   return x;
	       }
	       int left = 1;
	       int right = x>>1; // �Ƿ���м俪ʼ����ν����Ϊ���ֵĻ���ʵ�Ͷ���һ�μ��㣻
	       int notIntRes = 1;
	       while(left <= right){
	    	   int mid = (left+right) / 2;
	    	   if (x / mid > mid){  //��ס���ó�����
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






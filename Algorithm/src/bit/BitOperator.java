package bit;

import java.util.BitSet;

import org.junit.Test;

public class BitOperator {

	public int numberOf1(int n){
		int count = 0;
		while (n != 0){
			n = n & (n-1);    // 将n最右边的1置为0
			count++;
		}
		return count;
	}
	
	/**
	 * 至少要改变几位才能将a变为b
	 */
	public int changeNum(int a, int b){
		int count = 0;
		int c = a ^ b;
		while (c != 0){
			c = c >> 1;
			count += (c & 1); // 另一种方法计算一个数二进制中有多少个1；
		}
		return count;
	}
	
	@Test
	public void testBitSet(){
		BitSet bs = new BitSet();
		for (int i = 0; i < 1000; i++){
			if (bs.get(i)){
				System.out.println(i);
			}else{
				bs.set(i);
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}

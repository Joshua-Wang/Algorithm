package interview;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class Array01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void testmaxLengthSame01(){
		int data[] = {0, 0, 0,1 ,1 ,1 ,1};
		System.out.println(maxLengthSame01(data));
	}
	
	public int maxLengthSame01(int[] data){
		int len = data.length;
		int max = 0;
		int[] preSum = new int[len];
		Map<Integer, Integer> times = new HashMap<Integer, Integer>();
		for(int i = 0; i < len; i++){
			data[i] = (data[i] == 0 ? -1 : data[i]);
		}
		preSum[0] = data[0];
		for (int i = 1; i < len; i++){
			preSum[i] = preSum[i-1] + data[i];
			int index1 = 0,index2 = 0;
			if (preSum[i] == 0){
				index1 = i+1;
			}else{
				if (times.containsKey(preSum[i])){
					index2 = i - times.get(preSum[i]);
				}else{
					times.put(preSum[i], i);
				}
			}
			max = Math.max(index1, max);
			max = Math.max(index2, max);
		}
		return max;
	}
	
	public int maxLengthTwoArray(int[] data1, int[] data2){
		int len = data1.length;
		for (int i = 0; i < len; i++){
			data1[i] = data1[i] - data2[i];
		}
		int[] preSum = new int[len];
		int max = 0;
		Map<Integer, Integer> times = new HashMap<Integer, Integer>();
		preSum[0] = data1[0];
		for (int i = 1; i < len; i++){
			preSum[i] = preSum[i-1] + data1[i];
			int index1 = 0,index2 = 0;
			if (preSum[i] == 0){
				index1 = i+1;
			}else{
				if (times.containsKey(preSum[i])){
					index2 = i - times.get(preSum[i]);
				}else{
					times.put(preSum[i], i);
				}
			}
			max = Math.max(index1, max);
			max = Math.max(index2, max);
		}
		return max;
	}
}













package interview;

public class ShiftArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {1,2,3,4,5,6,7};
		new ShiftArray().shift(array, 5);
	}

	public void shift(int[] array, int shift){
		int len = array.length;
		shift = shift % len;
		reverse(array, 0, len-1);
		reverse(array, 0, shift-1);
		reverse(array, shift, len-1);
		for (int i = 0; i < len; i++){
			System.out.print(array[i]);
		}
	}
	
	public void reverse(int[] data, int left, int right){
		while (left <= right){
			int tmp = data[left];
			data[left] = data[right];
			data[right] = tmp;
			left++;
			right--;
		}
	}
	
}

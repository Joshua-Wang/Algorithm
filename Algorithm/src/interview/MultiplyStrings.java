package interview;

public class MultiplyStrings {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// 非常典型的大数乘法；开始时还是没有思路，记住这种思路；
	public String multiply(String num1, String num2) {
		if (num1 == null || num1.length() == 0){
			return num2;
		}
		if (num2 == null || num2.length() == 0){
			return num1;
		}
		if (num1.charAt(0) == '0' || num2.charAt(0) == '0'){
			return "0";
		}
		int len1 = num1.length();
		int len2 = num2.length();
		int[] data = new int[len1+len2];
		
		for (int i = 0; i < len1; i++){
			for (int j = 0; j < len2; j++){
				data[i+j+1] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
			}
		}
		String res = "";
		for (int i = len1+len2-1; i >= 0; i--){
			if (i > 0){
				data[i-1] += data[i] / 10;
				data[i] %= 10;
			}
			res = data[i] + res;
		}
		return res.charAt(0) == '0' ? res.substring(1) : res;
    }

}















package interview;

public class FindMaxNum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new FindMaxNum().find("faahoag123fa1fhao123fhaofh12345haofhd21234567853fahfa1234567"));
	}
	
	// 需要两个指针走的题不要用for，用while，然后指针每次都要和长度进行判断；
	// 类似的字符串的题还是要多练
	public String find(String str){
		if (str == null || str.length() == 0){
			return str;
		}
		String res = "";
		int max = 0;
		int end = 0;
		int start = 0;
		
		while(start < str.length()){
			if (str.charAt(start) >= '0' && str.charAt(start) <= '9'){
				end = start;
				while (end < str.length() && str.charAt(end) >= '0' && str.charAt(end) <= '9'){
					end++;
				}
				if (end - start > max){
					max = end - start;
					res = str.substring(start, end);
				}
				start = end;
			}else{
				start++;
			}
		}
		return res;
	}
}

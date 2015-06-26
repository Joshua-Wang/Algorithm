package interview;

public class FindMaxNum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new FindMaxNum().find("faahoag123fa1fhao123fhaofh12345haofhd21234567853fahfa1234567"));
	}
	
	// ��Ҫ����ָ���ߵ��ⲻҪ��for����while��Ȼ��ָ��ÿ�ζ�Ҫ�ͳ��Ƚ����жϣ�
	// ���Ƶ��ַ������⻹��Ҫ����
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

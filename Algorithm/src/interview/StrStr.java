package interview;

import org.junit.Test;

public class StrStr {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// Ҫ���Ǳ����Ҳ����������
	public String strStr2(String haystack, String needle) {
		int index = haystack.indexOf(needle);
		if (index >= 0){
			return haystack.substring(index);
		}
		return null;
	}
	
	@Test
	public void test(){
		String s1 = "";
		String s2 = "";
		System.out.println(s2.length());
	}

	// һ���򵥵ĳ��򶼲�����д�ԣ��Լ�д���뿼�ǵ����̫���ˣ� �ַ������� null ���� "" ��û�п��ǣ�
	// ��ȡ��KMP	�Լ�ʵ��һ�飻
	// �ر���Ҫ���� "","" �����������
	public String strStr(String haystack, String needle) {
		if (haystack == null || needle == null){
			return null;
		}
		int len1 = haystack.length();
		int len2 = needle.length();
		if (len1 < len2){
			return null;
		}
		int i = 0, j = 0, start = 0;
		while (start <= len1- len2){
			// i , j ��Ҫ����str.charAt(i) str.charAt(j)֮ǰ�жϣ�
			while(i < haystack.length() && j < needle.length() && haystack.charAt(i) == needle.charAt(j)){
				i++;
				j++;
			}
			if (j == needle.length()){
				return haystack.substring(start);
			}
			start++;
			i = start;
			j = 0;
		}
		return null;
	}
}















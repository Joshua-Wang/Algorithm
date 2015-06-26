package interview;

import org.junit.Test;

public class StrStr {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// 要考虑比如找不到的情况；
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

	// 一个简单的程序都不容易写对；自己写代码考虑的情况太少了， 字符串各种 null 各种 "" 都没有考虑；
	// 争取把KMP	自己实现一遍；
	// 特别需要考虑 "","" 的用例情况；
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
			// i , j 需要访问str.charAt(i) str.charAt(j)之前判断；
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















package string;

import java.util.*;

import org.junit.Test;

public class StringAll {

	/**
	 * @param args
	 */
	// for C++, string str = ""; str += 'a'; str[i] > 'a'; 
	// sort(str.begin(),str.end());
	// ArrayList��array[]������array[]�Ƕ����ģ���ArrayList�ǲ������ģ�
	public ArrayList<String> anagrams(ArrayList<String> strs){
		ArrayList<String> result = new ArrayList<String>();
		Map<String, Integer> mymap = new HashMap<String, Integer>();
		for (int i = 0; i < strs.size(); i++){
			String tempstr = strs.get(i);
			tempstr = mysort(tempstr);
			if (mymap.containsKey(tempstr) == false){
				mymap.put(tempstr, i);
			}else{
				int index = mymap.get(tempstr);
				if (index >= 0){
					result.add(strs.get(mymap.get(tempstr)));
					mymap.put(tempstr, -1);
				}
				result.add(strs.get(i));
			}
		}
		return result;
	}
	
	public String mysort(String str){
		char[] temp = str.toCharArray();
		Arrays.sort(temp);
		return new String(temp);
	}
	
	public String compress(String str){
		String result = "";
		int last = 0;
		int count = 1;
		for (int i = 1; i < str.length(); i++){
			if (str.charAt(i) == str.charAt(last)){
				count++;
			}else{
				result += str.charAt(last) + "" + count;
				last = i;
				count = 1;
			}
		}
		result += str.charAt(last) + "" + count;
		return result;
	}
	
	@Test
	public void testCompress(){
		String test = "aabbbcd";
		System.out.println("hello");
		System.out.println(compress(test));
	}
	
	// str == null : ��ʾstr��null�� str.length() == 0����ʾstr�ǿ��ַ�����
	// �����C++����ô������ int atoi(const char* str)����Ͳ���Ҫ�ж�strԽ����������Ϊ�ⲻ�����飻Javaд��Ƚ��鷳��
	// �Ȱѿո����꣬Ȼ���ж�+-�ţ�
	// ��32λ�����ϣ�int��long����32λ��4bytes��,��64λ�����ϣ�int 4bytes, long 8bytes.
	public int atoi(String str){ 
		return 0;
		/*
		 * int atoi(const char *str) {
		        if (str == NULL){
		            return 0;
		        }
		        while(*str == ' '){
		            str++;
		        }
		        bool positive = true;
		        if (*str == '+' || *str == '-'){
		            if (*str == '-'){
		                positive = false;
		            }
		            str++;
		        }
		        long long result = 0;
		        while(*str >= '0' && *str <= '9'){
		            result = 10*result + *str-'0';
		            if (positive && result > INT_MAX){
		                return INT_MAX;
		            }else if (!positive && -1*result < INT_MIN){
		                return INT_MIN;
		            }
		            str++;
		        }
		        if (positive){
		            return result;
		        }else{
		            return -1*result;
		        }
    		}
		 */
	}
	
	// minimum-window-substring leetcode�� ��S�а���T�����ַ�������Ӵ���
	public String minWindow(String S, String T) {
		if (S == null || T == null || S.length() == 0 || T.length() == 0 || S.length() < T.length()){
			return "";
		}
		int count1[] = new int[256];
		int count2[] = new int[256];
		for (int i = 0; i < T.length(); i++){
			count1[T.charAt(i)]++;
			count2[T.charAt(i)]++;
		}
		int start = 0;
		int end;
		int count = 0;
		int res = Integer.MAX_VALUE;
		int res_start = 0, res_end = 0;
		for(end = start; end < S.length(); end++){
			if (count2[S.charAt(end)] > 0){
				count1[S.charAt(end)]--;
				count++;
			}
			if (count == T.length()){
				// ������ͷָ�룻start������
				while(count2[S.charAt(start)] == 0){
					start++;
				}
				
				// �ҵ����䣬��������֮ǰ��С����¼���䣬
				int tmp = end - start + 1;
				if (tmp < res){
					res = tmp;
					res_start = start;
					res_end = end;
				}
				
				// ����start��end�����һָ�count1���飻
				System.arraycopy(count2, 0, count1, 0, count2.length);
				count = 0;
				start++;
				end = start;
			}
		}
        return S.substring(res_start, res_end+1);
    }
	
	@Test
	public void testMinWindow(){
		String S = "ADOBECODEBANC";
		String T = "ABC";
		System.out.println(minWindow(S,T));
	}
	
	// Leetcode Word Ladder  , BFS
	// AC, Ҳû�ж��ѣ����㣺1��Ҫ��¼��ǰ���²�ĳ��ȣ�����������һ��step++��2��String���޷��޸ģ�
	// 3���������ÿ�ѵ�һ����Ҫ����dict����Ѵ�ɾ�ˣ���Ϊ��ԭ��֮��ľ������𲽵����ģ�����Ӱ������������·����
	public int ladderLength(String start, String end, Set<String> dict) {
		if (!dict.contains(start) || !dict.contains(end)){
			return 0;
		}
		if (start.equals(end)){
			return 0;
		}
		Queue<String> q1 = new LinkedList<String>();
		q1.add(start);
		dict.remove(start);
		int step = 2;
		int len1 = 1;
		int len2 = 0; // �ֱ��¼��ǰ���к���һ����еĳ��ȣ���һ���������Ժ�step++
		while(!q1.isEmpty()){
			String head = q1.remove();
			len1--;
			for (int i = 0; i < head.length(); i++){
				for (char c = 'a'; c <= 'z'; c++){
					if (head.charAt(i) == c){
						continue;
					}
					StringBuilder sb = new StringBuilder(head);
					sb.setCharAt(i, c); // String���޷��޸ģ�����Ҫ�޸�ĳһ���ַ��Ļ�����Ҫ���StringBuilder�ࣻ
					if (dict.contains(sb.toString())){
						if (end.equals(sb.toString())){
							return step;
						}else{
							q1.add(sb.toString());
							len2++;
							dict.remove(sb.toString());
						}
					}
				}
			}
			if (len1 == 0){
				len1 = len2;
				len2 = 0;
				step++;
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringAll test = new StringAll();
		ArrayList<String> str1 = new ArrayList<String>();
		str1.add("hello");
		str1.add("test");
		str1.add("hlloe");
		str1.add("hlleo");
		ArrayList<String> str2 = test.anagrams(str1);
		for (int i = 0; i < str2.size(); i++){
			//System.out.println(str2.get(i));
		}
		String s1 = "rabbbit";
		String s2 = "rabbit";
	}

}















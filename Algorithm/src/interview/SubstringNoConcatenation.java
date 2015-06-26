package interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

public class SubstringNoConcatenation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Test
	public void testFindSubString(){
		List<Integer> res = new ArrayList<Integer>();
		String S = "aaa";
		String[] L = {"a", "a"};
		res = findSubstring(S,L);
		for (int i = 0; i < res.size(); i++){
			System.out.println(res.get(i));
		}
	}
	
	// д�˺ܾã���������������⣻
	// �ܶ�ϸ��Ҫע�⣬���ǳ�ʱ��
	// ����һ��������ȽϺã�����HashMapÿ�ζ�Ҫ���½�����
	public List<Integer> findSubstring(String S, String[] L) {
		List<Integer> res = new ArrayList<Integer>();
		if (L == null || L.length == 0){
			return res;
		}
		int lenS = S.length();
		int lenL = L.length;
		int perLenL = L[0].length();
		int endIndex = lenS - perLenL*lenL;
		if (lenS < lenL*perLenL){
			return res;
		}
		for (int i = 0; i <= endIndex; i++){
			if (checkStart(i, S, L)){ // ����ֱ����ǰ�� lenL*perLenL������Ϊ�����кܶ��ظ��ģ�
				res.add(i);
			}
		}
		return res;
    }
	
	public boolean checkStart(int index, String S, String[] L){
		int lenS = S.length();
		int lenL = L.length;
		int perLenL = L[0].length();
		HashMap<String, Integer> count = new HashMap<String, Integer>();
		for (int i = 0; i < lenL; i++){
			if (!count.containsKey(L[i])){
				count.put(L[i], 1); // ��һ�ηŽ�ȥ��1��
			}else{
				count.put(L[i], count.get(L[i])+1);
			}
		}
		int endIndex = index+lenL*perLenL;
		for (int i = index; i < endIndex; i++){ 
			String temp = S.substring(i, i+perLenL);
			if (!count.containsKey(temp)){
				return false;
			}else{
				count.put(temp, count.get(temp)-1);
				i = i+perLenL-1;  // forѭ������i++����������i�������Σ�
			}
		}
		for (int i = 0; i < lenL; i++){
			if (count.get(L[i]) != 0){
				return false;
			}
		}
		return true;
	}
}



































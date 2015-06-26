package interview;

import java.util.ArrayList;

public class LetterCombinations {

	public ArrayList<String> letterCombinations(String digits) {
        String letters[] = {" ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv","wxyz"};
        String temp = "";
        ArrayList<String> res = new ArrayList<String>();
        dfs(digits, 0, letters, res, temp);
        return res;
    }
	
	public void dfs(String digits, int index, String[] letters, ArrayList<String> res, String temp){
		if (index == digits.length()){
			String temp2 = temp;
			res.add(temp2);
			return ;
		}
		char c = digits.charAt(index);
		String letter = letters[c-'0'];
		for (int i = 0; i < letter.length(); i++){
			dfs(digits, index+1, letters, res, temp + letter.charAt(i));
		}
	}
}

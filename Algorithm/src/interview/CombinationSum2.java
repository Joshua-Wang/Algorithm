package interview;

import java.util.ArrayList;
import java.util.Arrays;

public class CombinationSum2 {

	public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
        int len = num.length;
		Arrays.sort(num);
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> cur = new ArrayList<Integer>();
		dfs(num ,res, 0, cur, target);
		return res;
    }
	
	public void dfs(int[] num, ArrayList<ArrayList<Integer>> res, int start, ArrayList<Integer> cur, int left){
		if (left == 0){
			ArrayList<Integer> next = new ArrayList<Integer>(cur);
			res.add(next);
			return ;
		}
		for (int i = start; i < num.length; i++){
			if (left >= num[i]){
				cur.add(num[i]);
				dfs(num, res, i+1, cur, left - num[i]); // 一个元素可以选多次，这里用i;而不是i+1;
				cur.remove(cur.size() - 1);
			}
			while (i < num.length- 1 && num[i] == num[i+1]){
			    i++;
			}
		}
	}
}

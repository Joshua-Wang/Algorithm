package interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation2 {

	// ����AC, cur��������ArrayList���㣬arraylistÿ��Ҫ����newһ��������Ȼ��ÿ�λ�Ҫadd / remove�����鶼����Ҫ��
	public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
		int len = num.length;
		int flag[] = new int[len];
		Arrays.sort(num);
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> cur = new ArrayList<Integer>();
		dfs(0, num, flag,res, cur);
		return res;
    }
	
	public void dfs(int index, int[] num, int[] flag, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> cur){
		if (index == num.length){
			ArrayList<Integer> next = new ArrayList<Integer>(cur);
			res.add(next);
			return ;
		}
		for (int i = 0; i < num.length; i++){
			if (flag[i] == 0){
				flag[i] = 1;
				cur.add(num[i]);
				dfs(index+1, num, flag, res, cur);
				flag[i] = 0;
				cur.remove(cur.size()-1);
				while(i < num.length-1 && num[i] == num[i+1]){   // ��ֹ�ظ����ǹؼ���
					i++;
				}
			}
		}
	}
	
}

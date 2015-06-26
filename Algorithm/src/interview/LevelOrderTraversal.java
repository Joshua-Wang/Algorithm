package interview;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

public class LevelOrderTraversal {

	// 一次AC,毫无商量
	public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if (root == null){
			return res;
		}
		Queue<TreeNode> q1 = new LinkedList<TreeNode>();
		Queue<TreeNode> q2 = new LinkedList<TreeNode>();
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		q1.add(root);
		while (!q1.isEmpty()){
			TreeNode head = q1.poll();
			tmp.add(head.val);
			if (head.left != null){
				q2.add(head.left);
			}
			if (head.right != null){
				q2.add(head.right);
			}
			if (q1.isEmpty()){
				Queue<TreeNode> q3 = q1;
				q1 = q2;
				q2 = q3;
				ArrayList<Integer> tmpResult = new ArrayList<Integer>(tmp);
				res.add(tmpResult);
				tmp.clear();
			}
		}
		return res;
    }
}

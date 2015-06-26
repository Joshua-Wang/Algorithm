package interview;

//  ���֮������һ�Ŷ����������������ڵ�������룻

class Node{
	int val;
	Node left, right;
	int leftMax;
	int rightMax;
}

public class FindMaxLenBT {
	
	private int res = 0;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void compute(Node root){
		if (root == null){
			return ;
		}
		if (root.left != null){
			compute(root.left);
		}
		if (root.right != null){
			compute(root.right);
		}
		if (root.left == null){
			root.leftMax = 0;
		}else{
			root.leftMax = Math.max(root.left.leftMax, root.left.rightMax) + 1;
		}
		if (root.right == null){
			root.rightMax = 0;
		}else{
			root.rightMax = Math.max(root.right.leftMax, root.right.rightMax) + 1;
		}
		res = Math.max(res, root.rightMax+root.leftMax+1);
	}

}











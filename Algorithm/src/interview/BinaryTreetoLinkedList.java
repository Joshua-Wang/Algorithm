package interview;

public class BinaryTreetoLinkedList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// http://www.2cto.com/kf/201401/269562.html
	public void flatten(TreeNode root) {
		root = help(root);
	}
	
	public TreeNode help(TreeNode root){
		if (root == null || root.left == null || root.right == null){
        	return root;
        }
		TreeNode left = help(root.left);
		TreeNode right = help(root.right);
		if (left != null){
			TreeNode temp = left;
			while (temp.right != null){
				temp = temp.right;
			}
			temp.right = right;
		}else{
			left = right;
		}
		root.left = null;
		root.right = left;
		return root;
	}

}

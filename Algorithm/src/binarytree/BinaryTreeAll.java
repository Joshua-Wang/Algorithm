package binarytree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.junit.Test;

class BinaryTree{
	int val;
	BinaryTree left, right;
	BinaryTree(int val){
		this.val = val;
	}
	BinaryTree(){
		
	}
}

class ParentTree{
	int val;
	ParentTree left, right;
	ParentTree parent;
	ParentTree(int val){
		this.val = val;
	}
	ParentTree(){
		
	}
}

class TreeLinkNode{
	int val;
	TreeLinkNode left, right, next;
	TreeLinkNode(int x){
		val = x;
	}
}

public class BinaryTreeAll {

	/**
	 * @param args
	 */
	private BinaryTree previous;
	private int res = Integer.MIN_VALUE;
	
	public BinaryTree createTree(int n){
		if (n <= 0){
			return null;
		}
		BinaryTree root = new BinaryTree(n);
		root.left = createTree(n-1);
		root.right = createTree(n-1);
		return root;
	}
	
	public BinaryTree createTreeFromArray(int[] data){
		List<BinaryTree> nodeList=new ArrayList<BinaryTree>();  
        for(int each:data){  
        	BinaryTree node=new BinaryTree(each);  
            nodeList.add(node);  
        }  
        int lastRootIndex=data.length/2-1;  
        for(int i=lastRootIndex;i>=0;i--){  
            int leftIndex=i*2+1;  
            BinaryTree root=nodeList.get(i);  
            BinaryTree left=nodeList.get(leftIndex);  
            root.left = left;  
            if(leftIndex+1<data.length){  
            	BinaryTree right=nodeList.get(leftIndex+1);  
                root.right = right;  
            }  
        }  
        BinaryTree head=nodeList.get(0);  
        return head;  
	}
	
	public void preOrder(BinaryTree root){
		Stack<BinaryTree> ss = new Stack<BinaryTree>();
		System.out.println("PreOrder:");
		if (root != null){
			ss.push(root);
		}
		while(!ss.isEmpty()){
			BinaryTree temp = ss.pop();
			if (null != temp){
				System.out.println(temp.val);
				ss.push(temp.right);
				ss.push(temp.left);
			}
		}
	}
	
	public void inOrder(BinaryTree root){
		Stack<BinaryTree> ss = new Stack<BinaryTree>();
		System.out.println("InOrder:");
		while(!ss.isEmpty() || root != null){   // 这个条件有点绕
			while(root != null){
				ss.push(root);
				root = root.left;
			}
			root = ss.pop();
			System.out.println(root.val);
			root = root.right;
		}
	}
	
	public void postOrder(BinaryTree root){
		Stack<BinaryTree> stack = new Stack<BinaryTree>();
		System.out.println("PostOrder:");
		BinaryTree node = root, prev = root;    
        while (node != null || stack.size() > 0) {    
            while (node != null) {    
                stack.push(node);    
                node = node.left;    
            }    
            if (stack.size() > 0) {    
            	BinaryTree temp = stack.peek().right;    
                if (temp == null || temp == prev) {    
                    node = stack.pop();
                    System.out.println(node.val);
                    prev = node;    
                    node = null;    
                } else {    
                    node = temp;    
                }    
            }    
    
        }    
	}
	
	// 二叉树转双向链表
	// 典型的中序遍历：先左子树，然后根节点，然后右子树；只不过在记录了previous节点，并调整其和根节点的关系
	public void convertToList(BinaryTree root){  // 和剑指offer思路一致，代码更简洁，previous必须作为类成员变量；
		if (root == null){
			return ;
		}
		convertToList(root.left);
		
		if (previous != null){
			previous.right = root;
			root.left = previous; // 画二叉排序树的图更容易理解；
		}
		previous = root;
		
		convertToList(root.right);
	}
	
	public boolean isSymmetic(BinaryTree root){
		if (root == null){
			return true;
		}
		return isSymmetic(root.left, root.right);
	}
	
	// 不能复用same tree，递归比较的左子树右子树不同；只是代码很近似而已；
	public boolean isSymmetic(BinaryTree left, BinaryTree right){
		if (left == null && right == null){
			return true;
		}
		if (left == null || right == null){
			return false;
		}
		if (left.val != right.val){
			return false;
		}
		return isSymmetic(left.left, right.right) && isSymmetic(left.right, right.left);
	}
	
	public boolean isSame(BinaryTree left, BinaryTree right){
		if (left == null && right == null){
			return true;
		}
		if (left == null || right == null){
			return false;
		}
		if (left.val != right.val){
			return false;
		}
		return isSame(left.left, right.left) && isSame(left.right, right.right);
	}
	
	public boolean isBalanced(BinaryTree root){
		if (root == null){
			return true;
		}
		int left = maxDepth(root.left);
		int right = maxDepth(root.right);
		int diff = left - right;
		if (diff > 1 || diff < -1){
			return false;
		}
		return isBalanced(root.left) && isBalanced(root.right);
	}
	
	// 判断p2是否是p1的子树
	public boolean isSubTree(BinaryTree p1, BinaryTree p2){
		if (p1 == null || p2 == null){
			return false;
		}
		if (p1.val == p2.val){
			return hasSubTree(p1,p2);
		}
		return isSubTree(p1.left, p2) || isSubTree(p1.right, p2);  //注意，这边用的是或
	}
	
	public boolean hasSubTree(BinaryTree p1, BinaryTree p2){
		if (p1 == null){
			return false;
		}
		if (p2 == null){     // 这里和sameTree不同，不需要两边同时为null;
			return true;
		}
		if (p1.val != p2.val){
			return false;
		}
		return hasSubTree(p1.left, p2.left) && hasSubTree(p1.right, p2.right); // 这边用的是与
	}
	
	public int maxDepth(BinaryTree root){
		if (root == null){	
			return 0;
		}
		int leftDepth = maxDepth(root.left);
		int rightDepth = maxDepth(root.right);
		return leftDepth > rightDepth ? leftDepth+1 : rightDepth+1;
	}
	
	// 层次遍历，典型的双队列法； Java 的代码写起来比较复杂；
	public ArrayList<ArrayList<Integer>> levelOrder(BinaryTree root) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		Queue<BinaryTree> cur = new LinkedList<BinaryTree>();
		Queue<BinaryTree> next = new LinkedList<BinaryTree>();
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		if (root == null){
			return result;
		}
		if (root != null){
			cur.add(root);
		}
		while(!cur.isEmpty()){
			BinaryTree head = cur.remove();
			tmp.add(head.val);
			if (head.left != null){
				next.add(head.left);
			}
			if (head.right != null){
				next.add(head.right);
			}
			if (cur.isEmpty()){
				result.add(tmp);
				tmp = new ArrayList<Integer>();
				Queue<BinaryTree> swap = cur;
				cur = next;
				next = swap; 
			}
		}
        return result;
    }
	
	// 不能先判断当前是不是然后各自判断左右子树；要保证所有左子树的节点都小于当前节点，所以递归时要传参；
	public boolean validateBST(BinaryTree head){
		// 对应于C++的 INT_MAX, INT_MIN; 
		return validateBST(head, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	public boolean validateBST(BinaryTree head, int left, int right){
		if (head == null){
			return true;
		}
		return head.val < right && head.val > left && validateBST(head.left, left, head.val) && validateBST(head.right, head.val, right); 
	}
	
	public BinaryTree sortedArrayToBST(int[] data, int begin, int end){
		if (begin > end){
			return null;
		}
		int mid = (begin + end)/2;
		BinaryTree root = new BinaryTree(data[mid]);
		root.left = sortedArrayToBST(data, begin ,mid-1);
		root.right = sortedArrayToBST(data, mid+1, end);
		return root;
	}
	
	// 判断是否存在路径：从根节点到叶节点所有节点值加起来等于目标参数
	public boolean pathSum(BinaryTree head, int sum){
		if (head == null){
			return false;
		}
		int tmp = sum - head.val; 
		if (tmp == 0 && head.left == null && head.right == null){
			return true;
		}
		return pathSum(head.left, tmp) || pathSum(head.right, tmp);
	}
	
	// 打印出所有路径：从根节点到叶节点所有节点值加起来等于目标参数
	public void pathSum2(BinaryTree head, int sum){
		
	}
	
	// 返回二叉树中任意两个节点之间的最大和；
	// http://blog.csdn.net/worldwindjp/article/details/18953987 
	public int binaryMaxSum(BinaryTree head){
		if (head == null){
			return 0;
		}
		binaryMax(head);
		return res;
	}
	
	public int binaryMax(BinaryTree head){
		if (head == null){
			return 0;
		}
		int lres = 0,rres = 0;
		int max = head.val;
		if (head.left != null){
			lres = binaryMax(head.left);
			if (lres > 0){
				max += lres;
			}
		}
		if (head.right != null){
			rres = binaryMax(head.right);
			if (rres > 0){
				max += rres;
			}
		}
		if (max > res){
			res = max;
		}
		// 注意：返回的时候并不是返回max或者res
		if (Math.max(lres, rres) < 0){
			return head.val;
		}else{
			return head.val + Math.max(lres, rres);
		}
	}
	
	// 寻找公共祖先，二叉排序树
	// 先写函数整体，再写子函数，这样就能明确子函数的功能和返回值，参数判断等等；
	public BinaryTree commonAncestorBST(BinaryTree root, BinaryTree p, BinaryTree q){
		if (root == null || p == null || q == null){
			return null;
		}
		if (p.val < root.val && q.val < root.val){
			return commonAncestorBST(root.left, p, q);
		}else if (p.val > root.val && q.val > root.val){
			return commonAncestorBST(root.right, p, q);
		}else if (coverBST(root, p) && coverBST(root, q)){
			return root;
		}else {
			return null;
		}
	}
	
	public boolean coverBST(BinaryTree root, BinaryTree node){
		if (root == null){
			return false;
		}
		if (root == node){
			return true;
		}
		if (node.val > root.val){
			return coverBST(root.right, node);
		}else if (node.val < root.val){
			return coverBST(root.left, node);
		}else {
			return false;
		}
	}
	
	// 寻找公共祖先，非二叉排序树
	// 排序二叉树在判断该节点是否在树中会方便一些，因为只要判断左子树或者右子树，而不是二叉排序树的话就是两棵子树都要递归判断一下；
	// 剑指offer上还有只遍历一遍的方法：碉堡了！
	public BinaryTree commonAncestor(BinaryTree root, BinaryTree p, BinaryTree q){
		if (!cover(root,p) || !cover(root,q)){
			return null;
		}
		return commonAncestorHelper(root, p, q);
	}
	
	public BinaryTree commonAncestorHelper(BinaryTree root, BinaryTree p, BinaryTree q){
		if (root == null){
			return null;
		}
		if (root == p || root == q){
			return root;
		}
		boolean in_left_p = cover(root.left, p);
		boolean in_left_q = cover(root.left, q);
		if (in_left_p != in_left_q){
			return root;
		}
		BinaryTree dir = (in_left_p == true ? root.left : root.right);
		return commonAncestorHelper(dir, p, q);
	}
	
	public boolean cover(BinaryTree root, BinaryTree node){
		if (root == null){
			return false;
		}
		if (root == node){
			return true;
		}
		return cover(root.left, node) || cover(root.right, node);
	}
	
	// 寻找公共祖先，有指向父节点的指针	
	// 有指向父节点的指针的话：可以很轻松的拿到从当前节点到父节点的链表，然后判断两个链表的第一个交点就可以；
	public ParentTree commonAncestorParent(ParentTree root, ParentTree p, ParentTree q){
		int len1 = getLength(p);
		int len2 = getLength(q);
		if (len1 < len2){
			ParentTree tmp = p;
			p = q;
			q = tmp;
		}
		int step = len1-len2;
		while(step-- != 0){
			p = p.parent;
		}
		while(p != null){
			if(p == q){
				return p;
			}
			p = p.parent;
			q = q.parent;
		}
		return null;
	}
	
	public int getLength(ParentTree root){
		int count = 1;
		if (root == null){
			return 0;
		}
		while(root.parent != null){
			count++;
			root = root.parent;
		}
		return count;
	}
	
	// Populating Next Right Pointers in Each Node, 完全二叉树,画图理解，分别连接不同的线；
	public void connect(TreeLinkNode root){
		if (root == null){
			return ;
		}
		if (root.left != null){
			root.left.next = root.right;
		}
		if (root.right != null && root.next != null){
			root.right.next = root.next.left;
		}
		connect(root.left);
		connect(root.right);
	}
	
	// Populating Next Right Pointers in Each Node 2, 普通二叉树, 一次AC，但是有借鉴；
	public void connect2(TreeLinkNode root){
		Queue<TreeLinkNode> cur = new LinkedList<TreeLinkNode>();
		Queue<TreeLinkNode> next = new LinkedList<TreeLinkNode>();
		if (root == null){
			return ;
		}
		cur.add(root);
		while(!cur.isEmpty()){
			TreeLinkNode head = cur.remove();
			if (cur.isEmpty()){ // 这一步是关键，不用到后面在遍历；而且这个是从队首到队尾的遍历；
				head.next = null;
			}else{
				head.next = cur.peek();
			}
			if (head.left != null){
				next.add(head.left);
			}
			if (head.right != null){
				next.add(head.right);
			}
			if (cur.isEmpty()){
				Queue<TreeLinkNode> tmp = cur; // 在C++中可以直接swap(cur,next);
				cur = next;
				next = tmp;
			}
		}
	}
	
	// 前序中序求后序；
	// 利用C字符串更好写，见VS。
	public void constructPostOrder(int len , char[] preorder, char[] inorder, char[] postorder){
		
	}
	
	public int findMaxNumEdg(BinaryTree node){
		
		return -1;
	}
	
	@Test
	public void testConstruct(){
		char preorder[] = {'a','b','d','e','c','f','g'};
		char inorder[] = {'d','e','b','a','f','c','g'};
		char postorder[] = new char[7];
		constructPostOrder(6,preorder, inorder, postorder);
		for (int i = 0; i < postorder.length; i++){
			System.out.print(postorder[i] + " ");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTreeAll test = new BinaryTreeAll();
		int[] a={2,3,4,5,6,7,8}; //这些数据是按二叉查找树的层次遍历存放  
		/*test.inOrder(root);
		test.preOrder(root);
		test.postOrder(root);
		BinaryTree previous = null;
		test.convertToList(root);
		while(root.left != null){
			root = root.left;
		}
		while(root != null){
			System.out.println(root.val);
			root = root.right;
		}*/
		BinaryTree root1 = test.createTree(4);
		BinaryTree root2 = test.sortedArrayToBST(a, 0, a.length-1);
		if (test.pathSum(root2, 19)){
			System.out.println("Sum Found.");
		}
		if (test.validateBST(root2)){
			System.out.println("BST");
		}
	}
}

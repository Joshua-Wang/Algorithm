package interview;

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) {
		val = x;
	    next = null;
	}
}

public class RotateList {
	
	// 搞死了，虽说思路很简单，但是很多细节的处理
	// 这题虽然有思路，但是写出来仍然漏洞百出；
	// 做题的时候要有防御性编程的思路，比如这题，head == null , head.next == null, n == 0直接返回就行，不用处理；
	public ListNode rotateRight(ListNode head, int n) {
		if (head == null || head.next == null || n == 0){
			return head;
		}
		ListNode cur = head;
		int len = 1;
		while (cur.next != null){
			cur = cur.next;
			len++;
		}
		cur.next = head;  // 第一遍走完就可以连起来了；
		n = n%len;
		cur = head;
		for (int i = 1; i < len-n; i++){  // 不需要两个指针；而且不是走n步，是走len-n步；
			cur = cur.next;
		}
		ListNode temp = cur.next;
		cur.next = null;
		return temp;
    }

}


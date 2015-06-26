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
	
	// �����ˣ���˵˼·�ܼ򵥣����Ǻܶ�ϸ�ڵĴ���
	// ������Ȼ��˼·������д������Ȼ©���ٳ���
	// �����ʱ��Ҫ�з����Ա�̵�˼·���������⣬head == null , head.next == null, n == 0ֱ�ӷ��ؾ��У����ô���
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
		cur.next = head;  // ��һ������Ϳ����������ˣ�
		n = n%len;
		cur = head;
		for (int i = 1; i < len-n; i++){  // ����Ҫ����ָ�룻���Ҳ�����n��������len-n����
			cur = cur.next;
		}
		ListNode temp = cur.next;
		cur.next = null;
		return temp;
    }

}


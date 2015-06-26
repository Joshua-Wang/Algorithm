package listnode;

import java.util.HashMap;
import java.util.HashSet;

class ListNode{
	int val;
	ListNode next;
	public ListNode(int val){
		this.val = val;
	}
}

public class ListAll {

	/**
	 * @param args
	 */
	public ListNode create(int n){
		ListNode head = new ListNode(-1);
		ListNode cur = head;
		for (int i = 0; i < n; i++){
			ListNode temp = new ListNode(i);
			cur.next = temp;
			cur = cur.next;
		}
		ListNode temp = new ListNode(n-1);
		cur.next = temp;
		return head.next;
	}
	
	// ���ڵݹ������ת��
	public ListNode reverse(ListNode head){
		if (head == null || head.next == null){
			return head;
		}
		ListNode second = reverse(head.next);
		head.next.next = head;
		head.next = null;
		return second;
	}
	
	// �ǵݹ�ʵ�֣���ָ�뷨��Ҫ֪������ָ����ô�ߣ����ĸ�ָ�����ж�ѭ�����ĸ�ָ�������Ҫ���ص�ͷ��㣻
	public ListNode reverse2(ListNode head){
		if (head == null || head.next == null){
			return head;
		}
		ListNode pre,cur,post;
		pre = head;
		cur = head.next;
		while(cur != null){      // ��cur����¼ѭ��
			post = cur.next;     // post��ѭ�����渳ֵ
			cur.next = pre;
			pre = cur;
			cur = post;
		}
		head.next = null;
		return pre;
	}
	
	public ListNode reverseNeighbor(ListNode head){
		return head;
	}
	
	public ListNode removeDuplicate(ListNode head){
		if (head == null || head.next == null){
			return head;
		}
		ListNode cur = head;
		while(cur.next != null){
			if (cur.next.val == cur.val){
				cur.next = cur.next.next;
			}else{
				cur = cur.next;
			}
		}
		return head;
	}
	
	public ListNode removeNth(ListNode head, int n){
		ListNode p1 = head;
		ListNode p2 = head;
		ListNode pre = head;
		for (int i = 0; i < n; i++){
			p1 = p1.next;
		}
		while(p1 != null){
			p1 = p1.next;
			pre = p2;
			p2 = p2.next;
		}
		pre.next = p2.next;
		return head;
	}
	
	public ListNode change(ListNode head){
		if (head == null || head.next == null){
			return head;
		}
		ListNode second = change(head.next.next);
		ListNode newHead = head.next;
		newHead.next = head;
		head.next = second;
		return newHead;
	}
	
	public ListNode mergeSortedList(ListNode head1, ListNode head2){
		ListNode newHead = new ListNode(-1);
		ListNode p1 = head1;
		ListNode p2 = head2;
		ListNode p = newHead;
		while(true){
			if (p1 == null || p2 == null){
				break;
			}
			if (p1.val < p2.val){
				p.next = p1;
				p1 = p1.next;
			}else{
				p.next = p2;
				p2 = p2.next;
			}
			p = p.next;
		}
		if (p1 == null){
			p.next = p2;
		}else{
			p.next = p1;
		}
		return newHead.next;
	}
	
	// ����Ҫ�ж��Ƿ��л�����Ҫ���ػ�����ڵ�ַ��
	public ListNode hasCycle(ListNode head){
		ListNode fast = head;
        ListNode slow = head;
        while(true){
            if (fast == null || fast.next == null){
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow){
                break;
            }
        }
        slow = head;                
        while (slow != fast){
            fast = fast.next;
            slow = slow.next;
        }
        
        return slow;
	}
	
	// ����һ��������map�ȴ�һ���������Ϣ����Ҫ�ö���洢��
	// ���������ж��Ƿ��л���
	// 		  ��û�л����ж�β�ڵ��Ƿ���ͬ����ͬ���ཻ���ýϳ�����������m-n����Ȼ��һ���ߣ����ĸ��ڵ�������ýڵ����ཻ�Ľڵ㣻
	//		  һ���л�һ��û�����϶����뽻���������л�����һ�������������У��жϻ�����ڵ��Ƿ�һ�£�
	//		  �л��ĵ�����һ���Ի���β��
	public boolean hasCross(ListNode head1, ListNode head2){
		return true;
	}
	
	// ɾ��δ���������е��ظ��ڵ㣬������hashset��hashmapҪ�ã�
	public ListNode removeDuplicate2(ListNode head){
		ListNode cur = head;
		ListNode pre = head;
		HashSet myset = new HashSet();
		while(cur != null){
			if (myset.add(head.val) == false){
				pre.next = head.next;
			}else{
				pre = cur;
			}
			cur = cur.next;
		}
		return head;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListAll myList = new ListAll();
		ListNode head = myList.create(10);
		ListNode cur = head;
		while(cur != null){
			System.out.print(cur.val);
			cur = cur.next;
		}
		System.out.println();
		ListNode reverseHead = myList.change(head);
		while(reverseHead != null){
			System.out.print(reverseHead.val);
			reverseHead = reverseHead.next;
		}
	}
}







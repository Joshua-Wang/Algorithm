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
	
	// 基于递归的链表反转，
	public ListNode reverse(ListNode head){
		if (head == null || head.next == null){
			return head;
		}
		ListNode second = reverse(head.next);
		head.next.next = head;
		head.next = null;
		return second;
	}
	
	// 非递归实现，三指针法，要知道三个指针怎么走，用哪个指针来判断循环，哪个指针是最后要返回的头结点；
	public ListNode reverse2(ListNode head){
		if (head == null || head.next == null){
			return head;
		}
		ListNode pre,cur,post;
		pre = head;
		cur = head.next;
		while(cur != null){      // 用cur来记录循环
			post = cur.next;     // post在循环里面赋值
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
	
	// 不仅要判断是否有环，还要返回环的入口地址；
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
	
	// 方法一：可以用map先存一个链表的信息；需要用额外存储；
	// 方法二：判断是否有环：
	// 		  都没有环，判断尾节点是否相同，相同则相交；让较长的链表先走m-n步，然后一起走，在哪个节点相遇则该节点是相交的节点；
	//		  一个有环一个没环，肯定不想交；两个都有环：则环一定是两个链表共有，判断环的入口点是否一致；
	//		  有环的单链表一定以环结尾；
	public boolean hasCross(ListNode head1, ListNode head2){
		return true;
	}
	
	// 删除未排序链表中的重复节点，这里用hashset比hashmap要好；
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







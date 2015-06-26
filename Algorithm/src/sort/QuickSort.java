package sort;

import java.util.Stack;

public class QuickSort {
	
	public int partition(int num[], int start, int end){
		int flag = num[start];
		int i,j;
		for (i = start+1, j = i; i <= end; i++){
			if (num[i] < flag){
				int tmp = num[i];
				num[i] = num[j];
				num[j] = tmp;
				j++;
			}
		}
		int tmp = num[start];
		num[start] = num[j-1];
		num[j-1] = tmp;
		return j-1;
	}

	public void quickSort(int num[], int start, int end){
		if (start <= end){
			int index = partition(num, start, end);
			quickSort(num, start, index-1);
			quickSort(num, index+1, end);
		}
	}
	
	public void nonRecrutQuickSort(int a[]) {
		if ( a == null || a.length <= 0){
			return ;
		}
		int start = 0;
		int end = a.length - 1;
		Stack<Integer> index = new Stack<Integer>(); // �ǵݹ飬����stackֻ�ᴴ��һ�������ᴴ�������
		index.push(start);
		index.push(end);
		while(!index.isEmpty()){
			end = index.pop();
			start = index.pop();
			int pos = partition(a, start, end);
			if (start < pos){
				index.push(start);
				index.push(pos-1);
			}
			if (pos < end){
				index.push(pos+1);
				index.push(end);
			}
		}
	}
	
	/**
	 * @param args
	 */
	// ˼�����Ҫ��������������������⣻
	// ���ŵĿռ临�Ӷ���O(nlogn)����Ҫ��Ϊ�ݹ�ջ�����ģ�
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {2,1,3,5,4};
		QuickSort qs = new QuickSort();
		qs.nonRecrutQuickSort(A); // ������length-1��Ҫ��֤end����ȡ����
		for (int i = 0; i < A.length; i++){
			System.out.println(A[i]);
		}
	}
}

class Qsort{
	public int partition(int num[], int begin, int end){
		int i,j;
		for (i = begin+1, j = i; i <= end; i++){
			if (num[i] < num[begin]){	
				// swap(num[i], num[j]);
				j++;
			}
		}
		// swap(num[j-1], num[begin]);
		return j - 1;
	}
	
	public void sort(int num[], int begin, int end){
		if (begin > end){
			return ;
		}
		int index = partition(num, begin, end);
		sort(num, begin, index-1);
		sort(num, index+1, end);
	}
}














































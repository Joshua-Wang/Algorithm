package sort;

public class MergeSort {

	// ¿Õ¼ä¸´ÔÓ¶ÈO(n)
	public void mergeSort(int num[], int start, int end){
		if (start < end){
			int mid = (start+end) / 2;
			mergeSort(num, start, mid);
			mergeSort(num, mid+1, end);
			merge(num, start, mid, end);
		}
	}
	
	public void merge(int num[], int start, int mid, int end){
		int temp[] = new int[100];
		int i = start, j = mid+1, k = 0;
		while(i <= mid && j <= end){
			if (num[i] < num[j]){
				temp[k++] = num[i++];
			}else{
				temp[k++] = num[j++];
			}
		}
		while(i <= mid){
			temp[k++] = num[i++];
		}
		while(j <= end){
			temp[k++] = num[j++];
		}
		for (int a = start, b = 0; a <= end; a++){
			num[a] = temp[b++];
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int test[] = {2,1,3,4,5};
		MergeSort testMerge = new MergeSort();
		testMerge.mergeSort(test, 0, test.length-1);
		for (int i = 0; i < test.length; i++){	
			System.out.println(test[i]);
		}
	}
}

class MSort{
	public void mergeSort(int[] num, int begin ,int end){
		if (begin > end){
			return ;
		}
		int mid = (begin + end) / 2;
		mergeSort(num, begin, mid);
		mergeSort(num, mid+1, end);
		merge(num, begin, mid, end);
	}
	
	public void merge(int[] num, int start, int mid, int end){
		int[] temp = new int[end];
		int i = start, j = mid+1;
		int k = 0;
		while(i <= mid && j <= end){
			if (num[i] < num[j]){
				temp[k++] = num[i++];
			}else{
				temp[k++] = num[j++];
			}
		}
		while(i <= mid){
			temp[k++] = num[i++];
		}
		while(j <= end){
			temp[k++] = num[j++];
		}
		int b;
		for (i = start, b = 0; i <= end; i++){
			num[i] = temp[b++];
		}
	}
}
































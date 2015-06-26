package interview;

public class FindMinRotate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// 循环数组找最小值，两次AC，
	// 开始自己写的也不顺，不要急，理清思路就能写出来；边界条件注意好；
	public int findMin(int[] num) {
        if (num.length <= 1){
            return num[0];
        }
        int left = 0;
        int right = num.length-1;
        while(left <= right){
            int mid = (left + right) / 2;
            boolean flag = true;
            if (mid > 0){
                flag = flag && (num[mid] < num[mid-1]);
            }
            if (mid < num.length-1){
                flag = flag && (num[mid] < num[mid+1]);
            }
            if (flag){
                return num[mid];
            }
            if (num[mid] >= num[left] && num[mid] >= num[right]){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return -1;
    }
	

	public int findMaxd(int[] data){
	    if (data.length == 0){
	        return -1;
	    }
	    int len = data.length;
	    int left = 0;
	    int right = data.length-1;
	    while(left <= right){
	        int mid = (left + right) / 2;
	        boolean flag1 = true;
	        boolean flag2 = true;
	        if (mid > 0 ){
	            flag1 = data[mid] > data[mid-1];
	        }
	        if (mid < len-1){
	            flag2 = data[mid] > data[mid+1];
	        }
	        if (flag1 && flag2){
	            return mid;
	        }
	        if (flag1 == false){
	            right = mid-1;
	        }else{
	            left = mid+1;
	        }
	    }
	    return -1; 
	}
}

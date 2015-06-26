package interview;

import java.util.HashSet;
import java.util.Set;

public class PrintUseID {

	private Set<Integer> myset = new HashSet<Integer>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int data[] = {2,3,4,5,6};
		new PrintUseID().printAll(data);
	}
	
	public void printAll(int data[]){
		
		int len = data.length;
		int res[] = new int[len];
		dfs(0, data,res);
	}
	
	public void dfs(int index, int[] data, int[] res){
		if (index == data.length){
			for (int i = 0; i < index; i++){
				System.out.print(res[i]);
			}
			System.out.println();
			return ;
		}
		for (int i = 1; i <= data[index]; i++){
			if (!myset.contains(i)){
				myset.add(i);
				res[index] = i;
				dfs(index+1, data, res);
				myset.remove(i);
			}
		}
	}
}

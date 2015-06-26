package interview;

public class Joseph {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(lastRemainning(3,5));
	}
	
	// 0��n-1��n�������ų�һȦ����0��ʼ��1������mʱɾ���������������һ�����֣�
	// �ݹ鹫ʽ f(n,m) = (f(n-1,m) + m) % n;
	public static int lastRemainning(int m, int n){
		if (m < 1 || n < 1){
			return -1;
		}
		int last = 0; // i = 1����������Ľ��һ���ǵ�һ���ˣ����Ա����0��
		for (int i = 2; i <= n; i++){
			last = (last + m) % i;
		}
		return last; // ����Ǵ�1~N��ţ���ôreturn last+1;
		
	}
}

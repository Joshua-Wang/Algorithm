package interview;

public class SingleNumber2 {
	
	// �ǳ�ͨ�õķ������Ż����������ƣ�
	// ������Ժ�Ҫ��дһд��
	public int singleNumber(int[] A) {
		int bits[] = new int[32];
		int res = 0;
		for (int i = 0; i < 32; i++){
			for (int j = 0; j < A.length; j++){
				bits[i] += (A[j]>>i) & 1;
			}
			res = res | (bits[i]%3)<<i;
		}
		return res;
    }

}

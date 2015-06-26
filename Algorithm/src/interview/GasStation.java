package interview;

public class GasStation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// Ò»´ÎAC
	public int canCompleteCircuit(int[] gas, int[] cost) { 
		int len = gas.length;
		int gasNeed = 0;
		int gasHave = 0;
		for (int i = 0; i < len; i++){
			gasNeed += cost[i];
			gasHave += gas[i];
		}
		if (gasNeed > gasHave){
			return -1;
		}
		gasNeed = gasHave = 0;
		int index = 0;
		for (int i = 0; i < len; i++){
			gasNeed += cost[i];
			gasHave += gas[i];
			if (gasNeed > gasHave){
				index = i+1;
				gasNeed = gasHave = 0;
			}
		}
		return index;
    }
}

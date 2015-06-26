package interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import interview.InsertInterval;

public class MergeIntervals {
	
	// ������Ļ��� O(n)��ɨһ��Ϳ����ˣ�
	public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        Collections.sort(intervals, new mysort());
        int len = intervals.size();
        ArrayList<Interval> res = new ArrayList<Interval>();
        if (intervals == null || intervals.size() == 0){
        	return res;
        }
        Interval tmp = intervals.get(0);
        
        for (int i = 1; i < len; i++){
        	// ������ж�����Ҫ�ر�ע��
        	if (intervals.get(i).start <= tmp.end){
        		tmp.end = Math.max(tmp.end, intervals.get(i).end);
        	}else{
        		res.add(tmp);
        		tmp = intervals.get(i);
        	}
        }
        res.add(tmp);
        return res;
	}
}

class mysort implements Comparator{

	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		Interval i1 = (Interval)o1;
		Interval i2 = (Interval)o2;
		if (i1.start > i2.start){
			return 1;
		}else if (i1.start == i2.start){
			return 0;
		}else{
			return -1;
		}
	}
}

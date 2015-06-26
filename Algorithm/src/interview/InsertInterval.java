package interview;

import java.util.ArrayList;
import java.util.ListIterator;

import org.junit.Test;

class Interval {
	int start;
	int end;
	Interval() { start = 0; end = 0; }
	Interval(int s, int e) { start = s; end = e; }
}

public class InsertInterval {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// 思路有，但是写起来挺麻烦的；
	public ArrayList<Interval> insert2(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> res = new ArrayList<Interval>();
        if (intervals.size() == 0){
        	res.add(newInterval);
        	return res;
        }
        int index = search(intervals, newInterval);
        for (int i = 0; i < index; i++){
        	res.add(intervals.get(i));
        }
        int newStart = newInterval.start;
        int newEnd = newInterval.end;
        if (index == -1){
        	index = 0;
        }
        while (index < intervals.size()){
        	if (overlap(intervals.get(index),newInterval)){
        		newStart = Math.min(intervals.get(index).start, newInterval.start);
        		newEnd = Math.max(intervals.get(index).end, newInterval.end);
        		index++;
        	}else{
        		break;
        	}
        }
        res.add(new Interval(newStart, newEnd));
        for (int i = index; i < intervals.size(); i++){
        	res.add(intervals.get(i));
        }
        return res;
    }
	
	public boolean overlap(Interval p1, Interval p2){
		if(p1.start > p2.end || p1.end < p2.start){
			return false;
		}else{
			return true;
		}
	}
	
	public int search(ArrayList<Interval> intervals, Interval newInterval){
		int left = 0;
		int right = intervals.size()-1;
		int start = newInterval.start;
		while (left <= right){
			int mid = (left+right) / 2;
			if (newInterval.start == intervals.get(mid).start){
				return mid;
			}else if (newInterval.start > intervals.get(mid).start){
				left = mid+1;
			}else
				right = mid -1 ;
		}
		return right;
	}
	
	@Test
	public void test(){
		ArrayList<Interval> intervals = new ArrayList<Interval>();
		intervals.add(new Interval(1,5));
		insert(intervals, new Interval(0,3));
	}
	
	// 不用额外空间的方法； ListIterator 
	// 这题还是很不错好做，还是抄答案的，有空再写一遍；这种写法很经典，没有用额外空间，而且代码简洁；
	public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
		if (intervals.size() == 0){
			intervals.add(newInterval);
			return intervals;
		}
		ListIterator<Interval> iter = intervals.listIterator();
		while (iter.hasNext()){
			Interval tmp = iter.next();
			if (newInterval.end < tmp.start){
				iter.previous();
				iter.add(newInterval);
				return intervals;
			}
			if (newInterval.start > tmp.end){
				continue;
			}
			newInterval.start = Math.min(tmp.start, newInterval.start);
			newInterval.end = Math.max(tmp.end, newInterval.end);
			iter.remove();
		}
		intervals.add(newInterval);
		return intervals;
	}
}

























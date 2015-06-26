package interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import org.junit.Test;

public class LRUCache {
    HashMap<Integer, Integer> map;
    ArrayList<Integer> list;
    int capacity;
    
    public LRUCache(int capacity) {
        map = new HashMap<Integer, Integer>(capacity);
        list = new ArrayList<Integer>(capacity);
        this.capacity = capacity;
    }
    
    // List肯定是不重复的；
    // 先从map开始找；
    public int get(int key) {
        if (map.get(key) == null){
        	return -1;
        }
        // 原来关键点在这儿，必须写成new Integer(key)
        // remove方法有两个重载：remove(int index) :删除下标为index的元素；remove(Object e) 删除e这个元素；
        // 所以这里要删除 new Integer(key);
        list.remove(new Integer(key));  
        list.add(key);
        return map.get(key);
    }
    
    public void set(int key, int value) {
    	if (map.get(key) != null) {
    		map.put(key, value);
    		list.remove(new Integer(key)); 
            list.add(key);
    	} else {
    		if (map.size() < capacity) {
    			map.put(key, value);
    			list.add(key);
    		} else {
    			int leastkey = list.remove(0);
                list.add(key);
                map.remove(leastkey); // map 里面也要删；
                map.put(key, value);
    		}
    	}
    }
}



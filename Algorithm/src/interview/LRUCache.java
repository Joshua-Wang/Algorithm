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
    
    // List�϶��ǲ��ظ��ģ�
    // �ȴ�map��ʼ�ң�
    public int get(int key) {
        if (map.get(key) == null){
        	return -1;
        }
        // ԭ���ؼ��������������д��new Integer(key)
        // remove�������������أ�remove(int index) :ɾ���±�Ϊindex��Ԫ�أ�remove(Object e) ɾ��e���Ԫ�أ�
        // ��������Ҫɾ�� new Integer(key);
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
                map.remove(leastkey); // map ����ҲҪɾ��
                map.put(key, value);
    		}
    	}
    }
}



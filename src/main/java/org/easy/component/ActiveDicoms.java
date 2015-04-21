package org.easy.component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ActiveDicoms {

	private ConcurrentHashMap<String,String> map = null;
	
	public ActiveDicoms(){		
		setList(new ConcurrentHashMap<String,String>());
	}

	public Map<String,String> getList() {
		return map;
	}

	public void setList(ConcurrentHashMap<String,String> list) {
		this.map = list;
	}
	
	public Integer getSize(){
		return map.size();
	}
	
	public void add(String key, String value){
		this.map.put(key, value);
	}
	
	public void remove(String key){
		this.map.remove(key);
	}
	
	@Override
	public String toString(){
		
		StringBuffer buffer = new StringBuffer();
		for (Map.Entry<String, String> entry : map.entrySet()) { 
			buffer.append(entry.getValue());
			//System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
		}
		
		return buffer.toString();
	}
}

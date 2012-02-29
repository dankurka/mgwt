package com.googlecode.mgwt.storage.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalStorageImplForTests implements Storage{

	private Map<String, String> storage;
	private List<String> keyList;
	
	public LocalStorageImplForTests() {
		storage = new HashMap<String, String>();
		keyList = new ArrayList<String>();
	}
	
	@Override
	public boolean isSupported() {
		return true;
	}

	@Override
	public void clear() {
		storage.clear();
		keyList.clear();
	}

	@Override
	public String getItem(String key) {
		return storage.get(key);
	}

	@Override
	public int getLength() {
		return storage.size();
	}

	@Override
	public String key(int index) {
		if(index < 0)
			return null;
		if(index >= keyList.size())
			return null;
		return keyList.get(index);
	}

	@Override
	public void removeItem(String key) {
		String remove = storage.remove(key);
		if(remove != null)
			keyList.remove(remove);
		
	}

	@Override
	public void setItem(String key, String data) {
		String put = storage.put(key, data);
		if(put == null){
			keyList.add(key);
		}
		
	}
	
}

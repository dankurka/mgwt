package com.googlecode.mgwt.storage.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation for testing on localstorage
 * @author Daniel Kurka
 *
 */
public class LocalStorageImplForTests implements Storage{

	private Map<String, String> storage;
	private List<String> keyList;
	
	public LocalStorageImplForTests() {
		storage = new HashMap<String, String>();
		keyList = new ArrayList<String>();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.storage.client.Storage#isSupported()
	 */
	@Override
	public boolean isSupported() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.storage.client.Storage#clear()
	 */
	@Override
	public void clear() {
		storage.clear();
		keyList.clear();
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.storage.client.Storage#getItem(java.lang.String)
	 */
	@Override
	public String getItem(String key) {
		return storage.get(key);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.storage.client.Storage#getLength()
	 */
	@Override
	public int getLength() {
		return storage.size();
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.storage.client.Storage#key(int)
	 */
	@Override
	public String key(int index) {
		if(index < 0)
			return null;
		if(index >= keyList.size())
			return null;
		return keyList.get(index);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.storage.client.Storage#removeItem(java.lang.String)
	 */
	@Override
	public void removeItem(String key) {
		String remove = storage.remove(key);
		if(remove != null)
			keyList.remove(remove);
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.storage.client.Storage#setItem(java.lang.String, java.lang.String)
	 */
	@Override
	public void setItem(String key, String data) {
		String put = storage.put(key, data);
		if(put == null){
			keyList.add(key);
		}
		
	}
	
	/**
	 * get the internal list that keeps track of used keys
	 * @return
	 */
	public List<String> getKeyList() {
		return keyList;
	}
	
	/**
	 * get the internal map that keeps track of values
	 * @return
	 */
	public Map<String, String> getStorage() {
		return storage;
	}
	
}

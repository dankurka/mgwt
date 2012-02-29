package com.googlecode.mgwt.storage.client;

public class LocalStorageGwtImpl implements Storage{

	private com.google.gwt.storage.client.Storage storage;

	public LocalStorageGwtImpl() {
		storage = com.google.gwt.storage.client.Storage.getLocalStorageIfSupported();
		if(storage == null)
			throw new IllegalStateException("no storage support");
	}
	
	

	@Override
	public boolean isSupported() {
		return com.google.gwt.storage.client.Storage.isSupported();
	}

	@Override
	public void clear() {
		storage.clear();
		
	}

	@Override
	public String getItem(String key) {
		return storage.getItem(key);
	}

	@Override
	public int getLength() {
		return storage.getLength();
	}

	@Override
	public String key(int index) {
		return storage.key(index);
	}

	@Override
	public void removeItem(String key) {
		storage.removeItem(key);
		
	}

	@Override
	public void setItem(String key, String data) {
		storage.setItem(key, data);
		
	}

}

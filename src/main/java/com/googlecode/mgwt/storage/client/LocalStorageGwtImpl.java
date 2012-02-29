package com.googlecode.mgwt.storage.client;

/**
 * Storage implementation that uses the gwt storage implementation.
 * @author Daniel Kurka
 *
 */
public class LocalStorageGwtImpl implements Storage{

	private com.google.gwt.storage.client.Storage storage;

	public LocalStorageGwtImpl() {
		storage = com.google.gwt.storage.client.Storage.getLocalStorageIfSupported();
		if(storage == null)
			throw new IllegalStateException("no storage support");
	}
	
	

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.storage.client.Storage#isSupported()
	 */
	@Override
	public boolean isSupported() {
		return com.google.gwt.storage.client.Storage.isSupported();
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.storage.client.Storage#clear()
	 */
	@Override
	public void clear() {
		storage.clear();
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.storage.client.Storage#getItem(java.lang.String)
	 */
	@Override
	public String getItem(String key) {
		return storage.getItem(key);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.storage.client.Storage#getLength()
	 */
	@Override
	public int getLength() {
		return storage.getLength();
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.storage.client.Storage#key(int)
	 */
	@Override
	public String key(int index) {
		return storage.key(index);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.storage.client.Storage#removeItem(java.lang.String)
	 */
	@Override
	public void removeItem(String key) {
		storage.removeItem(key);
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.storage.client.Storage#setItem(java.lang.String, java.lang.String)
	 */
	@Override
	public void setItem(String key, String data) {
		storage.setItem(key, data);
		
	}

}

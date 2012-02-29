package com.googlecode.mgwt.storage.client;

/**
 * Interface for using LocalStorage & SessionStorage.
 * 
 * This exists for testing reasons and will use the GWT Standard Implementation if running inside a browser.
 * 
 * 
 * 
 * @author Daniel Kurka
 *
 */
public interface Storage {



	/**
	 * Returns <code>true</code> if the Storage API (both localStorage and
	 * sessionStorage) is supported on the running platform.
	 */
	public boolean isSupported();

	/**
	 * Removes all items in the Storage.
	 *
	 * @see <a href="http://www.w3.org/TR/webstorage/#dom-storage-clear">W3C Web
	 *      Storage - Storage.clear()</a>
	 */
	public void clear();

	/**
	 * Returns the item in the Storage associated with the specified key.
	 *
	 * @param key the key to a value in the Storage
	 * @return the value associated with the given key
	 * @see <a href="http://www.w3.org/TR/webstorage/#dom-storage-getitem">W3C Web
	 *      Storage - Storage.getItem(k)</a>
	 */
	public String getItem(String key);

	/**
	 * Returns the number of items in this Storage.
	 *
	 * @return number of items in this Storage
	 * @see <a href="http://www.w3.org/TR/webstorage/#dom-storage-l">W3C Web
	 *      Storage - Storage.length()</a>
	 */
	public int getLength();

	/**
	 * Returns the key at the specified index.
	 *
	 * @param index the index of the key
	 * @return the key at the specified index in this Storage
	 * @see <a href="http://www.w3.org/TR/webstorage/#dom-storage-key">W3C Web
	 *      Storage - Storage.key(n)</a>
	 */
	public String key(int index);

	/**
	 * Removes the item in the Storage associated with the specified key.
	 *
	 * @param key the key to a value in the Storage
	 * @see <a href="http://www.w3.org/TR/webstorage/#dom-storage-removeitem">W3C
	 *      Web Storage - Storage.removeItem(k)</a>
	 */
	public void removeItem(String key);

	/**
	 * Sets the value in the Storage associated with the specified key to the
	 * specified data.
	 *
	 * Note: The empty string may not be used as a key.
	 *
	 * @param key the key to a value in the Storage
	 * @param data the value associated with the key
	 * @see <a href="http://www.w3.org/TR/webstorage/#dom-storage-setitem">W3C Web
	 *      Storage - Storage.setItem(k,v)</a>
	 */
	public void setItem(String key, String data);
}

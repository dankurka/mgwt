/**
 * 13.11.2010
 * created by kurt
 */
package de.kurka.mobile.showcase.client;

import java.io.Serializable;

/**
 * @author kurt
 *
 */
public class Topic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -134211444131752658L;

	private String name;

	private int count;

	public Topic() {

	}

	/**
	 * @param string
	 * @param i
	 */
	public Topic(String name, int count) {
		this.name = name;
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}

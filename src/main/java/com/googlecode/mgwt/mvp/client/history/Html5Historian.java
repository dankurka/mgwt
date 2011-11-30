package com.googlecode.mgwt.mvp.client.history;

import com.google.web.bindery.event.shared.HandlerRegistration;

public interface Html5Historian {
	public void forward();

	public void back();

	public void go(int number);

	public int length();

	public void pushState(String data, String title, String url);

	public void replaceState(String data, String title, String url);

	public HandlerRegistration addPopStateHandler(PopStateHandler handler);



}

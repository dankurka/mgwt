package com.googlecode.mgwt.mvp.client.history;

import com.google.web.bindery.event.shared.Event;

public class PopStateEvent extends Event<PopStateHandler> {

	private static final Type<PopStateHandler> TYPE = new Type<PopStateHandler>();
	private final String data;
	private final String title;
	private final String url;

	public static Type<PopStateHandler> getType() {
		return TYPE;
	}

	public PopStateEvent(String data, String title, String url) {
		this.data = data;
		this.title = title;
		this.url = url;
	}

	@Override
	public com.google.web.bindery.event.shared.Event.Type<PopStateHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(PopStateHandler handler) {
		handler.onPopStateEvent(this);

	}

	public String getData() {
		return data;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

}

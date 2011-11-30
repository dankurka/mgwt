package com.googlecode.mgwt.mvp.client.history;

import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class Html5HistorianImpl implements Html5Historian {

	private EventBus eventBus = new SimpleEventBus();

	public Html5HistorianImpl() {
		bind();
	}

	private native void bind() /*-{
		var that = this;
		$wnd.addEventListener( 'popstate', function(event) {
			alert("pop");
			var data = "";
			
			if(event.state != null)
			{
				data = event.state;
			}
			that.@com.googlecode.mgwt.mvp.client.history.Html5HistorianImpl::onPopState(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(data, event.title, event.url);

		});

	}-*/;

	private void onPopState(String data, String title, String url) {
		eventBus.fireEvent(new PopStateEvent(data, title, url));
	}

	@Override
	public native void forward() /*-{
		$wnd.history.forward();
	}-*/;

	@Override
	public native void back() /*-{
		$wnd.history.back();
	}-*/;

	@Override
	public native void go(int number) /*-{
		$wnd.history.go(number);
	}-*/;

	@Override
	public native int length() /*-{
		return $wnd.history.length;
	}-*/;

	@Override
	public native void pushState(String data, String title, String url) /*-{
		alert("push: '" + url + "' data: '" + data + "'");
		$wnd.history.pushState(data, title, url);
	}-*/;

	@Override
	public native void replaceState(String data, String title, String url) /*-{
		$wnd.history.replaceState(data, title, url);
	}-*/;

	@Override
	public HandlerRegistration addPopStateHandler(PopStateHandler handler) {
		return eventBus.addHandler(PopStateEvent.getType(), handler);

	}

	protected native String decodeFragment(String encodedFragment) /*-{
		// decodeURI() does *not* decode the '#' character.
		return decodeURI(encodedFragment.replace("%23", "#"));
	}-*/;

	protected native String encodeFragment(String fragment) /*-{
		// encodeURI() does *not* encode the '#' character.
		return encodeURI(fragment).replace("#", "%23");
	}-*/;


}

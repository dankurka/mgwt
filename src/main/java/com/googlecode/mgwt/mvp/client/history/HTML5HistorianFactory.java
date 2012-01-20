package com.googlecode.mgwt.mvp.client.history;


public class HTML5HistorianFactory {

	public static Html5Historian getHistorian() {
		if (hasHtml5HistorySupport()) {
			return new Html5HistorianImpl();
		}
		return new HTML5HistorianLegacyImpl();
	}

	private native static boolean hasHtml5HistorySupport()/*-{
		return typeof ($wnd.history.pushState) != "undefined";
	}-*/;
}
	
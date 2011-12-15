package com.googlecode.mgwt.linker.client.cache.html5;

import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.googlecode.mgwt.linker.client.cache.ApplicationCache;
import com.googlecode.mgwt.linker.client.cache.ApplicationCacheStatus;
import com.googlecode.mgwt.linker.client.cache.event.CachedEvent;
import com.googlecode.mgwt.linker.client.cache.event.CheckingEvent;
import com.googlecode.mgwt.linker.client.cache.event.CheckingEvent.Handler;
import com.googlecode.mgwt.linker.client.cache.event.DownloadingEvent;
import com.googlecode.mgwt.linker.client.cache.event.ErrorEvent;
import com.googlecode.mgwt.linker.client.cache.event.NoUpadateEvent;
import com.googlecode.mgwt.linker.client.cache.event.ObsoluteEvent;
import com.googlecode.mgwt.linker.client.cache.event.ProgressEvent;
import com.googlecode.mgwt.linker.client.cache.event.UpdateReadyEvent;

public class Html5ApplicationCache implements ApplicationCache {

	public static final ApplicationCacheStatus[] STATUS_MAPPING = new ApplicationCacheStatus[] { ApplicationCacheStatus.UNCACHED, ApplicationCacheStatus.IDLE, ApplicationCacheStatus.CHECKING,
			ApplicationCacheStatus.DOWNLOADING, ApplicationCacheStatus.UPDATEREADY, ApplicationCacheStatus.OBSOLTE };

	protected EventBus eventBus = new SimpleEventBus();

	public Html5ApplicationCache() {
		initialize();
	}

	@Override
	public ApplicationCacheStatus getStatus() {
		int status0 = getStatus0();
		return STATUS_MAPPING[status0];
	}

	@Override
	public HandlerRegistration addCheckingHandler(Handler handler) {
		return eventBus.addHandler(CheckingEvent.getType(), handler);
	}

	@Override
	public HandlerRegistration addCachedHandler(com.googlecode.mgwt.linker.client.cache.event.CachedEvent.Handler handler) {
		return eventBus.addHandler(CachedEvent.getType(), handler);
	}

	@Override
	public HandlerRegistration addDownloadingHandler(com.googlecode.mgwt.linker.client.cache.event.DownloadingEvent.Handler handler) {
		return eventBus.addHandler(DownloadingEvent.getType(), handler);
	}

	@Override
	public HandlerRegistration addErrorHandler(com.googlecode.mgwt.linker.client.cache.event.ErrorEvent.Handler handler) {
		return eventBus.addHandler(ErrorEvent.getType(), handler);
	}

	@Override
	public HandlerRegistration addNoUpdateHandler(com.googlecode.mgwt.linker.client.cache.event.NoUpadateEvent.Handler handler) {
		return eventBus.addHandler(NoUpadateEvent.getType(), handler);
	}

	@Override
	public HandlerRegistration addObsoluteHandler(com.googlecode.mgwt.linker.client.cache.event.ObsoluteEvent.Handler handler) {
		return eventBus.addHandler(ObsoluteEvent.getType(), handler);
	}

	@Override
	public HandlerRegistration addProgressHandler(com.googlecode.mgwt.linker.client.cache.event.ProgressEvent.Handler handler) {
		return eventBus.addHandler(ProgressEvent.getType(), handler);
	}

	@Override
	public HandlerRegistration addUpdateReadyHandler(com.googlecode.mgwt.linker.client.cache.event.UpdateReadyEvent.Handler handler) {
		return eventBus.addHandler(UpdateReadyEvent.getType(), handler);
	}

	protected native int getStatus0()/*-{
		return $wnd.applicationCache.status;
	}-*/;

	protected void onChecking() {
		eventBus.fireEventFromSource(new CheckingEvent(), this);
	}

	protected void onError() {
		eventBus.fireEventFromSource(new ErrorEvent(), this);
	}

	protected void onNoUpadte() {
		eventBus.fireEventFromSource(new NoUpadateEvent(), this);
	}

	protected void onDownloading() {
		eventBus.fireEventFromSource(new DownloadingEvent(), this);
	}

	protected void onProgress() {
		eventBus.fireEventFromSource(new ProgressEvent(), this);
	}

	protected void onUpdateReady() {
		eventBus.fireEventFromSource(new UpdateReadyEvent(), this);
	}

	protected void onCached() {
		eventBus.fireEventFromSource(new CachedEvent(), this);
	}

	protected void onObsolete() {
		eventBus.fireEventFromSource(new ObsoluteEvent(), this);
	}

	protected native void initialize() /*-{
	var that = this;
	var oncheckOld = $wnd.applicationCache.onchecking;
	$wnd.applicationCache.onchecking = $entry(function() {
		that.@com.googlecode.mgwt.linker.client.cache.html5.Html5ApplicationCache::onChecking()();
		if (oncheckOld) {
			oncheckOld();
		}
	});

	var onErrorHandler = $wnd.applicationCache.onerror;
	$wnd.applicationCache.onerror = $entry(function() {
		that.@com.googlecode.mgwt.linker.client.cache.html5.Html5ApplicationCache::onError()();
		if (onErrorHandler) {
			onErrorHandler();
		}
	});

	var onNoUpdateHandler = $wnd.applicationCache.onnoupdate;
	$wnd.applicationCache.onnoupdate = $entry(function() {
		that.@com.googlecode.mgwt.linker.client.cache.html5.Html5ApplicationCache::onNoUpadte()();
		if (onNoUpdateHandler) {
			onNoUpdateHandler();
		}
	});

	var onDownloadingHandler = $wnd.applicationCache.ondownloading;
	$wnd.applicationCache.ondownloading = $entry(function() {
		that.@com.googlecode.mgwt.linker.client.cache.html5.Html5ApplicationCache::onDownloading()();
		if (onDownloadingHandler) {
			onDownloadingHandler();
		}
	});

	var onProgressHandler = $wnd.applicationCache.onprogress;
	$wnd.applicationCache.onprogress = $entry(function() {
		that.@com.googlecode.mgwt.linker.client.cache.html5.Html5ApplicationCache::onProgress()();
		if (onProgressHandler) {
			onProgressHandler();
		}
	});

	var onUpdateReadyHandler = $wnd.applicationCache.onupdateready;
	$wnd.applicationCache.onupdateready = function() {
		($entry(that.@com.googlecode.mgwt.linker.client.cache.html5.Html5ApplicationCache::onUpdateReady()))();
		if (onUpdateReadyHandler) {
			onUpdateReadyHandler();
		}
	};
	
	var onCachedHandler = $wnd.applicationCache.oncached;
	$wnd.applicationCache.oncached = function() {
		($entry(that.@com.googlecode.mgwt.linker.client.cache.html5.Html5ApplicationCache::onCached()))();
		if (onCachedHandler) {
			onCachedHandler();
		}
	};
	
	var onObsoleteHandler = $wnd.applicationCache.onobsolete;
	$wnd.applicationCache.onobsolete = function() {
		($entry(that.@com.googlecode.mgwt.linker.client.cache.html5.Html5ApplicationCache::onCached()))();
		if (onObsoleteHandler) {
			onObsoleteHandler();
		}
	};

}-*/;

}

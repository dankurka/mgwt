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
import com.googlecode.mgwt.linker.client.cache.event.NoUpdateEvent;
import com.googlecode.mgwt.linker.client.cache.event.ObsoleteEvent;
import com.googlecode.mgwt.linker.client.cache.event.ProgressEvent;
import com.googlecode.mgwt.linker.client.cache.event.UpdateReadyEvent;

public class Html5ApplicationCache implements ApplicationCache {

  private static final ApplicationCacheStatus[] STATUS_MAPPING = new ApplicationCacheStatus[] {
      ApplicationCacheStatus.UNCACHED, ApplicationCacheStatus.IDLE, ApplicationCacheStatus.CHECKING, ApplicationCacheStatus.DOWNLOADING, ApplicationCacheStatus.UPDATEREADY,
      ApplicationCacheStatus.OBSOLETE};

  public static Html5ApplicationCache createIfSupported() {
    if (!isSupported()) {
      return null;
    }
    return new Html5ApplicationCache();
  }

  protected static native boolean isSupported()/*-{
		return typeof ($wnd.applicationCache) == "object";
  }-*/;

  protected EventBus eventBus = new SimpleEventBus();

  protected Html5ApplicationCache() {
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
  public HandlerRegistration addNoUpdateHandler(com.googlecode.mgwt.linker.client.cache.event.NoUpdateEvent.Handler handler) {
    return eventBus.addHandler(NoUpdateEvent.getType(), handler);
  }

  @Override
  public HandlerRegistration addObsoleteHandler(com.googlecode.mgwt.linker.client.cache.event.ObsoleteEvent.Handler handler) {
    return eventBus.addHandler(ObsoleteEvent.getType(), handler);
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

  protected void onNoUpdate() {
    eventBus.fireEventFromSource(new NoUpdateEvent(), this);
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
    eventBus.fireEventFromSource(new ObsoleteEvent(), this);
  }

  protected native void initialize() /*-{
		var that = this;

		var check = $entry(function() {
			that.@com.googlecode.mgwt.linker.client.cache.html5.Html5ApplicationCache::onChecking()();
		});
		$wnd.applicationCache.addEventListener("checking", check);

		var onError = $entry(function() {
			that.@com.googlecode.mgwt.linker.client.cache.html5.Html5ApplicationCache::onError()();

		});
		$wnd.applicationCache.addEventListener("onerror", onError);

		var onUpdate = $entry(function() {
			that.@com.googlecode.mgwt.linker.client.cache.html5.Html5ApplicationCache::onNoUpdate()();

		});
		$wnd.applicationCache.addEventListener("onnoupdate", onUpdate);

		var ondownloading = $entry(function() {
			that.@com.googlecode.mgwt.linker.client.cache.html5.Html5ApplicationCache::onDownloading()();
		});
		$wnd.applicationCache.addEventListener("ondownloading", ondownloading);

		var onprogress = $entry(function() {
			that.@com.googlecode.mgwt.linker.client.cache.html5.Html5ApplicationCache::onProgress()();
		});
		$wnd.applicationCache.addEventListener("onprogress", onprogress);

		var onupdateReady = $entry(function() {
			that.@com.googlecode.mgwt.linker.client.cache.html5.Html5ApplicationCache::onUpdateReady()();
		});
		$wnd.applicationCache.addEventListener("onupdateready", onupdateReady);

		var oncached = $entry(function() {
			that.@com.googlecode.mgwt.linker.client.cache.html5.Html5ApplicationCache::onCached()();
		});
		$wnd.applicationCache.addEventListener("oncached", oncached);

		var onobsolete = $entry(function() {
			that.@com.googlecode.mgwt.linker.client.cache.html5.Html5ApplicationCache::onObsolete()();
		});
		$wnd.applicationCache.addEventListener("onobsolete", onobsolete);

  }-*/;

  @Override
  public native void swapCache() /*-{
		$wnd.applicationCache.swapCache();
  }-*/;
  
  @Override
  public native void update() /*-{
		$wnd.applicationCache.update();
  }-*/;

}

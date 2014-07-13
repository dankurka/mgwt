package com.googlecode.mgwt.linker.client.cache;

import com.google.web.bindery.event.shared.HandlerRegistration;

import com.googlecode.mgwt.linker.client.cache.event.CachedEvent;
import com.googlecode.mgwt.linker.client.cache.event.CheckingEvent;
import com.googlecode.mgwt.linker.client.cache.event.DownloadingEvent;
import com.googlecode.mgwt.linker.client.cache.event.ErrorEvent;
import com.googlecode.mgwt.linker.client.cache.event.NoUpdateEvent;
import com.googlecode.mgwt.linker.client.cache.event.ObsoleteEvent;
import com.googlecode.mgwt.linker.client.cache.event.ProgressEvent;
import com.googlecode.mgwt.linker.client.cache.event.UpdateReadyEvent;

public interface ApplicationCache {
	ApplicationCacheStatus getStatus();

	void swapCache();

	void update();

	HandlerRegistration addCheckingHandler(CheckingEvent.Handler handler);

	HandlerRegistration addCachedHandler(CachedEvent.Handler handler);

	HandlerRegistration addDownloadingHandler(DownloadingEvent.Handler handler);

	HandlerRegistration addErrorHandler(ErrorEvent.Handler handler);

	HandlerRegistration addNoUpdateHandler(NoUpdateEvent.Handler handler);

	HandlerRegistration addObsoleteHandler(ObsoleteEvent.Handler handler);

	HandlerRegistration addProgressHandler(ProgressEvent.Handler handler);

	HandlerRegistration addUpdateReadyHandler(UpdateReadyEvent.Handler handler);
}

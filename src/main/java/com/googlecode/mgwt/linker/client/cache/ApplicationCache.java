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
	public ApplicationCacheStatus getStatus();

	public void swapCache();
	
	public HandlerRegistration addCheckingHandler(CheckingEvent.Handler handler);

	public HandlerRegistration addCachedHandler(CachedEvent.Handler handler);

	public HandlerRegistration addDownloadingHandler(DownloadingEvent.Handler handler);

	public HandlerRegistration addErrorHandler(ErrorEvent.Handler handler);

	public HandlerRegistration addNoUpdateHandler(NoUpdateEvent.Handler handler);

	public HandlerRegistration addObsoleteHandler(ObsoleteEvent.Handler handler);

	public HandlerRegistration addProgressHandler(ProgressEvent.Handler handler);

	public HandlerRegistration addUpdateReadyHandler(UpdateReadyEvent.Handler handler);
}

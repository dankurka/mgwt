package com.googlecode.mgwt.ui.client.util;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ImageLoader {

	private boolean canceled;
	private boolean started;

	private ImageElement imgElement;
	private AsyncCallback<IsImage> callback;

	public ImageLoader() {

	}

	public void loadImage(String url, AsyncCallback<IsImage> callback) {
		if (started) {
			throw new IllegalStateException("this loader is already loading an image");
		}
		if (callback == null) {
			throw new IllegalArgumentException("callback can not be null");
		}
		started = true;
		canceled = false;
		imgElement = ImageElement.as(Document.get().createElement("img"));
		this.callback = callback;
		loadImage0(imgElement, url);
	}

	public void cancel() {
		canceled = true;
	}

	protected void onError() {
		started = false;
		if (canceled)
			return;
		callback.onFailure(new Exception());
	}

	protected void onLoad() {
		started = false;
		if (canceled)
			return;

		callback.onSuccess(new ImageElementWrapper(imgElement));
	}

	private native void loadImage0(ImageElement img, String url)/*-{
		var instance = this;

		img.onload = $entry(function() {
			img.onload = null;
			instance.@com.googlecode.mgwt.ui.client.util.ImageLoader::onLoad()();
		});

		img.onerror = $entry(function() {
			img.onerror = null;

			instance.@com.googlecode.mgwt.ui.client.util.ImageLoader::onError()();
		});

		img.src = url;
	}-*/;

	private class ImageElementWrapper implements IsImage {

		private final ImageElement element;

		public ImageElementWrapper(ImageElement element) {
			this.element = element;

		}

		@Override
		public ImageElement getElement() {
			return element;
		}

	}

}

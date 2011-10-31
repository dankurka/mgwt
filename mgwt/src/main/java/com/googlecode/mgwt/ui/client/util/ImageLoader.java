/*
 * 
 * Copyright 2011 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.mgwt.ui.client.util;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * A utility class to load images from a remote resource
 * 
 * @author Daniel Kurka
 * 
 */
public class ImageLoader {

	private static class ImageElementWrapper implements IsImage {

		private final ImageElement element;

		public ImageElementWrapper(ImageElement element) {
			this.element = element;

		}

		@Override
		public ImageElement getElement() {
			return element;
		}

	}

	private boolean canceled;
	private boolean started;

	private ImageElement imgElement;
	private AsyncCallback<IsImage> callback;

	/**
	 * Construct a loader
	 */
	public ImageLoader() {

	}

	/**
	 * load an image from a given url
	 * 
	 * @param url the url of the image
	 * @param callback the callback to call when image loading has finished /
	 *            failed
	 */
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

	/**
	 * Cancel the load of an image
	 * 
	 */
	public void cancel() {
		canceled = true;
		if (started && imgElement != null) {
			imgElement.setSrc("");
		}
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

}

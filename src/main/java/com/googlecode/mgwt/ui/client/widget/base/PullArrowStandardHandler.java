/*
 * Copyright 2012 Daniel Kurka
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
package com.googlecode.mgwt.ui.client.widget.base;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.mgwt.ui.client.widget.base.PullPanel.PullWidget;
import com.googlecode.mgwt.ui.client.widget.base.PullPanel.PullWidget.PullState;
import com.googlecode.mgwt.ui.client.widget.base.PullPanel.Pullhandler;

public class PullArrowStandardHandler implements Pullhandler {

	public interface PullActionHandler {
		public void onPullAction(AsyncCallback<Void> callback);
	}

	private final PullArrowWidget pullArrow;

	private String normalText;
	private String pulledText;
	private String errorText;
	private String loadingText;

	private boolean callRunning;

	private int errorTime = 1000;

	private PullActionHandler pullActionHandler;

	private final HasRefresh pullPanel;

	public PullArrowStandardHandler(PullArrowWidget pullArrow, HasRefresh pullPanel) {
		this.pullArrow = pullArrow;
		this.pullPanel = pullPanel;
		this.callRunning = false;
	}

	@Override
	public void onPullStateChanged(PullWidget pullWidget, PullState state) {
		switch (state) {
		case NORMAL:
			pullWidget.setHTML(normalText != null ? normalText : "");
			break;
		case PULLED:
			pullWidget.setHTML(pulledText != null ? pulledText : "");
			break;

		default:
			break;
		}

	}

	@Override
	public void onPullAction(PullWidget pullWidget) {
		if (pullActionHandler == null)
			return;

		if (callRunning)
			return;
		callRunning = true;
		pullWidget.setHTML(loadingText != null ? loadingText : "");

		pullArrow.showLoadingIndicator();

		pullActionHandler.onPullAction(new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				pullArrow.showError();
				pullArrow.setHTML(errorText != null ? errorText : "");

				new Timer() {

					@Override
					public void run() {
						callRunning = false;
						pullPanel.refresh();

						pullArrow.setHTML(normalText != null ? normalText : "");
						pullArrow.showArrow();

					}
				}.schedule(errorTime);

			}

			@Override
			public void onSuccess(Void result) {
				callRunning = false;
				pullArrow.setHTML(normalText != null ? normalText : "");
				pullArrow.showArrow();

			}
		});

	}

	public void setNormalText(String normalText) {
		this.normalText = normalText;
	}

	public void setPulledText(String pulledText) {
		this.pulledText = pulledText;
	}

	public void setLoadingText(String loadingText) {
		this.loadingText = loadingText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	public void setErrorTime(int errorTime) {
		if (errorTime < 1) {
			throw new IllegalArgumentException();
		}
		this.errorTime = errorTime;
	}

	public void setPullActionHandler(PullActionHandler pullActionHandler) {
		this.pullActionHandler = pullActionHandler;
	}

}

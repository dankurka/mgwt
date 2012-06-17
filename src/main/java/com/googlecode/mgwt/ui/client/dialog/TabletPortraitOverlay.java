/*
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
package com.googlecode.mgwt.ui.client.dialog;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent.ORIENTATION;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeHandler;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.HeaderCss;

/**
 * A Tablet overlay class
 * 
 * the content of the overlay is only visible in portrait mode
 * 
 * @author Daniel Kurka
 */
public class TabletPortraitOverlay implements HasOneWidget, Dialog {

  /**
   * The menu of an ipad overlay
   * 
   * @author Daniel Kurka
   * 
   */
	public static class IpadMenu extends Composite {

		private FlowPanel main;

		private FlowPanel menuArrow;

		private FlowPanel content;

    /**
     * Construct an {@link IpadMenu}
     */
		public IpadMenu() {
			this(MGWTStyle.getTheme().getMGWTClientBundle().getHeaderCss());
		}

    /**
     * Construct an {@link IpadMenu} with a given css
     * 
     * @param css the css to use
     */
		public IpadMenu(HeaderCss css) {
			main = new FlowPanel();
			css.ensureInjected();
			initWidget(main);

			setStylePrimaryName(css.main());

			// arrow
			menuArrow = new FlowPanel();
			menuArrow.setStylePrimaryName(css.arrow());
			main.add(menuArrow);

			content = new FlowPanel();
			content.addStyleName(css.content());

			main.add(content);

		}

		public FlowPanel getBody() {
			return content;
		}
	}

	private AnimatableDialogBase popinDialog;
	private IpadMenu ipadMenu;

	/**
	 * Construct a TabletOverlay
	 */
	public TabletPortraitOverlay() {
		popinDialog = new AnimatableDialogBase(MGWTStyle.getTheme().getMGWTClientBundle().getDialogCss()) {

			@Override
			protected Animation getShowAnimation() {
				return null;
			}

			@Override
			protected Animation getHideAnimation() {
				return null;
			}
		};

		ipadMenu = new IpadMenu();

		popinDialog.setCenterContent(false);
		popinDialog.setShadow(false);
		popinDialog.add(ipadMenu);
		popinDialog.setHideOnBackgroundClick(true);

		MGWT.addOrientationChangeHandler(new OrientationChangeHandler() {

			@Override
			public void onOrientationChanged(OrientationChangeEvent event) {
				if (event.getOrientation() == ORIENTATION.LANDSCAPE) {
					popinDialog.hide();
				}

			}
		});

	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.AcceptsOneWidget#setWidget(com.google.gwt.user.client.ui.IsWidget)
	 */
	/** {@inheritDoc} */
	@Override
	public void setWidget(IsWidget w) {
		ipadMenu.getBody().clear();
		ipadMenu.getBody().add(w);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasOneWidget#getWidget()
	 */
	/** {@inheritDoc} */
	@Override
	public Widget getWidget() {
		if (ipadMenu.getBody().getWidgetCount() > 0) {
			return ipadMenu.getBody().getWidget(0);
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasOneWidget#setWidget(com.google.gwt.user.client.ui.Widget)
	 */
	/** {@inheritDoc} */
	@Override
	public void setWidget(Widget w) {
		ipadMenu.getBody().clear();
		ipadMenu.getBody().add(w);

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.dialog.Dialog#show()
	 */
	/** {@inheritDoc} */
	@Override
	public void show() {
		popinDialog.show();
	}

}

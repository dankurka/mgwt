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
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.MGWTUtil;
import com.googlecode.mgwt.ui.client.theme.base.HeaderCss;

/**
 * A Tablet overlay class
 * 
 * the content of the overlay is only visible in portrait mode
 * 
 * @author Daniel Kurka
 * 
 */
public class TabletPortraitOverlay implements HasOneWidget, Dialog {

	public class IpadMenu extends Composite {

		private FlowPanel main;

		private FlowPanel menuArrow;

		private FlowPanel content;

		public IpadMenu() {
			this(MGWTStyle.getDefaultClientBundle().getHeaderCss());
		}

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
		popinDialog = new AnimatableDialogBase(MGWTStyle.getDefaultClientBundle().getDialogCss()) {

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

		MGWTUtil.addOrientationChangeHandler(new OrientationChangeHandler() {

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
	@Override
	public void setWidget(IsWidget w) {
		ipadMenu.getBody().clear();
		ipadMenu.getBody().add(w);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasOneWidget#getWidget()
	 */
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
	@Override
	public void setWidget(Widget w) {
		ipadMenu.getBody().clear();
		ipadMenu.getBody().add(w);

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.dialog.Dialog#show()
	 */
	@Override
	public void show() {
		popinDialog.show();
	}

}

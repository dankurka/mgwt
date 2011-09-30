package com.googlecode.mgwt.ui.client.panel;

import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent.ORIENTATION;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeHandler;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.MGWTUtil;
import com.googlecode.mgwt.ui.client.panel.ipadmenu.IpadMenu;

public class TabletPortraitOverlay implements HasOneWidget {
	private AnimatableDialogBase popinDialog;
	private IpadMenu ipadMenu;

	public TabletPortraitOverlay() {
		popinDialog = new AnimatableDialogBase(MGWTStyle.getDefaultClientBundle().getDialogCss()) {

			@Override
			protected Animation getShowAnimation() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected Animation getHideAnimation() {
				// TODO Auto-generated method stub
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

	@Override
	public void setWidget(IsWidget w) {
		ipadMenu.getBody().clear();
		ipadMenu.getBody().add(w);
	}

	@Override
	public Widget getWidget() {
		if (ipadMenu.getBody().getWidgetCount() > 0) {
			return ipadMenu.getBody().getWidget(0);
		} else {
			return null;
		}
	}

	@Override
	public void setWidget(Widget w) {
		ipadMenu.getBody().clear();
		ipadMenu.getBody().add(w);

	}

	public void show() {
		popinDialog.show();
	}

}

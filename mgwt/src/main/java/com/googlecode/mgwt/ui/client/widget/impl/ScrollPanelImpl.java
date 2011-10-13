package com.googlecode.mgwt.ui.client.widget.impl;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.ui.client.widget.event.HasScrollHandlers;

public abstract class ScrollPanelImpl extends Composite implements HasWidgets, HasScrollHandlers {

	public abstract void setPosition(int newPosX, int newPosY);

	public abstract void setUsePos(boolean pos);

	public abstract void scrollTo(int destX, int destY, int newDuration);

	public abstract boolean isScrollingEnabledX();

	public abstract void setScrollingEnabledX(boolean scrollingEnabledX);

	public abstract boolean isScrollingEnabledY();

	public abstract void setScrollingEnabledY(boolean scrollingEnabledY);

	public abstract void setOffset(int offsetX, int offsetY);

	public abstract void setWidget(IsWidget child);

	public abstract void refresh();
}

package com.googlecode.mgwt.examples.contact.client.activities;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;

public interface AddGroupDisplay extends IsWidget {

	public void setPresenter(Presenter presenter);

	public HasText getGroupName();

	public interface Presenter {
		public void onLeftButton();

		public void onAddButtonPressed();
	}

	public void showError(String string);
}

package com.googlecode.mgwt.examples.contact.client.activities;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.examples.contact.client.module.Group;

public interface ShowGroupDisplay extends IsWidget, Editor<Group> {

	public void setPresenter(Presenter presenter);

	public void edit(Group group);

	public interface Presenter {
		public void onErrors();

		public void onEditComplete(Group group);
	}
}

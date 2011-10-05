package com.googlecode.mgwt.examples.contact.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.googlecode.mgwt.examples.contact.client.css.AppBundle;
import com.googlecode.mgwt.examples.contact.client.module.Group;
import com.googlecode.mgwt.ui.client.widget.celllist.Cell;

public class GroupCell implements Cell<Group> {

	private static NormalTemplate NORMAL_TEMPLATE = GWT.create(NormalTemplate.class);
	private static EditTemplate EDIT_TEMPLATE = GWT.create(EditTemplate.class);

	public interface NormalTemplate extends SafeHtmlTemplates {
		@SafeHtmlTemplates.Template("<div class=\"{0}\">{1}</div>")
		SafeHtml content(String classes, String cellContents);

	}

	public interface EditTemplate extends SafeHtmlTemplates {
		@SafeHtmlTemplates.Template("<div class=\"{0}\">{1}</div>")
		SafeHtml content(String classes, String cellContents);

	}

	public GroupCell() {
		AppBundle.INSTANCE.getAppCss().ensureInjected();
	}

	private boolean edit = false;

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	@Override
	public void render(SafeHtmlBuilder safeHtmlBuilder, final Group model) {
		if (edit) {
			safeHtmlBuilder.append(EDIT_TEMPLATE.content(AppBundle.INSTANCE.getAppCss().editCell(), SafeHtmlUtils.htmlEscape(getDisplayString(model))));
		} else {
			safeHtmlBuilder.append(NORMAL_TEMPLATE.content("", SafeHtmlUtils.htmlEscape(getDisplayString(model))));
		}

	}

	public String getDisplayString(Group model) {
		return model.getName();
	}

	@Override
	public boolean canBeSelected(Group model) {
		return !edit;
	}

}

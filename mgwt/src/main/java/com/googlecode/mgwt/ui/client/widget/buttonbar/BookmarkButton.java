package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.BookmarkButtonCss;

public class BookmarkButton extends ButtonBarButtonBase {

	public BookmarkButton() {
		this(MGWTStyle.getDefaultClientBundle().getBookmarkButtonCss());
	}

	public BookmarkButton(BookmarkButtonCss bookmarkButtonCss) {
		super(bookmarkButtonCss);
		addStyleName(bookmarkButtonCss.bookmark());
	}

}

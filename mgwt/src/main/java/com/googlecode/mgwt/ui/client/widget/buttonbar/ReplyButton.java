package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.ReplyButtonCss;

public class ReplyButton extends ButtonBarButtonBase {

	public ReplyButton() {
		this(MGWTStyle.getDefaultClientBundle().getReplyButtonCss());
	}

	public ReplyButton(ReplyButtonCss replyButtonCss) {
		super(replyButtonCss);
		addStyleName(replyButtonCss.reply());
	}

}

/*
 * Copyright 2010 Daniel Kurka
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
package com.googlecode.mgwt.examples.showcase.client.activities.buttonbar;

import com.googlecode.mgwt.examples.showcase.client.DetailViewGwtImpl;
import com.googlecode.mgwt.ui.client.widget.RoundPanel;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ActionButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.AddContactButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ArrowDownButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ArrowLeftButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ArrowRightButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ArrowUpButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.BookmarkButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBar;
import com.googlecode.mgwt.ui.client.widget.buttonbar.CameraButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ComposeButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.DeleteButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.FastForwardButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.InfoButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.LocateButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.NewIconButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.NextSlideButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.OrganizeButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.PauseButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.PlayButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.PlusButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.PreviousSlideButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.RefreshButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ReplyButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.RewindButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.SearchButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.StopButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.TrashButton;

/**
 * @author Daniel Kurka
 * 
 */
public class ButtonBarViewGwtImpl extends DetailViewGwtImpl implements ButtonBarView {

	private ButtonBar footerPanel;

	/**
	 * 
	 */
	public ButtonBarViewGwtImpl() {

		RoundPanel roundPanel = new RoundPanel();
		roundPanel.setSize("200px", "200px");

		main.add(roundPanel);

		footerPanel = new ButtonBar();

		footerPanel.add(new RefreshButton());
		footerPanel.add(new ActionButton());
		footerPanel.add(new ArrowDownButton());
		footerPanel.add(new ArrowUpButton());
		footerPanel.add(new ArrowLeftButton());
		footerPanel.add(new ArrowRightButton());
		footerPanel.add(new BookmarkButton());
		footerPanel.add(new CameraButton());
		footerPanel.add(new ComposeButton());
		footerPanel.add(new AddContactButton());
		footerPanel.add(new FastForwardButton());
		footerPanel.add(new DeleteButton());
		footerPanel.add(new InfoButton());
		footerPanel.add(new LocateButton());
		footerPanel.add(new NewIconButton());
		footerPanel.add(new NextSlideButton());
		footerPanel.add(new OrganizeButton());
		footerPanel.add(new PauseButton());
		footerPanel.add(new PlayButton());
		footerPanel.add(new PlusButton());
		footerPanel.add(new PreviousSlideButton());
		footerPanel.add(new ReplyButton());
		footerPanel.add(new RewindButton());
		footerPanel.add(new SearchButton());
		footerPanel.add(new StopButton());
		footerPanel.add(new TrashButton());

		main.add(footerPanel);
	}

}

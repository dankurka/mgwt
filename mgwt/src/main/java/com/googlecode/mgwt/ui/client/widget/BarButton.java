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
package com.googlecode.mgwt.ui.client.widget;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.button.ButtonBase;
import com.googlecode.mgwt.ui.client.theme.base.ButtonBarCss;

/**
 * @author Daniel Kurka
 * 
 */
public class BarButton extends ButtonBase {
	protected final ButtonBarCss css;

	public enum TYPE {
		action {
			@Override
			public String cssClass() {
				return this.css.action();
			}
		},
		arrow_down {
			@Override
			public String cssClass() {
				return this.css.arrowDown();
			}
		},

		arrow_left {
			@Override
			public String cssClass() {
				return this.css.arrowLeft();
			}
		},

		arrow_right {
			@Override
			public String cssClass() {
				return this.css.arrowRight();
			}
		},

		arrow_up {
			@Override
			public String cssClass() {
				return this.css.arrowUp();
			}
		},

		bookmark {
			@Override
			public String cssClass() {
				return this.css.bookmark();
			}
		},

		camera {
			@Override
			public String cssClass() {
				return this.css.camera();
			}
		},

		compose {
			@Override
			public String cssClass() {
				return this.css.compose();
			}
		},

		contact_add {
			@Override
			public String cssClass() {
				return this.css.contactAdd();
			}
		},

		delete {
			@Override
			public String cssClass() {
				return this.css.delete();
			}
		},

		fast_forward {
			@Override
			public String cssClass() {
				return this.css.fastForward();
			}
		},

		info {
			@Override
			public String cssClass() {
				return this.css.info();
			}
		},

		locate {
			@Override
			public String cssClass() {
				return this.css.locate();
			}
		},

		new_icon {
			@Override
			public String cssClass() {
				return this.css.newIcon();
			}
		},

		next_slide {
			@Override
			public String cssClass() {
				return this.css.nextSlide();
			}
		},

		organize {
			@Override
			public String cssClass() {
				return this.css.organize();
			}
		},

		pause {
			@Override
			public String cssClass() {
				return this.css.pause();
			}
		},

		play {
			@Override
			public String cssClass() {
				return this.css.play();
			}
		},

		plus {
			@Override
			public String cssClass() {
				return this.css.plus();
			}
		},

		previous_slide {
			@Override
			public String cssClass() {
				return this.css.previousSlide();
			}
		},

		refresh {
			@Override
			public String cssClass() {
				return this.css.refresh();
			}
		},

		reply {
			@Override
			public String cssClass() {
				return this.css.reply();
			}
		},

		rewind {
			@Override
			public String cssClass() {
				return this.css.rewind();
			}
		},

		search {
			@Override
			public String cssClass() {
				return this.css.search();
			}
		},

		stop {
			@Override
			public String cssClass() {
				return this.css.stop();
			}
		},

		trash {
			@Override
			public String cssClass() {
				return this.css.trash();
			}
		},

		none {
			@Override
			public String cssClass() {
				return "";
			}
		};

		protected final ButtonBarCss css;

		TYPE() {
			this(MGWTStyle.getDefaultClientBundle().getButtonBarCss());
		}

		TYPE(ButtonBarCss css) {
			this.css = css;
		}

		public abstract String cssClass();

	}

	public BarButton() {
		this(MGWTStyle.getDefaultClientBundle().getButtonBarCss());
	}

	public BarButton(ButtonBarCss css) {
		this(css, TYPE.none);
	}

	public BarButton(TYPE type) {
		this(MGWTStyle.getDefaultClientBundle().getButtonBarCss(), type);
	}

	public BarButton(ButtonBarCss css, TYPE type) {
		super(css);
		this.css = css;
		this.css.ensureInjected();

		addStyleName(css.barButton());
		setType(type);

	}

	public void setType(TYPE type) {
		if (type != TYPE.none)
			addStyleName(type.cssClass());
	}
}

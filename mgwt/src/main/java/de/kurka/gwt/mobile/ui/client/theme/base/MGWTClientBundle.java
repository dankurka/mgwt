package de.kurka.gwt.mobile.ui.client.theme.base;

import com.google.gwt.resources.client.ClientBundle;

public interface MGWTClientBundle extends ClientBundle {

	@Source("css/progressbar.css")
	ProgressBarCss getProgressBarCss();

	@Source("css/progressindicator.css")
	ProgressIndicatorCss getProgressIndicatorCss();

	@Source("css/headerpanel.css")
	HeaderPanelCss getHeaderPanelCss();

	@Source("css/headerbutton.css")
	HeaderButtonCss getHeaderButtonCss();

	@Source("css/slider.css")
	SliderCss getSliderCss();

}

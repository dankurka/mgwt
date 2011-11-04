package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.CameraButtonCss;

public class CameraButton extends ButtonBarButtonBase {

	public CameraButton() {
		this(MGWTStyle.getDefaultClientBundle().getCameraButtonCss());
	}

	public CameraButton(CameraButtonCss cameraButtonCss) {
		super(cameraButtonCss);
		addStyleName(cameraButtonCss.camera());
	}

}

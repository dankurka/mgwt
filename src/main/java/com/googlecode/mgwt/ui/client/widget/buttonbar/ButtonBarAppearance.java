package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Panel;
import com.googlecode.mgwt.ui.client.util.MGWTCssResource;
import com.googlecode.mgwt.ui.client.widget.button.ButtonBaseAppearance;

public interface ButtonBarAppearance extends ButtonBaseAppearance {
  public interface ButtonBarButtonCss extends ButtonBaseAppearance.ButtonBaseCss {
    @Override
    @ClassName("mgwt-BarButton")
    public String button();

    @Override
    @ClassName("mgwt-BarButton-active")
    public String active();
  }

  public interface ButtonBarIcons {
    ImageResource highlightImage();

    ImageResource actionImage();

    ImageResource arrowDownImage();

    ImageResource arrowLeftImage();

    ImageResource arrowRightImage();

    ImageResource arrowUpImage();

    ImageResource bookmarkImage();

    ImageResource cameraImage();

    ImageResource composeImage();

    ImageResource fastForwardImage();

    ImageResource infoImage();

    ImageResource locateImage();

    ImageResource minusImage();

    ImageResource newImage();

    ImageResource nextSlideImage();

    ImageResource organizeImage();

    ImageResource pauseImage();

    ImageResource playImage();

    ImageResource plusImage();

    ImageResource previousSlideImage();

    ImageResource refreshImage();

    ImageResource replyImage();

    ImageResource rewindImage();

    ImageResource searchImage();

    ImageResource stopImage();

    ImageResource trashImage();

  }

  public interface ButtonBarCss extends MGWTCssResource {

    @ClassName("mgwt-ButtonBar")
    public String buttonBar();

    @ClassName("mgwt-ButtonBar-spacer")
    public String spacer();

    @ClassName("mgwt-ButtonBar-text")
    public String text();
  }

  @Override
  ButtonBarButtonCss css();

  ButtonBarCss barCss();

  ButtonBarIcons icons();

  @Override
  UiBinder<? extends Element, ButtonBarButtonBase> uiBinder();

  UiBinder<? extends Panel, ButtonBar> barBinder();

  UiBinder<? extends Element, ButtonBarSpacer> barSpacer();

  UiBinder<? extends Element, ButtonBarText> barText();

}

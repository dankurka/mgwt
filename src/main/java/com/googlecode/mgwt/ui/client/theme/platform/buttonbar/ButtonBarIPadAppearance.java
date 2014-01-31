package com.googlecode.mgwt.ui.client.theme.platform.buttonbar;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBarAbstractAppearance;

public class ButtonBarIPadAppearance extends ButtonBarAbstractAppearance {

  static {
    Resources.INSTANCE.buttonCss().ensureInjected();
    Resources.INSTANCE.barCss().ensureInjected();
  }

  interface Resources extends ClientBundle, ButtonBarIcons {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"buttonbarbutton.css"})
    ButtonBarButtonCss buttonCss();
    
    @Source({"buttonbar-base.css", "buttonbar-iphone.css"})
    ButtonBarCss barCss();

    @Override
    @Source("resources/pressed.png")
    ImageResource highlightImage();

    @Override
    @Source("resources/action.png")
    ImageResource actionImage();

    @Override
    @Source("resources/arrowdown.png")
    ImageResource arrowDownImage();

    @Override
    @Source("resources/arrowleft.png")
    ImageResource arrowLeftImage();

    @Override
    @Source("resources/arrowright.png")
    ImageResource arrowRightImage();

    @Override
    @Source("resources/arrowup.png")
    ImageResource arrowUpImage();

    @Override
    @Source("resources/bookmarks.png")
    ImageResource bookmarkImage();

    @Override
    @Source("resources/camera.png")
    ImageResource cameraImage();

    @Override
    @Source("resources/compose.png")
    ImageResource composeImage();

    @Override
    @Source("resources/fastforward.png")
    ImageResource fastForwardImage();

    @Override
    @Source("resources/info.png")
    ImageResource infoImage();

    @Override
    @Source("resources/locate.png")
    ImageResource locateImage();

    @Override
    @Source("resources/minus.png")
    ImageResource minusImage();

    @Override
    @Source("resources/new.png")
    ImageResource newImage();

    @Override
    @Source("resources/nextslide.png")
    ImageResource nextSlideImage();

    @Override
    @Source("resources/organize.png")
    ImageResource organizeImage();

    @Override
    @Source("resources/pause.png")
    ImageResource pauseImage();

    @Override
    @Source("resources/play.png")
    ImageResource playImage();

    @Override
    @Source("resources/plus.png")
    ImageResource plusImage();

    @Override
    @Source("resources/previousslide.png")
    ImageResource previousSlideImage();

    @Override
    @Source("resources/refresh.png")
    ImageResource refreshImage();

    @Override
    @Source("resources/reply.png")
    ImageResource replyImage();

    @Override
    @Source("resources/rewind.png")
    ImageResource rewindImage();

    @Override
    @Source("resources/search.png")
    ImageResource searchImage();

    @Override
    @Source("resources/stop.png")
    ImageResource stopImage();

    @Override
    @Source("resources/trash.png")
    ImageResource trashImage();

  }
  
  @Override
  public ButtonBarButtonCss css() {
    return Resources.INSTANCE.buttonCss();
  }

  @Override
  public ButtonBarCss barCss() {
    return Resources.INSTANCE.barCss();
  }

  @Override
  public ButtonBarIcons icons() {
    return Resources.INSTANCE;
  }
}

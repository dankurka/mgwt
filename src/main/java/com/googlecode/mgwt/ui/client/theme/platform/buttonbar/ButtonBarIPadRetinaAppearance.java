package com.googlecode.mgwt.ui.client.theme.platform.buttonbar;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBarAbstractAppearance;

public class ButtonBarIPadRetinaAppearance extends ButtonBarAbstractAppearance {

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
    @Source("resources/action_x2.png")
    ImageResource actionImage();

    @Override
    @Source("resources/arrowdown_x2.png")
    ImageResource arrowDownImage();

    @Override
    @Source("resources/arrowleft_x2.png")
    ImageResource arrowLeftImage();

    @Override
    @Source("resources/arrowright_x2.png")
    ImageResource arrowRightImage();

    @Override
    @Source("resources/arrowup_x2.png")
    ImageResource arrowUpImage();

    @Override
    @Source("resources/bookmarks_x2.png")
    ImageResource bookmarkImage();

    @Override
    @Source("resources/camera_x2.png")
    ImageResource cameraImage();

    @Override
    @Source("resources/compose_x2.png")
    ImageResource composeImage();

    @Override
    @Source("resources/fastforward_x2.png")
    ImageResource fastForwardImage();

    @Override
    @Source("resources/info_x2.png")
    ImageResource infoImage();

    @Override
    @Source("resources/locate_x2.png")
    ImageResource locateImage();

    @Override
    @Source("resources/minus_x2.png")
    ImageResource minusImage();

    @Override
    @Source("resources/new_x2.png")
    ImageResource newImage();

    @Override
    @Source("resources/nextslide_x2.png")
    ImageResource nextSlideImage();

    @Override
    @Source("resources/organize_x2.png")
    ImageResource organizeImage();

    @Override
    @Source("resources/pause_x2.png")
    ImageResource pauseImage();

    @Override
    @Source("resources/play_x2.png")
    ImageResource playImage();

    @Override
    @Source("resources/plus_x2.png")
    ImageResource plusImage();

    @Override
    @Source("resources/previousslide_x2.png")
    ImageResource previousSlideImage();

    @Override
    @Source("resources/refresh_x2.png")
    ImageResource refreshImage();

    @Override
    @Source("resources/reply_x2.png")
    ImageResource replyImage();

    @Override
    @Source("resources/rewind_x2.png")
    ImageResource rewindImage();

    @Override
    @Source("resources/search_x2.png")
    ImageResource searchImage();

    @Override
    @Source("resources/stop_x2.png")
    ImageResource stopImage();

    @Override
    @Source("resources/trash_x2.png")
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

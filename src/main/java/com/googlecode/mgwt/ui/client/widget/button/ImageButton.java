package com.googlecode.mgwt.ui.client.widget.button;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.TouchCancelEvent;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;

import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.base.IsSizeable;

public class ImageButton extends ButtonBase implements IsSizeable {

  public interface IconHandler {
    public void setIcons(Element element, ImageResource icon, ImageResource highlight, boolean active);
  }

  // Used with GWT.create
  @SuppressWarnings("unused")
  private static class IconHandlerImpl implements IconHandler {

    private static class Dimension {
      private int width;
      private int height;
      public Dimension(int width, int height) {
        this.width = width;
        this.height = height;
      }
    }

    @Override
    public void setIcons(Element element, ImageResource icon, ImageResource highlight,
        boolean active) {
      if (icon == null) {
        return;
      }

      if (!active) {
        Dimension dimensions = calculateDimensions(icon);
        element.getStyle().setBackgroundImage("url(" + icon.getSafeUri().asString() + ")");
        element.getStyle().setProperty("backgroundSize",
            dimensions.width + "px " + dimensions.height + "px");
      } else {
        // don't set active state if we don't have a hightlight icon...
        if (highlight == null) {
          return;
        }
        Dimension dimensions = calculateDimensions(icon);
        Dimension highlightDim = calculateDimensions(icon);
        element.getStyle().setBackgroundImage(
            "url(" + highlight.getSafeUri().asString() + "), url(" + icon.getSafeUri().asString()
                + ")");
        element.getStyle().setProperty(
            "backgroundSize",
            highlightDim.width + "px " + highlightDim.height + "px, " + dimensions.width + "px "
                + dimensions.height + "px");
      }
    }

    private Dimension calculateDimensions(ImageResource icon) {
      int iconWidth = icon.getWidth();
      int iconHeight = icon.getHeight();

      if(MGWT.getDeviceDensity().isHighDPI()) {
        iconWidth /= 1.5;
        iconHeight /= 1.5;
      } else if(MGWT.getDeviceDensity().isXHighDPI()){
        iconWidth /= 2;
        iconHeight /= 2;
      }
      return new Dimension(iconWidth, iconHeight);
    }
  }

  protected static final IconHandler ICON_HANDLER = GWT.create(IconHandler.class);

  private static final ImageButtonAppearance DEFAULT_BUTTON_APPEARANCE = GWT
      .create(ImageButtonAppearance.class);

  private ImageButtonAppearance appearance;

  private ImageResource icon;

  private boolean active;

  private ImageResource highlight;

  @UiField
  Element text;

  @UiField
  Element image;

  public ImageButton() {
    this(DEFAULT_BUTTON_APPEARANCE, "");
  }

  public ImageButton(String text) {
    this(DEFAULT_BUTTON_APPEARANCE, text);
  }

  public ImageButton(ImageResource icon) {
    this(DEFAULT_BUTTON_APPEARANCE, icon, null, "");
  }

  public ImageButton(ImageResource icon, ImageResource highlight) {
    this(DEFAULT_BUTTON_APPEARANCE, icon, highlight, "");
  }

  public ImageButton(ImageButtonAppearance appearance, String text) {
    this(appearance, null, null, text);
  }

  public ImageButton(ImageButtonAppearance appearance, ImageResource iconImage,
      ImageResource highlightImage, String text) {
    super(appearance);
    this.appearance = appearance;
    setElement(appearance.uiBinder().createAndBindUi(this));
    setIcon(iconImage);
    setHighlightImage(highlightImage);

    addTouchHandler(new TouchHandler() {

      @Override
      public void onTouchCancel(TouchCancelEvent event) {
        active = false;
        ICON_HANDLER.setIcons(image, icon, highlight, active);
      }

      @Override
      public void onTouchEnd(TouchEndEvent event) {
        active = false;
        ICON_HANDLER.setIcons(image, icon, highlight, active);
      }

      @Override
      public void onTouchMove(TouchMoveEvent event) {
      }

      @Override
      public void onTouchStart(TouchStartEvent event) {
        active = true;
        ICON_HANDLER.setIcons(image, icon, highlight, active);
      }
    });
  }

  @UiFactory
  protected ImageButtonAppearance getAppearance() {
    return appearance;
  }

  @Override
  public String getText() {
    return text.getInnerText();
  }

  @Override
  public void setText(String text) {
    this.text.setInnerText(text);
  }

  public void setIcon(ImageResource icon) {
    this.icon = icon;
    ICON_HANDLER.setIcons(image, icon, highlight, active);
  }

  public void setHighlightImage(ImageResource highlight) {
    this.highlight = highlight;
    ICON_HANDLER.setIcons(image, icon, highlight, active);
  }

  @Override
  public void setSmall(boolean small) {
    if (small) {
      addStyleName(appearance.css().small());
    } else {
      removeStyleName(appearance.css().small());
    }
  }
}

package com.googlecode.mgwt.ui.client.widget.button;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.TouchCancelEvent;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;

import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.image.client.ImageConverter;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.base.IsSizeable;

public class ImageButton extends ButtonBase implements IsSizeable {

  public interface IconHandler {
    public void setIcons(Element element, ImageResource icon, String color);
  }

  private static class IconHandlerNativeImpl implements IconHandler {

    protected static class Dimension {
      private int width;
      private int height;

      public Dimension(int width, int height) {
        this.width = width;
        this.height = height;
      }
    }

    @Override
    public void setIcons(Element element, ImageResource icon, String color) {
      if (icon == null) {
        return;
      }

      Dimension dimensions = calculateDimensions(icon);
      element.getStyle().setProperty("WebkitMaskBoxImage",
          "url(" + icon.getSafeUri().asString() + ")");
      element.getStyle().setWidth(dimensions.width, Unit.PX);
      element.getStyle().setHeight(dimensions.height, Unit.PX);
      element.getStyle().setProperty("WebkitMaskSize",
          dimensions.width + "px, " + dimensions.height + "px");
    }

    protected Dimension calculateDimensions(ImageResource icon) {
      int iconWidth = icon.getWidth();
      int iconHeight = icon.getHeight();

      if (MGWT.getDeviceDensity().isHighDPI()) {
        iconWidth /= 1.5;
        iconHeight /= 1.5;
      } else if (MGWT.getDeviceDensity().isXHighDPI()) {
        iconWidth /= 2;
        iconHeight /= 2;
      }
      return new Dimension(iconWidth, iconHeight);
    }
  }

  // Used with GWT.create
  @SuppressWarnings("unused")
  private static class IconHandlerEmulatedImpl extends IconHandlerNativeImpl {

    private static final ImageConverter converter = new ImageConverter();

    @Override
    public void setIcons(Element element, ImageResource icon, String color) {
      if (icon == null) {
        return;
      }

      element.getStyle().setBackgroundColor("transparent");
      ImageResource convertImageResource = converter.convert(icon, color);
      Dimension dimensions = calculateDimensions(convertImageResource);
      element.getStyle().setBackgroundImage(
          "url(" + convertImageResource.getSafeUri().asString() + ")");
      element.getStyle().setProperty("backgroundSize",
          dimensions.width + "px " + dimensions.height + "px");
    }

  }

  protected static final IconHandler ICON_HANDLER = GWT.create(IconHandler.class);

  private static final ImageButtonAppearance DEFAULT_BUTTON_APPEARANCE = GWT
      .create(ImageButtonAppearance.class);

  private final ImageButtonAppearance appearance;

  @UiField
  Element text;

  @UiField
  Element image;

  private ImageResource icon;

  public ImageButton() {
    this(DEFAULT_BUTTON_APPEARANCE, "");
  }

  public ImageButton(String text) {
    this(DEFAULT_BUTTON_APPEARANCE, text);
  }

  public ImageButton(ImageResource icon) {
    this(DEFAULT_BUTTON_APPEARANCE, icon, "");
  }

  public ImageButton(ImageButtonAppearance appearance, String text) {
    this(appearance, null, text);
  }

  public ImageButton(ImageButtonAppearance appearance, ImageResource iconImage, String text) {
    super(appearance);
    this.appearance = appearance;
    setElement(appearance.uiBinder().createAndBindUi(this));
    setIcon(iconImage);

    // iOS6 and old android have problems with the aligning in flexible box model with inline-block
    // elements
    if (MGWT.getOsDetection().isAndroid4_3_orLower() || MGWT.getOsDetection().isIOS6()) {
      this.text.getStyle().setDisplay(Display.BLOCK);
    }

    addTouchHandler(new TouchHandler() {

      @Override
      public void onTouchCancel(TouchCancelEvent event) {

        ICON_HANDLER.setIcons(image, icon, ImageButton.this.appearance.css()
            .ICON_BACKGROUND_COLOR());
      }

      @Override
      public void onTouchEnd(TouchEndEvent event) {

        ICON_HANDLER.setIcons(image, icon, ImageButton.this.appearance.css()
            .ICON_BACKGROUND_COLOR());
      }

      @Override
      public void onTouchMove(TouchMoveEvent event) {
      }

      @Override
      public void onTouchStart(TouchStartEvent event) {

        ICON_HANDLER.setIcons(image, icon, ImageButton.this.appearance.css()
            .ICON_BACKGROUND_COLOR_HIGHLIGHT());
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
    ICON_HANDLER.setIcons(image, icon, appearance.css().ICON_BACKGROUND_COLOR());
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

/*
 * Copyright 2014 Daniel Kurka
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
package com.googlecode.mgwt.image.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.CanvasPixelArray;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.ImageData;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeUri;

public class ImageConverter {

  static class ConversionContext {
    Canvas canvas;
    Context2d context;
    CanvasPixelArray canvasPixelArray;
  }

  private class ConvertedImageResource implements ImageResource {

    private final String dataUrl;
    private final int width;
    private final int height;

    public ConvertedImageResource(String dataUrl, int width, int height) {
      this.dataUrl = dataUrl;
      this.width = width;
      this.height = height;
    }

    @Override
    public String getName() {
      return "";
    }

    @Override
    public int getHeight() {
      return height;
    }

    @Override
    public int getLeft() {
      return 0;
    }

    @Override
    public SafeUri getSafeUri() {
      return new SafeUri() {

        @Override
        public String asString() {
          return dataUrl;
        }

        @Override
        public int hashCode() {
          return dataUrl.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
          return dataUrl.equals(obj);
        }
      };
    }

    @Override
    public int getTop() {
      return 0;
    }

    @Override
    public String getURL() {
      return dataUrl;
    }

    @Override
    public int getWidth() {
      return width;
    }

    @Override
    public boolean isAnimated() {
      return false;
    }
  }

  public ImageResource convert(ImageResource resource, String color) {

    if (color == null) {
      throw new IllegalArgumentException();
    }

    if (!color.startsWith("#")) {
      throw new IllegalArgumentException();
    }

    int hexColor = Integer.parseInt(color.substring(1), 16);

    int red = hexColor >> 16 & 0xFF;
    int green = hexColor >> 8 & 0xFF;
    int blue = hexColor & 0xFF;

    int height = resource.getHeight();
    int width = resource.getWidth();

    ImageElement imageElement = loadImage(resource.getSafeUri().asString(),
        width, height);

    Canvas canvas = Canvas.createIfSupported();
    canvas.getElement().setPropertyInt("height", height);
   canvas.getElement().setPropertyInt("width", width);

    Context2d context = canvas.getContext2d();
    context.drawImage(imageElement, 0, 0);
    ImageData imageData = context.getImageData(0, 0, width,
        height);

    CanvasPixelArray canvasPixelArray = imageData.getData();

    for (int i = 0; i < canvasPixelArray.getLength(); i += 4) {
      canvasPixelArray.set(i, red);
      canvasPixelArray.set(i + 1, green);
      canvasPixelArray.set(i + 2, blue);
      canvasPixelArray.set(i + 3,
      canvasPixelArray.get(i + 3));
    }
    context.putImageData(imageData, 0, 0);


    return new ConvertedImageResource(
        canvas.toDataUrl("image/png"), resource.getWidth(),
        resource.getHeight());
  }

  protected native ImageElement loadImage(String dataUrl, int width, int height) /*-{
    var img = new Image();
    img.width = width;
    img.height = height;
    img.src = dataUrl;
    return img;
  }-*/;
}

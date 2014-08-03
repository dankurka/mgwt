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

import com.google.gwt.core.shared.GWT;
import com.google.gwt.junit.DoNotRunWith;
import com.google.gwt.junit.Platform;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public class ImageConverterGwtTestCase extends GWTTestCase {

  interface ImageConverterTestBundle extends ClientBundle {
    ImageConverterTestBundle INSTANCE = GWT
        .create(ImageConverterTestBundle.class);

    @Source("alpha_test_image.png")
    ImageResource knownImage();
  }

  @Override
  public String getModuleName() {
    return "com.googlecode.mgwt.image.Image";
  }

  @DoNotRunWith(Platform.HtmlUnitUnknown)
  public void testConvert_withKnownImage() {
    ImageConverter imageConverter = new ImageConverter();
    ImageResource convertedResource = imageConverter.convert(
        ImageConverterTestBundle.INSTANCE.knownImage(), "#F10000");

    /*
     * Dirty hack to test, should be improved.
     */
    if (ua().contains("chrome/36")) {
      // Chrome 36
      assertEquals(
          "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAQAAAAECAYAAACp8Z5+AAAAN0lEQVQIW2NkAIP/jEAChJmAxH8OBoYPQMwgAMQKQIEPbkAJKaCEAhDzAAU+TgPKGAGxBhDzAwAOPQijzPdtmwAAAABJRU5ErkJggg==",
          convertedResource.getSafeUri().asString());
    } else if (ua().contains("rv:11.0")) {
      // IE11
      assertEquals(
          "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAQAAAAECAYAAACp8Z5+AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAA6SURBVBhXY2QAg/8gGoSZgMR/DiADiH8IMDD8UQAKfHFjYPgrBRRUACriAQp8nAZkGAFVajAwMPADACZjCanGPO3dAAAAAElFTkSuQmCC",
          convertedResource.getSafeUri().asString());
    } else if (ua().contains("iphone") && ua().contains("OS 7_1_2")) {
      // iphone 7.1.2
      assertEquals(
          "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAQAAAAECAYAAACp8Z5+AAAADElEQVQIHWNgoBwAAABEAAFFxiNWAAAAAElFTkSuQmCC",
          convertedResource.getSafeUri().asString());
    } else if (ua()
        .equalsIgnoreCase(
            "Mozilla/5.0 (Mobile; ALCATELOneTouch4012X; rv:18.1) Gecko/18.1 Firefox/18.1")) {
      // FF OS 1.1.0.0
      assertEquals(
          "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAQAAAAECAYAAACp8Z5+AAAADElEQVQImWNgoBwAAABEAAGC/mVLAAAAAElFTkSuQmCC",
          convertedResource.getSafeUri().asString());
    } else if (ua()
        .equalsIgnoreCase(
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:24.0) Gecko/20100101 Firefox/24.0")) {
      // FF 24
      assertEquals(
          "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAQAAAAECAYAAACp8Z5+AAAADElEQVQImWNgoBwAAABEAAGC/mVLAAAAAElFTkSuQmCC",
          convertedResource.getSafeUri().asString());
    } else if (ua()
        .equalsIgnoreCase(
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.75.14 (KHTML, like Gecko) Version/7.0.3 Safari/537.75.14")) {
      // safari 7
      assertEquals(
          "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAQAAAAECAYAAACp8Z5+AAAAOUlEQVQIHWNkAIP/jEAKhJmAxH8OBoYPQMwgAMQKLAwMH+0YGJilgBIKQMwDFGAMADKMgLIaQDY/AAdqCG2g3jC2AAAAAElFTkSuQmCC",
          convertedResource.getSafeUri().asString());
    } else {
      fail("browser has no value in test");
    }
  }

  private native String ua() /*-{
   return  $wnd.navigator.userAgent.toLowerCase();
  }-*/;
}

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
package com.googlecode.mgwt.dom.client.event.touch;

import com.google.gwt.dom.client.Touch;

public class TouchCopy {

  public static TouchCopy copy(Touch touch) {
    return new TouchCopy(touch.getPageX(), touch.getPageY(), touch.getIdentifier());
  }

  private final int x;
  private final int y;
  private final int id;

  public TouchCopy(int x, int y, int id) {
    this.x = x;
    this.y = y;
    this.id = id;
  }

  public int getPageX() {
    return x;
  }

  public int getPageY() {
    return y;
  }

  public int getIdentifier() {
    return id;
  }
}

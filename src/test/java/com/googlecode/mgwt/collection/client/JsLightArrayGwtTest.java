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
package com.googlecode.mgwt.collection.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.junit.client.GWTTestCase;

/**
 * Test for {@link JsLightArray}.
 */
public class JsLightArrayGwtTest extends GWTTestCase {

  @Override
  public String getModuleName() {
    return "com.googlecode.mgwt.collection.GwtCollections";
  }

  public void testPushAndShift() {
    JsLightArray<String> array = new JsLightArray<String>();

    array.push("1");
    array.push("2");
    array.push("3");

    assertEquals(3, array.length());
    assertEquals("1", array.get(0));
    assertEquals("2", array.get(1));
    assertEquals("3", array.get(2));

    assertEquals("1", array.shift());
    assertEquals("2", array.shift());
    assertEquals("3", array.shift());
    assertEquals(0, array.length());
  }

  public void testExistingArray() {
    JsArrayString nativeArray = JavaScriptObject.createArray(5).cast();
    nativeArray.set(0, "0");
    nativeArray.set(1, "1");
    nativeArray.set(2, "2");
    nativeArray.set(3, "3");
    nativeArray.set(4, "4");

    JsLightArray<String> array = new JsLightArray<String>(nativeArray);
    assertEquals(5, array.length());
    assertEquals("0", array.get(0));
    assertEquals("1", array.get(1));
    assertEquals("2", array.get(2));
    assertEquals("3", array.get(3));
    assertEquals("4", array.get(4));

    nativeArray.shift();
    nativeArray.shift();
    nativeArray.shift();
    nativeArray.shift();
    nativeArray.shift();

    assertEquals(0, array.length());
  }
}

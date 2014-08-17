/*
 * Copyright 2014 Daniel Kurka
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.mgwt.ui.client.widget.input.search;

import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;

import com.googlecode.mgwt.dom.client.event.tap.TapEvent;

public class MSearchBoxGwtTest extends GWTTestCase {

  private MSearchBox mSearchBox;

  private int submitCount;
  private int clearCount;
  private int valueChangeEventCount;

  @Override
  public String getModuleName() {
    return "com.googlecode.mgwt.ui.client.widget.input.search.MSearchBoxGwtTest";
  }

  @Override
  protected void gwtSetUp() throws Exception {
    mSearchBox = new MSearchBox();
    RootPanel.get().add(mSearchBox);
    submitCount = 0;
    clearCount = 0;
    valueChangeEventCount = 0;
  }

  @Override
  protected void gwtTearDown() throws Exception {
    RootPanel.get().remove(mSearchBox);
  }

  public void testEventsFired() {
    mSearchBox.addSearchClearHandler(new SearchClearHandler() {

      @Override
      public void onEvent(SearchClearEvent event) {
        clearCount++;
      }
    });

    mSearchBox.addSearchSubmitHandler(new SearchSubmitHandler() {

      @Override
      public void onEvent(SearchSubmitEvent event) {
        submitCount++;
      }
    });

    mSearchBox.addValueChangeHandler(new ValueChangeHandler<String>() {

      @Override
      public void onValueChange(ValueChangeEvent<String> event) {
        valueChangeEventCount++;
      }
    });

    fireKeyUpAndDownEventAndAddValue(77, "m");
    fireKeyUpAndDownEventAndAddValue(71, "g");
    fireKeyUpAndDownEventAndAddValue(87, "w");
    fireKeyUpAndDownEventAndAddValue(84, "t");

    delayTestFinish(1000);

    final ScheduledCommand clickClearHandler = new ScheduledCommand() {

      @Override
      public void execute() {
        assertEquals("mgwt", mSearchBox.getValue());
        assertEquals(2, submitCount);
        assertEquals(4, valueChangeEventCount);
        assertEquals(0, clearCount);

        mSearchBox.clearButton.fireEvent(new TapEvent(this, null, 0, 0));

        assertEquals("", mSearchBox.getValue());
        assertEquals(2, submitCount);
        assertEquals(5, valueChangeEventCount);
        assertEquals(1, clearCount);

        finishTest();
      }
    };

    final ScheduledCommand fireSecondEnterKey = new ScheduledCommand() {

      @Override
      public void execute() {
        assertEquals("mgwt", mSearchBox.getValue());
        assertEquals(1, submitCount);
        assertEquals(4, valueChangeEventCount);
        assertEquals(0, clearCount);
        fireKeyUpAndDownEventAndAddValue(13, "");
        runWithDelay(clickClearHandler, 100);
      }
    };

    ScheduledCommand fireEnterKey = new ScheduledCommand() {
      @Override
      public void execute() {
        assertEquals("mgwt", mSearchBox.getValue());
        assertEquals(0, submitCount);
        assertEquals(4, valueChangeEventCount);
        assertEquals(0, clearCount);
        fireKeyUpAndDownEventAndAddValue(13, "");
        runWithDelay(fireSecondEnterKey, 100);
      }
    };

    runWithDelay(fireEnterKey, 100);
  }

  private void fireKeyUpAndDownEventAndAddValue(int keyCode, String valueToAdd) {
    String newValue = mSearchBox.textBox.getElement().getPropertyString("value") + valueToAdd;
    mSearchBox.textBox.getElement().setPropertyString("value", newValue);
    NativeEvent keyDownEvent =
        Document.get().createKeyDownEvent(false, false, false, false, keyCode);
    DomEvent.fireNativeEvent(keyDownEvent, mSearchBox.textBox);
    NativeEvent keyUpEvent = Document.get().createKeyUpEvent(false, false, false, false, keyCode);
    DomEvent.fireNativeEvent(keyUpEvent, mSearchBox.textBox);
  }

  private void runWithDelay(final ScheduledCommand command, int delayInMs) {
    new Timer() {
      @Override
      public void run() {
        command.execute();
      }
    }.schedule(delayInMs);
  }
}

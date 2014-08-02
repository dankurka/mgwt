package com.googlecode.mgwt.ui.client.widget.input.radio;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;

public class MRadioButtonGwtTest extends GWTTestCase {

  private MRadioButton radioButton1;
  private MRadioButton radioButton2;

  @Override
  public String getModuleName() {
    return "com.googlecode.mgwt.ui.UI";
  }

  @Override
  protected void gwtSetUp() throws Exception {
    radioButton1 = new MRadioButton("group1");
    radioButton2 = new MRadioButton("group1");

    RootPanel.get().add(radioButton1);
    RootPanel.get().add(radioButton2);
  }

  @Override
  protected void gwtTearDown() throws Exception {
    radioButton1.removeFromParent();
    radioButton2.removeFromParent();
  }

  public void testValueChangeEventOnSetValue() {
    radioButton2.setValue(true);
    radioButton1.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

      @Override
      public void onValueChange(ValueChangeEvent<Boolean> event) {
        assertEquals(Boolean.TRUE, event.getValue());
        finishTest();
      }
    });

    radioButton2.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

      @Override
      public void onValueChange(ValueChangeEvent<Boolean> event) {
        fail("not expecting any events");
      }
    });

    delayTestFinish(200);
    radioButton1.setValue(true, true);
  }

  public void testNoEvents() {
    radioButton2.setValue(true);
    radioButton1.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

      @Override
      public void onValueChange(ValueChangeEvent<Boolean> event) {
        fail("not expecting any events");
      }
    });

    radioButton2.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

      @Override
      public void onValueChange(ValueChangeEvent<Boolean> event) {
        fail("not expecting any events");
      }
    });

    delayTestFinish(200);
    radioButton1.setValue(true);
    finishTestAfterDelay(100);
  }

  private void finishTestAfterDelay(int delayInMs) {
    new Timer() {
      @Override
      public void run() {
        finishTest();
      }
    }.schedule(delayInMs);
  }
}

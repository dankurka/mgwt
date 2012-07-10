/*
 * Copyright 2010 Daniel Kurka
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
package com.googlecode.mgwt.mvp.client;

import java.util.LinkedList;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * A baseclass for Activities which adds the ability to collect all handler instances and removes
 * them when the activity is stopped.
 * 
 * @author Daniel Kurka
 * @version $Id: $
 */
public abstract class MGWTAbstractActivity extends AbstractActivity {

  private LinkedList<HandlerRegistration> oldHandlers;
  private LinkedList<com.google.web.bindery.event.shared.HandlerRegistration> handlers;

  /**
   * <p>
   * Constructor for MGWTAbstractActivity.
   * </p>
   */
  public MGWTAbstractActivity() {
    oldHandlers = new LinkedList<HandlerRegistration>();
    handlers = new LinkedList<com.google.web.bindery.event.shared.HandlerRegistration>();
  }

  /**
   * add a {@link HandlerRegistration} to the handler collection
   * 
   * @param handlerRegistration a {@link com.google.gwt.event.shared.HandlerRegistration} object.
   */
  protected void addHandlerRegistration(
      com.google.web.bindery.event.shared.HandlerRegistration handlerRegistration) {
    handlers.add(handlerRegistration);
  }

  /**
   * add a {@link HandlerRegistration} to the handler collection
   * 
   * @param handlerRegistration a {@link com.google.gwt.event.shared.HandlerRegistration} object.
   */
  protected void addHandlerRegistration(HandlerRegistration handlerRegistration) {
    oldHandlers.add(handlerRegistration);
  }

  /**
   * {@inheritDoc}
   * 
   * onStop is overriden to automatically clear all {@link HandlerRegistration}
   */
  @Override
  public void onStop() {
    super.onStop();

    cancelAllHandlerRegistrations();
  }

  /**
   * Remove all collected oldHandlers, and remove them from the collection
   */
  protected void cancelAllHandlerRegistrations() {
    for (HandlerRegistration hr : oldHandlers) {
      hr.removeHandler();
    }
    oldHandlers.clear();

    for (com.google.web.bindery.event.shared.HandlerRegistration hr : handlers) {
      hr.removeHandler();
    }
    handlers.clear();
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    start(panel, (com.google.web.bindery.event.shared.EventBus) eventBus);
  }

  public void start(AcceptsOneWidget panel, com.google.web.bindery.event.shared.EventBus eventBus) {

  }

}

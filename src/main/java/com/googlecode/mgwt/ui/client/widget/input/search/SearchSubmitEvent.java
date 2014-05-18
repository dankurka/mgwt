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

import com.google.gwt.event.shared.GwtEvent;

public class SearchSubmitEvent extends GwtEvent<SearchSubmitHandler> {

  private static final Type<SearchSubmitHandler> TYPE = new Type<SearchSubmitHandler>();

  public static Type<SearchSubmitHandler> getType() {
    return TYPE;
  }

  private String value;

  public SearchSubmitEvent(String value) {
    this.value = value;
  }

  @Override
  public Type<SearchSubmitHandler> getAssociatedType() {
    return TYPE;
  }

  public String getValue() {
    return value;
  }

  @Override
  protected void dispatch(SearchSubmitHandler handler) {
    handler.onEvent(this);
  }
}

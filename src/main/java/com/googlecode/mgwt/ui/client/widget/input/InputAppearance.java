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
package com.googlecode.mgwt.ui.client.widget.input;

import com.googlecode.mgwt.ui.client.util.MGWTCssResource;

public interface InputAppearance {
  public interface InputCss extends MGWTCssResource {

    @ClassName("mgwt-TextBox")
    String textBox();

    @ClassName("mgwt-TextArea")
    String textArea();

    @ClassName("mgwt-PasswordTextBox")
    String passwordBox();

    @ClassName("mgwt-InputBox-box")
    String box();

    @ClassName("mgwt-InputBox-disabled")
    String disabled();

    @ClassName("mgwt-InputBox-invalid")
    String invalid();
  }

  InputCss css();
}

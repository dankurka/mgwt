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
package com.googlecode.mgwt.ui.client.widget.image;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ImageResource;

import com.googlecode.mgwt.ui.client.widget.image.ImageHolder.ImageHolderAppearance.Images;

public class ImageHolder {
  private static final ImageHolderAppearance APPEARANCE = GWT.create(ImageHolderAppearance.class);

  public interface ImageHolderAppearance {
    public interface Images {
      ImageResource about();

      ImageResource accept();

      ImageResource accounts();

      ImageResource addAlarm();

      ImageResource addGroup();

      ImageResource addPerson();

      ImageResource addToQueue();

      ImageResource airplaneModeOff();

      ImageResource airplaneModeOn();

      ImageResource alarms();

      ImageResource attachment();

      ImageResource back();

      ImageResource backspace();

      ImageResource bad();

      ImageResource battery();

      ImageResource bightnessLow();

      ImageResource bluetoothConnected();

      ImageResource bluetooth();

      ImageResource bluetoothSearching();

      ImageResource brightnessAuto();

      ImageResource brightnessHigh();

      ImageResource brightnessMedium();

      ImageResource call();

      ImageResource camera();

      ImageResource cancel();

      ImageResource cast();

      ImageResource ccBcc();

      ImageResource chat();

      ImageResource cloud();

      ImageResource collapse();

      ImageResource collection();

      ImageResource computer();

      ImageResource copy();

      ImageResource crop();

      ImageResource cut();

      ImageResource dataUsage();

      ImageResource dialPad();

      ImageResource directions();

      ImageResource discard();

      ImageResource dock();

      ImageResource download();

      ImageResource edit();

      ImageResource email();

      ImageResource endCall();

      ImageResource error();

      ImageResource event();

      ImageResource expand();

      ImageResource fastForward();

      ImageResource favorite();

      ImageResource flashAutomatic();

      ImageResource flashOff();

      ImageResource flashOn();

      ImageResource forward();

      ImageResource fullScreen();

      ImageResource gamepad();

      ImageResource goToToday();

      ImageResource good();

      ImageResource group();

      ImageResource halfImportant();

      ImageResource headphones();

      ImageResource headset();

      ImageResource help();

      ImageResource importExport();

      ImageResource important();

      ImageResource keyboard();

      ImageResource labels();

      ImageResource locationFound();

      ImageResource locationOff();

      ImageResource locationSearching();

      ImageResource makeAvailableOffline();

      ImageResource map();

      ImageResource merge();

      ImageResource mic();

      ImageResource micMuted();

      ImageResource mouse();

      ImageResource networkCell();

      ImageResource networkWifi();

      ImageResource newAccount();

      ImageResource newAttachment();

      ImageResource newEmail();

      ImageResource newEvent();

      ImageResource newItem();

      ImageResource newLabel();

      ImageResource newPicture();

      ImageResource next();

      ImageResource nextItem();

      ImageResource notImportant();

      ImageResource notSecure();

      ImageResource overflow();

      ImageResource paste();

      ImageResource pause();

      ImageResource pauseOverVideo();

      ImageResource person();

      ImageResource phone();

      ImageResource picture();

      ImageResource place();

      ImageResource play();

      ImageResource playOverVideo();

      ImageResource previous();

      ImageResource previousItem();

      ImageResource read();

      ImageResource refresh();

      ImageResource remove();

      ImageResource repeat();

      ImageResource replay();

      ImageResource replyAll();

      ImageResource reply();

      ImageResource returnFromFullScreen();

      ImageResource rewind();

      ImageResource ringVolume();

      ImageResource rotateLeft();

      ImageResource rotateRight();

      ImageResource save();

      ImageResource screenLockedToLandscape();

      ImageResource screenLockedToPortrait();

      ImageResource screenRotation();

      ImageResource sdStorage();

      ImageResource search();

      ImageResource secure();

      ImageResource selectAll();

      ImageResource sendNow();

      ImageResource settings();

      ImageResource share();

      ImageResource shuffle();

      ImageResource slideshow();

      ImageResource sortBySize();

      ImageResource split();

      ImageResource stop();

      ImageResource storage();

      ImageResource switchCamera();

      ImageResource switchVideo();

      ImageResource time();

      ImageResource undo();

      ImageResource unread();

      ImageResource upload();

      ImageResource usb();

      ImageResource video();

      ImageResource viewAsGrid();

      ImageResource viewAsList();

      ImageResource volumeMuted();

      ImageResource volumeOn();

      ImageResource warning();

      ImageResource webSite();
    }

    Images get();
  }

  public static Images get() {
    return APPEARANCE.get();
  }
}

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
package com.googlecode.mgwt.ui.client.widget.image;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

import com.googlecode.mgwt.ui.client.widget.image.ImageHolder.ImageHolderAppearance;

public class ImageHolderDefaultAppearance implements ImageHolderAppearance {
  interface Resources extends ClientBundle, Images {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source("resources/ic_action_about_mdpi.png")
    ImageResource about();

    @Source("resources/ic_action_accept_mdpi.png")
    ImageResource accept();

    @Source("resources/ic_action_accounts_mdpi.png")
    ImageResource accounts();

    @Source("resources/ic_action_add_alarm_mdpi.png")
    ImageResource addAlarm();

    @Source("resources/ic_action_add_group_mdpi.png")
    ImageResource addGroup();

    @Source("resources/ic_action_add_person_mdpi.png")
    ImageResource addPerson();

    @Source("resources/ic_action_add_to_queue_mdpi.png")
    ImageResource addToQueue();

    @Source("resources/ic_action_airplane_mode_off_mdpi.png")
    ImageResource airplaneModeOff();

    @Source("resources/ic_action_airplane_mode_on_mdpi.png")
    ImageResource airplaneModeOn();

    @Source("resources/ic_action_alarms_mdpi.png")
    ImageResource alarms();

    @Source("resources/ic_action_attachment_mdpi.png")
    ImageResource attachment();

    @Source("resources/ic_action_back_mdpi.png")
    ImageResource back();

    @Source("resources/ic_action_backspace_mdpi.png")
    ImageResource backspace();

    @Source("resources/ic_action_bad_mdpi.png")
    ImageResource bad();

    @Source("resources/ic_action_battery_mdpi.png")
    ImageResource battery();

    @Source("resources/ic_action_bightness_low_mdpi.png")
    ImageResource bightnessLow();

    @Source("resources/ic_action_bluetooth_connected_mdpi.png")
    ImageResource bluetoothConnected();

    @Source("resources/ic_action_bluetooth_mdpi.png")
    ImageResource bluetooth();

    @Source("resources/ic_action_bluetooth_searching_mdpi.png")
    ImageResource bluetoothSearching();

    @Source("resources/ic_action_brightness_auto_mdpi.png")
    ImageResource brightnessAuto();

    @Source("resources/ic_action_brightness_high_mdpi.png")
    ImageResource brightnessHigh();

    @Source("resources/ic_action_brightness_medium_mdpi.png")
    ImageResource brightnessMedium();

    @Source("resources/ic_action_call_mdpi.png")
    ImageResource call();

    @Source("resources/ic_action_camera_mdpi.png")
    ImageResource camera();

    @Source("resources/ic_action_cancel_mdpi.png")
    ImageResource cancel();

    @Source("resources/ic_action_cast_mdpi.png")
    ImageResource cast();

    @Source("resources/ic_action_cc_bcc_mdpi.png")
    ImageResource ccBcc();

    @Source("resources/ic_action_chat_mdpi.png")
    ImageResource chat();

    @Source("resources/ic_action_cloud_mdpi.png")
    ImageResource cloud();

    @Source("resources/ic_action_collapse_mdpi.png")
    ImageResource collapse();

    @Source("resources/ic_action_collection_mdpi.png")
    ImageResource collection();

    @Source("resources/ic_action_computer_mdpi.png")
    ImageResource computer();

    @Source("resources/ic_action_copy_mdpi.png")
    ImageResource copy();

    @Source("resources/ic_action_crop_mdpi.png")
    ImageResource crop();

    @Source("resources/ic_action_cut_mdpi.png")
    ImageResource cut();

    @Source("resources/ic_action_data_usage_mdpi.png")
    ImageResource dataUsage();

    @Source("resources/ic_action_dial_pad_mdpi.png")
    ImageResource dialPad();

    @Source("resources/ic_action_directions_mdpi.png")
    ImageResource directions();

    @Source("resources/ic_action_discard_mdpi.png")
    ImageResource discard();

    @Source("resources/ic_action_dock_mdpi.png")
    ImageResource dock();

    @Source("resources/ic_action_download_mdpi.png")
    ImageResource download();

    @Source("resources/ic_action_edit_mdpi.png")
    ImageResource edit();

    @Source("resources/ic_action_email_mdpi.png")
    ImageResource email();

    @Source("resources/ic_action_end_call_mdpi.png")
    ImageResource endCall();

    @Source("resources/ic_action_error_mdpi.png")
    ImageResource error();

    @Source("resources/ic_action_event_mdpi.png")
    ImageResource event();

    @Source("resources/ic_action_expand_mdpi.png")
    ImageResource expand();

    @Source("resources/ic_action_fast_forward_mdpi.png")
    ImageResource fastForward();

    @Source("resources/ic_action_favorite_mdpi.png")
    ImageResource favorite();

    @Source("resources/ic_action_flash_automatic_mdpi.png")
    ImageResource flashAutomatic();

    @Source("resources/ic_action_flash_off_mdpi.png")
    ImageResource flashOff();

    @Source("resources/ic_action_flash_on_mdpi.png")
    ImageResource flashOn();

    @Source("resources/ic_action_forward_mdpi.png")
    ImageResource forward();

    @Source("resources/ic_action_full_screen_mdpi.png")
    ImageResource fullScreen();

    @Source("resources/ic_action_gamepad_mdpi.png")
    ImageResource gamepad();

    @Source("resources/ic_action_go_to_today_mdpi.png")
    ImageResource goToToday();

    @Source("resources/ic_action_good_mdpi.png")
    ImageResource good();

    @Source("resources/ic_action_group_mdpi.png")
    ImageResource group();

    @Source("resources/ic_action_half_important_mdpi.png")
    ImageResource halfImportant();

    @Source("resources/ic_action_headphones_mdpi.png")
    ImageResource headphones();

    @Source("resources/ic_action_headset_mdpi.png")
    ImageResource headset();

    @Source("resources/ic_action_help_mdpi.png")
    ImageResource help();

    @Source("resources/ic_action_import_export_mdpi.png")
    ImageResource importExport();

    @Source("resources/ic_action_important_mdpi.png")
    ImageResource important();

    @Source("resources/ic_action_keyboard_mdpi.png")
    ImageResource keyboard();

    @Source("resources/ic_action_labels_mdpi.png")
    ImageResource labels();

    @Source("resources/ic_action_location_found_mdpi.png")
    ImageResource locationFound();

    @Source("resources/ic_action_location_off_mdpi.png")
    ImageResource locationOff();

    @Source("resources/ic_action_location_searching_mdpi.png")
    ImageResource locationSearching();

    @Source("resources/ic_action_make_available_offline_mdpi.png")
    ImageResource makeAvailableOffline();

    @Source("resources/ic_action_map_mdpi.png")
    ImageResource map();

    @Source("resources/ic_action_merge_mdpi.png")
    ImageResource merge();

    @Source("resources/ic_action_mic_mdpi.png")
    ImageResource mic();

    @Source("resources/ic_action_mic_muted_mdpi.png")
    ImageResource micMuted();

    @Source("resources/ic_action_mouse_mdpi.png")
    ImageResource mouse();

    @Source("resources/ic_action_network_cell_mdpi.png")
    ImageResource networkCell();

    @Source("resources/ic_action_network_wifi_mdpi.png")
    ImageResource networkWifi();

    @Source("resources/ic_action_new_account_mdpi.png")
    ImageResource newAccount();

    @Source("resources/ic_action_new_attachment_mdpi.png")
    ImageResource newAttachment();

    @Source("resources/ic_action_new_email_mdpi.png")
    ImageResource newEmail();

    @Source("resources/ic_action_new_event_mdpi.png")
    ImageResource newEvent();

    @Source("resources/ic_action_new_label_mdpi.png")
    ImageResource newLabel();

    @Source("resources/ic_action_new_mdpi.png")
    ImageResource newItem();

    @Source("resources/ic_action_new_picture_mdpi.png")
    ImageResource newPicture();

    @Source("resources/ic_action_next_item_mdpi.png")
    ImageResource nextItem();

    @Source("resources/ic_action_next_mdpi.png")
    ImageResource next();

    @Source("resources/ic_action_not_important_mdpi.png")
    ImageResource notImportant();

    @Source("resources/ic_action_not_secure_mdpi.png")
    ImageResource notSecure();

    @Source("resources/ic_action_overflow_mdpi.png")
    ImageResource overflow();

    @Source("resources/ic_action_paste_mdpi.png")
    ImageResource paste();

    @Source("resources/ic_action_pause_mdpi.png")
    ImageResource pause();

    @Source("resources/ic_action_pause_over_video_mdpi.png")
    ImageResource pauseOverVideo();

    @Source("resources/ic_action_person_mdpi.png")
    ImageResource person();

    @Source("resources/ic_action_phone_mdpi.png")
    ImageResource phone();

    @Source("resources/ic_action_picture_mdpi.png")
    ImageResource picture();

    @Source("resources/ic_action_place_mdpi.png")
    ImageResource place();

    @Source("resources/ic_action_play_mdpi.png")
    ImageResource play();

    @Source("resources/ic_action_play_over_video_mdpi.png")
    ImageResource playOverVideo();

    @Source("resources/ic_action_previous_item_mdpi.png")
    ImageResource previousItem();

    @Source("resources/ic_action_previous_mdpi.png")
    ImageResource previous();

    @Source("resources/ic_action_read_mdpi.png")
    ImageResource read();

    @Source("resources/ic_action_refresh_mdpi.png")
    ImageResource refresh();

    @Source("resources/ic_action_remove_mdpi.png")
    ImageResource remove();

    @Source("resources/ic_action_repeat_mdpi.png")
    ImageResource repeat();

    @Source("resources/ic_action_replay_mdpi.png")
    ImageResource replay();

    @Source("resources/ic_action_reply_all_mdpi.png")
    ImageResource replyAll();

    @Source("resources/ic_action_reply_mdpi.png")
    ImageResource reply();

    @Source("resources/ic_action_return_from_full_screen_mdpi.png")
    ImageResource returnFromFullScreen();

    @Source("resources/ic_action_rewind_mdpi.png")
    ImageResource rewind();

    @Source("resources/ic_action_ring_volume_mdpi.png")
    ImageResource ringVolume();

    @Source("resources/ic_action_rotate_left_mdpi.png")
    ImageResource rotateLeft();

    @Source("resources/ic_action_rotate_right_mdpi.png")
    ImageResource rotateRight();

    @Source("resources/ic_action_save_mdpi.png")
    ImageResource save();

    @Source("resources/ic_action_screen_locked_to_landscape_mdpi.png")
    ImageResource screenLockedToLandscape();

    @Source("resources/ic_action_screen_locked_to_portrait_mdpi.png")
    ImageResource screenLockedToPortrait();

    @Source("resources/ic_action_screen_rotation_mdpi.png")
    ImageResource screenRotation();

    @Source("resources/ic_action_sd_storage_mdpi.png")
    ImageResource sdStorage();

    @Source("resources/ic_action_search_mdpi.png")
    ImageResource search();

    @Source("resources/ic_action_secure_mdpi.png")
    ImageResource secure();

    @Source("resources/ic_action_select_all_mdpi.png")
    ImageResource selectAll();

    @Source("resources/ic_action_send_now_mdpi.png")
    ImageResource sendNow();

    @Source("resources/ic_action_settings_mdpi.png")
    ImageResource settings();

    @Source("resources/ic_action_share_mdpi.png")
    ImageResource share();

    @Source("resources/ic_action_shuffle_mdpi.png")
    ImageResource shuffle();

    @Source("resources/ic_action_slideshow_mdpi.png")
    ImageResource slideshow();

    @Source("resources/ic_action_sort_by_size_mdpi.png")
    ImageResource sortBySize();

    @Source("resources/ic_action_split_mdpi.png")
    ImageResource split();

    @Source("resources/ic_action_stop_mdpi.png")
    ImageResource stop();

    @Source("resources/ic_action_storage_mdpi.png")
    ImageResource storage();

    @Source("resources/ic_action_switch_camera_mdpi.png")
    ImageResource switchCamera();

    @Source("resources/ic_action_switch_video_mdpi.png")
    ImageResource switchVideo();

    @Source("resources/ic_action_time_mdpi.png")
    ImageResource time();

    @Source("resources/ic_action_undo_mdpi.png")
    ImageResource undo();

    @Source("resources/ic_action_unread_mdpi.png")
    ImageResource unread();

    @Source("resources/ic_action_upload_mdpi.png")
    ImageResource upload();

    @Source("resources/ic_action_usb_mdpi.png")
    ImageResource usb();

    @Source("resources/ic_action_video_mdpi.png")
    ImageResource video();

    @Source("resources/ic_action_view_as_grid_mdpi.png")
    ImageResource viewAsGrid();

    @Source("resources/ic_action_view_as_list_mdpi.png")
    ImageResource viewAsList();

    @Source("resources/ic_action_volume_muted_mdpi.png")
    ImageResource volumeMuted();

    @Source("resources/ic_action_volume_on_mdpi.png")
    ImageResource volumeOn();

    @Source("resources/ic_action_warning_mdpi.png")
    ImageResource warning();

    @Source("resources/ic_action_web_site_mdpi.png")
    ImageResource webSite();


  }

  @Override
  public Images get() {
    return Resources.INSTANCE;
  }
}

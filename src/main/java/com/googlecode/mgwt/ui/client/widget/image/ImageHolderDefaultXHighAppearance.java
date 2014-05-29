package com.googlecode.mgwt.ui.client.widget.image;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

import com.googlecode.mgwt.ui.client.widget.image.ImageHolder.ImageHolderAppearance;

public class ImageHolderDefaultXHighAppearance implements ImageHolderAppearance {
  interface Resources extends ClientBundle, Images {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source("resources/ic_action_about_xhdpi.png")
    ImageResource about();

    @Source("resources/ic_action_accept_xhdpi.png")
    ImageResource accept();

    @Source("resources/ic_action_accounts_xhdpi.png")
    ImageResource accounts();

    @Source("resources/ic_action_add_alarm_xhdpi.png")
    ImageResource addAlarm();

    @Source("resources/ic_action_add_group_xhdpi.png")
    ImageResource addGroup();

    @Source("resources/ic_action_add_person_xhdpi.png")
    ImageResource addPerson();

    @Source("resources/ic_action_add_to_queue_xhdpi.png")
    ImageResource addToQueue();

    @Source("resources/ic_action_airplane_mode_off_xhdpi.png")
    ImageResource airplaneModeOff();

    @Source("resources/ic_action_airplane_mode_on_xhdpi.png")
    ImageResource airplaneModeOn();

    @Source("resources/ic_action_alarms_xhdpi.png")
    ImageResource alarms();

    @Source("resources/ic_action_attachment_xhdpi.png")
    ImageResource attachment();

    @Source("resources/ic_action_back_xhdpi.png")
    ImageResource back();

    @Source("resources/ic_action_backspace_xhdpi.png")
    ImageResource backspace();

    @Source("resources/ic_action_bad_xhdpi.png")
    ImageResource bad();

    @Source("resources/ic_action_battery_xhdpi.png")
    ImageResource battery();

    @Source("resources/ic_action_bightness_low_xhdpi.png")
    ImageResource bightnessLow();

    @Source("resources/ic_action_bluetooth_connected_xhdpi.png")
    ImageResource bluetoothConnected();

    @Source("resources/ic_action_bluetooth_searching_xhdpi.png")
    ImageResource bluetoothSearching();

    @Source("resources/ic_action_bluetooth_xhdpi.png")
    ImageResource bluetooth();

    @Source("resources/ic_action_brightness_auto_xhdpi.png")
    ImageResource brightnessAuto();

    @Source("resources/ic_action_brightness_high_xhdpi.png")
    ImageResource brightnessHigh();

    @Source("resources/ic_action_brightness_medium_xhdpi.png")
    ImageResource brightnessMedium();

    @Source("resources/ic_action_call_xhdpi.png")
    ImageResource call();

    @Source("resources/ic_action_camera_xhdpi.png")
    ImageResource camera();

    @Source("resources/ic_action_cancel_xhdpi.png")
    ImageResource cancel();

    @Source("resources/ic_action_cast_xhdpi.png")
    ImageResource cast();

    @Source("resources/ic_action_cc_bcc_xhdpi.png")
    ImageResource ccBcc();

    @Source("resources/ic_action_chat_xhdpi.png")
    ImageResource chat();

    @Source("resources/ic_action_cloud_xhdpi.png")
    ImageResource cloud();

    @Source("resources/ic_action_collapse_xhdpi.png")
    ImageResource collapse();

    @Source("resources/ic_action_collection_xhdpi.png")
    ImageResource collection();

    @Source("resources/ic_action_computer_xhdpi.png")
    ImageResource computer();

    @Source("resources/ic_action_copy_xhdpi.png")
    ImageResource copy();

    @Source("resources/ic_action_crop_xhdpi.png")
    ImageResource crop();

    @Source("resources/ic_action_cut_xhdpi.png")
    ImageResource cut();

    @Source("resources/ic_action_data_usage_xhdpi.png")
    ImageResource dataUsage();

    @Source("resources/ic_action_dial_pad_xhdpi.png")
    ImageResource dialPad();

    @Source("resources/ic_action_directions_xhdpi.png")
    ImageResource directions();

    @Source("resources/ic_action_discard_xhdpi.png")
    ImageResource discard();

    @Source("resources/ic_action_dock_xhdpi.png")
    ImageResource dock();

    @Source("resources/ic_action_download_xhdpi.png")
    ImageResource download();

    @Source("resources/ic_action_edit_xhdpi.png")
    ImageResource edit();

    @Source("resources/ic_action_email_xhdpi.png")
    ImageResource email();

    @Source("resources/ic_action_end_call_xhdpi.png")
    ImageResource endCall();

    @Source("resources/ic_action_error_xhdpi.png")
    ImageResource error();

    @Source("resources/ic_action_event_xhdpi.png")
    ImageResource event();

    @Source("resources/ic_action_expand_xhdpi.png")
    ImageResource expand();

    @Source("resources/ic_action_fast_forward_xhdpi.png")
    ImageResource fastForward();

    @Source("resources/ic_action_favorite_xhdpi.png")
    ImageResource favorite();

    @Source("resources/ic_action_flash_automatic_xhdpi.png")
    ImageResource flashAutomatic();

    @Source("resources/ic_action_flash_off_xhdpi.png")
    ImageResource flashOff();

    @Source("resources/ic_action_flash_on_xhdpi.png")
    ImageResource flashOn();

    @Source("resources/ic_action_forward_xhdpi.png")
    ImageResource forward();

    @Source("resources/ic_action_full_screen_xhdpi.png")
    ImageResource fullScreen();

    @Source("resources/ic_action_gamepad_xhdpi.png")
    ImageResource gamepad();

    @Source("resources/ic_action_go_to_today_xhdpi.png")
    ImageResource goToToday();

    @Source("resources/ic_action_good_xhdpi.png")
    ImageResource good();

    @Source("resources/ic_action_group_xhdpi.png")
    ImageResource group();

    @Source("resources/ic_action_half_important_xhdpi.png")
    ImageResource halfImportant();

    @Source("resources/ic_action_headphones_xhdpi.png")
    ImageResource headphones();

    @Source("resources/ic_action_headset_xhdpi.png")
    ImageResource headset();

    @Source("resources/ic_action_help_xhdpi.png")
    ImageResource help();

    @Source("resources/ic_action_import_export_xhdpi.png")
    ImageResource importExport();

    @Source("resources/ic_action_important_xhdpi.png")
    ImageResource important();

    @Source("resources/ic_action_keyboard_xhdpi.png")
    ImageResource keyboard();

    @Source("resources/ic_action_labels_xhdpi.png")
    ImageResource labels();

    @Source("resources/ic_action_location_found_xhdpi.png")
    ImageResource locationFound();

    @Source("resources/ic_action_location_off_xhdpi.png")
    ImageResource locationOff();

    @Source("resources/ic_action_location_searching_xhdpi.png")
    ImageResource locationSearching();

    @Source("resources/ic_action_make_available_offline_xhdpi.png")
    ImageResource makeAvailableOffline();

    @Source("resources/ic_action_map_xhdpi.png")
    ImageResource map();

    @Source("resources/ic_action_merge_xhdpi.png")
    ImageResource merge();

    @Source("resources/ic_action_mic_muted_xhdpi.png")
    ImageResource micMuted();

    @Source("resources/ic_action_mic_xhdpi.png")
    ImageResource mic();

    @Source("resources/ic_action_mouse_xhdpi.png")
    ImageResource mouse();

    @Source("resources/ic_action_network_cell_xhdpi.png")
    ImageResource networkCell();

    @Source("resources/ic_action_network_wifi_xhdpi.png")
    ImageResource networkWifi();

    @Source("resources/ic_action_new_account_xhdpi.png")
    ImageResource newAccount();

    @Source("resources/ic_action_new_attachment_xhdpi.png")
    ImageResource newAttachment();

    @Source("resources/ic_action_new_email_xhdpi.png")
    ImageResource newEmail();

    @Source("resources/ic_action_new_event_xhdpi.png")
    ImageResource newEvent();

    @Source("resources/ic_action_new_label_xhdpi.png")
    ImageResource newLabel();

    @Source("resources/ic_action_new_picture_xhdpi.png")
    ImageResource newPicture();

    @Source("resources/ic_action_new_xhdpi.png")
    ImageResource newItem();

    @Source("resources/ic_action_next_item_xhdpi.png")
    ImageResource nextItem();

    @Source("resources/ic_action_next_xhdpi.png")
    ImageResource next();

    @Source("resources/ic_action_not_important_xhdpi.png")
    ImageResource notImportant();

    @Source("resources/ic_action_not_secure_xhdpi.png")
    ImageResource notSecure();

    @Source("resources/ic_action_overflow_xhdpi.png")
    ImageResource overflow();

    @Source("resources/ic_action_paste_xhdpi.png")
    ImageResource paste();

    @Source("resources/ic_action_pause_over_video_xhdpi.png")
    ImageResource pauseOverVideo();

    @Source("resources/ic_action_pause_xhdpi.png")
    ImageResource pause();

    @Source("resources/ic_action_person_xhdpi.png")
    ImageResource person();

    @Source("resources/ic_action_phone_xhdpi.png")
    ImageResource phone();

    @Source("resources/ic_action_picture_xhdpi.png")
    ImageResource picture();

    @Source("resources/ic_action_place_xhdpi.png")
    ImageResource place();

    @Source("resources/ic_action_play_over_video_xhdpi.png")
    ImageResource playOverVideo();

    @Source("resources/ic_action_play_xhdpi.png")
    ImageResource play();

    @Source("resources/ic_action_previous_item_xhdpi.png")
    ImageResource previousItem();

    @Source("resources/ic_action_previous_xhdpi.png")
    ImageResource previous();

    @Source("resources/ic_action_read_xhdpi.png")
    ImageResource read();

    @Source("resources/ic_action_refresh_xhdpi.png")
    ImageResource refresh();

    @Source("resources/ic_action_remove_xhdpi.png")
    ImageResource remove();

    @Source("resources/ic_action_repeat_xhdpi.png")
    ImageResource repeat();

    @Source("resources/ic_action_replay_xhdpi.png")
    ImageResource replay();

    @Source("resources/ic_action_reply_all_xhdpi.png")
    ImageResource replyAll();

    @Source("resources/ic_action_reply_xhdpi.png")
    ImageResource reply();

    @Source("resources/ic_action_return_from_full_screen_xhdpi.png")
    ImageResource returnFromFullScreen();

    @Source("resources/ic_action_rewind_xhdpi.png")
    ImageResource rewind();

    @Source("resources/ic_action_ring_volume_xhdpi.png")
    ImageResource ringVolume();

    @Source("resources/ic_action_rotate_left_xhdpi.png")
    ImageResource rotateLeft();

    @Source("resources/ic_action_rotate_right_xhdpi.png")
    ImageResource rotateRight();

    @Source("resources/ic_action_save_xhdpi.png")
    ImageResource save();

    @Source("resources/ic_action_screen_locked_to_landscape_xhdpi.png")
    ImageResource screenLockedToLandscape();

    @Source("resources/ic_action_screen_locked_to_portrait_xhdpi.png")
    ImageResource screenLockedToPortrait();

    @Source("resources/ic_action_screen_rotation_xhdpi.png")
    ImageResource screenRotation();

    @Source("resources/ic_action_sd_storage_xhdpi.png")
    ImageResource sdStorage();

    @Source("resources/ic_action_search_xhdpi.png")
    ImageResource search();

    @Source("resources/ic_action_secure_xhdpi.png")
    ImageResource secure();

    @Source("resources/ic_action_select_all_xhdpi.png")
    ImageResource selectAll();

    @Source("resources/ic_action_send_now_xhdpi.png")
    ImageResource sendNow();

    @Source("resources/ic_action_settings_xhdpi.png")
    ImageResource settings();

    @Source("resources/ic_action_share_xhdpi.png")
    ImageResource share();

    @Source("resources/ic_action_shuffle_xhdpi.png")
    ImageResource shuffle();

    @Source("resources/ic_action_slideshow_xhdpi.png")
    ImageResource slideshow();

    @Source("resources/ic_action_sort_by_size_xhdpi.png")
    ImageResource sortBySize();

    @Source("resources/ic_action_split_xhdpi.png")
    ImageResource split();

    @Source("resources/ic_action_stop_xhdpi.png")
    ImageResource stop();

    @Source("resources/ic_action_storage_xhdpi.png")
    ImageResource storage();

    @Source("resources/ic_action_switch_camera_xhdpi.png")
    ImageResource switchCamera();

    @Source("resources/ic_action_switch_video_xhdpi.png")
    ImageResource switchVideo();

    @Source("resources/ic_action_time_xhdpi.png")
    ImageResource time();

    @Source("resources/ic_action_undo_xhdpi.png")
    ImageResource undo();

    @Source("resources/ic_action_unread_xhdpi.png")
    ImageResource unread();

    @Source("resources/ic_action_upload_xhdpi.png")
    ImageResource upload();

    @Source("resources/ic_action_usb_xhdpi.png")
    ImageResource usb();

    @Source("resources/ic_action_video_xhdpi.png")
    ImageResource video();

    @Source("resources/ic_action_view_as_grid_xhdpi.png")
    ImageResource viewAsGrid();

    @Source("resources/ic_action_view_as_list_xhdpi.png")
    ImageResource viewAsList();

    @Source("resources/ic_action_volume_muted_xhdpi.png")
    ImageResource volumeMuted();

    @Source("resources/ic_action_volume_on_xhdpi.png")
    ImageResource volumeOn();

    @Source("resources/ic_action_warning_xhdpi.png")
    ImageResource warning();

    @Source("resources/ic_action_web_site_xhdpi.png")
    ImageResource webSite();


  }

  @Override
  public Images get() {
    return Resources.INSTANCE;
  }
}

package com.googlecode.mgwt.ui.client.widget.image;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

import com.googlecode.mgwt.ui.client.widget.image.ImageHolder.ImageHolderAppearance;

public class ImageHolderDefaultHighAppearance implements ImageHolderAppearance {
  interface Resources extends ClientBundle, Images {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source("resources/ic_action_about_hdpi.png")
    ImageResource about();

    @Source("resources/ic_action_accept_hdpi.png")
    ImageResource accept();

    @Source("resources/ic_action_accounts_hdpi.png")
    ImageResource accounts();

    @Source("resources/ic_action_add_alarm_hdpi.png")
    ImageResource addAlarm();

    @Source("resources/ic_action_add_group_hdpi.png")
    ImageResource addGroup();

    @Source("resources/ic_action_add_person_hdpi.png")
    ImageResource addPerson();

    @Source("resources/ic_action_add_to_queue_hdpi.png")
    ImageResource addToQueue();

    @Source("resources/ic_action_airplane_mode_off_hdpi.png")
    ImageResource airplaneModeOff();

    @Source("resources/ic_action_airplane_mode_on_hdpi.png")
    ImageResource airplaneModeOn();

    @Source("resources/ic_action_alarms_hdpi.png")
    ImageResource alarms();

    @Source("resources/ic_action_attachment_hdpi.png")
    ImageResource attachment();

    @Source("resources/ic_action_back_hdpi.png")
    ImageResource back();

    @Source("resources/ic_action_backspace_hdpi.png")
    ImageResource backspace();

    @Source("resources/ic_action_bad_hdpi.png")
    ImageResource bad();

    @Source("resources/ic_action_battery_hdpi.png")
    ImageResource battery();

    @Source("resources/ic_action_bightness_low_hdpi.png")
    ImageResource bightnessLow();

    @Source("resources/ic_action_bluetooth_connected_hdpi.png")
    ImageResource bluetoothConnected();

    @Source("resources/ic_action_bluetooth_hdpi.png")
    ImageResource bluetooth();

    @Source("resources/ic_action_bluetooth_searching_hdpi.png")
    ImageResource bluetoothSearching();

    @Source("resources/ic_action_brightness_auto_hdpi.png")
    ImageResource brightnessAuto();

    @Source("resources/ic_action_brightness_high_hdpi.png")
    ImageResource brightnessHigh();

    @Source("resources/ic_action_brightness_medium_hdpi.png")
    ImageResource brightnessMedium();

    @Source("resources/ic_action_call_hdpi.png")
    ImageResource call();

    @Source("resources/ic_action_camera_hdpi.png")
    ImageResource camera();

    @Source("resources/ic_action_cancel_hdpi.png")
    ImageResource cancel();

    @Source("resources/ic_action_cast_hdpi.png")
    ImageResource cast();

    @Source("resources/ic_action_cc_bcc_hdpi.png")
    ImageResource ccBcc();

    @Source("resources/ic_action_chat_hdpi.png")
    ImageResource chat();

    @Source("resources/ic_action_cloud_hdpi.png")
    ImageResource cloud();

    @Source("resources/ic_action_collapse_hdpi.png")
    ImageResource collapse();

    @Source("resources/ic_action_collection_hdpi.png")
    ImageResource collection();

    @Source("resources/ic_action_computer_hdpi.png")
    ImageResource computer();

    @Source("resources/ic_action_copy_hdpi.png")
    ImageResource copy();

    @Source("resources/ic_action_crop_hdpi.png")
    ImageResource crop();

    @Source("resources/ic_action_cut_hdpi.png")
    ImageResource cut();

    @Source("resources/ic_action_data_usage_hdpi.png")
    ImageResource dataUsage();

    @Source("resources/ic_action_dial_pad_hdpi.png")
    ImageResource dialPad();

    @Source("resources/ic_action_directions_hdpi.png")
    ImageResource directions();

    @Source("resources/ic_action_discard_hdpi.png")
    ImageResource discard();

    @Source("resources/ic_action_dock_hdpi.png")
    ImageResource dock();

    @Source("resources/ic_action_download_hdpi.png")
    ImageResource download();

    @Source("resources/ic_action_edit_hdpi.png")
    ImageResource edit();

    @Source("resources/ic_action_email_hdpi.png")
    ImageResource email();

    @Source("resources/ic_action_end_call_hdpi.png")
    ImageResource endCall();

    @Source("resources/ic_action_error_hdpi.png")
    ImageResource error();

    @Source("resources/ic_action_event_hdpi.png")
    ImageResource event();

    @Source("resources/ic_action_expand_hdpi.png")
    ImageResource expand();

    @Source("resources/ic_action_fast_forward_hdpi.png")
    ImageResource fastForward();

    @Source("resources/ic_action_favorite_hdpi.png")
    ImageResource favorite();

    @Source("resources/ic_action_flash_automatic_hdpi.png")
    ImageResource flashAutomatic();

    @Source("resources/ic_action_flash_off_hdpi.png")
    ImageResource flashOff();

    @Source("resources/ic_action_flash_on_hdpi.png")
    ImageResource flashOn();

    @Source("resources/ic_action_forward_hdpi.png")
    ImageResource forward();

    @Source("resources/ic_action_full_screen_hdpi.png")
    ImageResource fullScreen();

    @Source("resources/ic_action_gamepad_hdpi.png")
    ImageResource gamepad();

    @Source("resources/ic_action_go_to_today_hdpi.png")
    ImageResource goToToday();

    @Source("resources/ic_action_good_hdpi.png")
    ImageResource good();

    @Source("resources/ic_action_group_hdpi.png")
    ImageResource group();

    @Source("resources/ic_action_half_important_hdpi.png")
    ImageResource halfImportant();

    @Source("resources/ic_action_headphones_hdpi.png")
    ImageResource headphones();

    @Source("resources/ic_action_headset_hdpi.png")
    ImageResource headset();

    @Source("resources/ic_action_help_hdpi.png")
    ImageResource help();

    @Source("resources/ic_action_import_export_hdpi.png")
    ImageResource importExport();

    @Source("resources/ic_action_important_hdpi.png")
    ImageResource important();

    @Source("resources/ic_action_keyboard_hdpi.png")
    ImageResource keyboard();

    @Source("resources/ic_action_labels_hdpi.png")
    ImageResource labels();

    @Source("resources/ic_action_location_found_hdpi.png")
    ImageResource locationFound();

    @Source("resources/ic_action_location_off_hdpi.png")
    ImageResource locationOff();

    @Source("resources/ic_action_location_searching_hdpi.png")
    ImageResource locationSearching();

    @Source("resources/ic_action_make_available_offline_hdpi.png")
    ImageResource makeAvailableOffline();

    @Source("resources/ic_action_map_hdpi.png")
    ImageResource map();

    @Source("resources/ic_action_merge_hdpi.png")
    ImageResource merge();

    @Source("resources/ic_action_mic_hdpi.png")
    ImageResource mic();

    @Source("resources/ic_action_mic_muted_hdpi.png")
    ImageResource micMuted();

    @Source("resources/ic_action_mouse_hdpi.png")
    ImageResource mouse();

    @Source("resources/ic_action_network_cell_hdpi.png")
    ImageResource networkCell();

    @Source("resources/ic_action_network_wifi_hdpi.png")
    ImageResource networkWifi();

    @Source("resources/ic_action_new_account_hdpi.png")
    ImageResource newAccount();

    @Source("resources/ic_action_new_attachment_hdpi.png")
    ImageResource newAttachment();

    @Source("resources/ic_action_new_email_hdpi.png")
    ImageResource newEmail();

    @Source("resources/ic_action_new_event_hdpi.png")
    ImageResource newEvent();

    @Source("resources/ic_action_new_hdpi.png")
    ImageResource newItem();

    @Source("resources/ic_action_new_label_hdpi.png")
    ImageResource newLabel();

    @Source("resources/ic_action_new_picture_hdpi.png")
    ImageResource newPicture();

    @Source("resources/ic_action_next_hdpi.png")
    ImageResource next();

    @Source("resources/ic_action_next_item_hdpi.png")
    ImageResource nextItem();

    @Source("resources/ic_action_not_important_hdpi.png")
    ImageResource notImportant();

    @Source("resources/ic_action_not_secure_hdpi.png")
    ImageResource notSecure();

    @Source("resources/ic_action_overflow_hdpi.png")
    ImageResource overflow();

    @Source("resources/ic_action_paste_hdpi.png")
    ImageResource paste();

    @Source("resources/ic_action_pause_hdpi.png")
    ImageResource pause();

    @Source("resources/ic_action_pause_over_video_hdpi.png")
    ImageResource pauseOverVideo();

    @Source("resources/ic_action_person_hdpi.png")
    ImageResource person();

    @Source("resources/ic_action_phone_hdpi.png")
    ImageResource phone();

    @Source("resources/ic_action_picture_hdpi.png")
    ImageResource picture();

    @Source("resources/ic_action_place_hdpi.png")
    ImageResource place();

    @Source("resources/ic_action_play_hdpi.png")
    ImageResource play();

    @Source("resources/ic_action_play_over_video_hdpi.png")
    ImageResource playOverVideo();

    @Source("resources/ic_action_previous_hdpi.png")
    ImageResource previous();

    @Source("resources/ic_action_previous_item_hdpi.png")
    ImageResource previousItem();

    @Source("resources/ic_action_read_hdpi.png")
    ImageResource read();

    @Source("resources/ic_action_refresh_hdpi.png")
    ImageResource refresh();

    @Source("resources/ic_action_remove_hdpi.png")
    ImageResource remove();

    @Source("resources/ic_action_repeat_hdpi.png")
    ImageResource repeat();

    @Source("resources/ic_action_replay_hdpi.png")
    ImageResource replay();

    @Source("resources/ic_action_reply_all_hdpi.png")
    ImageResource replyAll();

    @Source("resources/ic_action_reply_hdpi.png")
    ImageResource reply();

    @Source("resources/ic_action_return_from_full_screen_hdpi.png")
    ImageResource returnFromFullScreen();

    @Source("resources/ic_action_rewind_hdpi.png")
    ImageResource rewind();

    @Source("resources/ic_action_ring_volume_hdpi.png")
    ImageResource ringVolume();

    @Source("resources/ic_action_rotate_left_hdpi.png")
    ImageResource rotateLeft();

    @Source("resources/ic_action_rotate_right_hdpi.png")
    ImageResource rotateRight();

    @Source("resources/ic_action_save_hdpi.png")
    ImageResource save();

    @Source("resources/ic_action_screen_locked_to_landscape_hdpi.png")
    ImageResource screenLockedToLandscape();

    @Source("resources/ic_action_screen_locked_to_portrait_hdpi.png")
    ImageResource screenLockedToPortrait();

    @Source("resources/ic_action_screen_rotation_hdpi.png")
    ImageResource screenRotation();

    @Source("resources/ic_action_sd_storage_hdpi.png")
    ImageResource sdStorage();

    @Source("resources/ic_action_search_hdpi.png")
    ImageResource search();

    @Source("resources/ic_action_secure_hdpi.png")
    ImageResource secure();

    @Source("resources/ic_action_select_all_hdpi.png")
    ImageResource selectAll();

    @Source("resources/ic_action_send_now_hdpi.png")
    ImageResource sendNow();

    @Source("resources/ic_action_settings_hdpi.png")
    ImageResource settings();

    @Source("resources/ic_action_share_hdpi.png")
    ImageResource share();

    @Source("resources/ic_action_shuffle_hdpi.png")
    ImageResource shuffle();

    @Source("resources/ic_action_slideshow_hdpi.png")
    ImageResource slideshow();

    @Source("resources/ic_action_sort_by_size_hdpi.png")
    ImageResource sortBySize();

    @Source("resources/ic_action_split_hdpi.png")
    ImageResource split();

    @Source("resources/ic_action_stop_hdpi.png")
    ImageResource stop();

    @Source("resources/ic_action_storage_hdpi.png")
    ImageResource storage();

    @Source("resources/ic_action_switch_camera_hdpi.png")
    ImageResource switchCamera();

    @Source("resources/ic_action_switch_video_hdpi.png")
    ImageResource switchVideo();

    @Source("resources/ic_action_time_hdpi.png")
    ImageResource time();

    @Source("resources/ic_action_undo_hdpi.png")
    ImageResource undo();

    @Source("resources/ic_action_unread_hdpi.png")
    ImageResource unread();

    @Source("resources/ic_action_upload_hdpi.png")
    ImageResource upload();

    @Source("resources/ic_action_usb_hdpi.png")
    ImageResource usb();

    @Source("resources/ic_action_video_hdpi.png")
    ImageResource video();

    @Source("resources/ic_action_view_as_grid_hdpi.png")
    ImageResource viewAsGrid();

    @Source("resources/ic_action_view_as_list_hdpi.png")
    ImageResource viewAsList();

    @Source("resources/ic_action_volume_muted_hdpi.png")
    ImageResource volumeMuted();

    @Source("resources/ic_action_volume_on_hdpi.png")
    ImageResource volumeOn();

    @Source("resources/ic_action_warning_hdpi.png")
    ImageResource warning();

    @Source("resources/ic_action_web_site_hdpi.png")
    ImageResource webSite();


  }

  @Override
  public Images get() {
    return Resources.INSTANCE;
  }
}

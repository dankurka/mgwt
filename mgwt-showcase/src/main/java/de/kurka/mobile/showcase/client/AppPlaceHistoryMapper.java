/*
 * Copyright 2010 Daniel Kurka
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
package de.kurka.mobile.showcase.client;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

import de.kurka.mobile.showcase.client.activities.AboutPlace.AboutPlaceTokenizer;
import de.kurka.mobile.showcase.client.activities.UIPlace.UIPlaceTokenizer;
import de.kurka.mobile.showcase.client.activities.animation.AnimationPlace.AnimationPlaceTokenizer;
import de.kurka.mobile.showcase.client.activities.animationdone.AnimationDissolvePlace.AnimationDissolvePlaceTokenizer;
import de.kurka.mobile.showcase.client.activities.animationdone.AnimationFadePlace.AnimationFadePlaceTokenizer;
import de.kurka.mobile.showcase.client.activities.animationdone.AnimationFlipPlace.AnimationFlipPlaceTokenizer;
import de.kurka.mobile.showcase.client.activities.animationdone.AnimationPopPlace.AnimationPopPlaceTokenizer;
import de.kurka.mobile.showcase.client.activities.animationdone.AnimationSlidePlace.AnimationSlidePlaceTokenizer;
import de.kurka.mobile.showcase.client.activities.animationdone.AnimationSlideUpPlace.AnimationSlideUpPlaceTokenizer;
import de.kurka.mobile.showcase.client.activities.animationdone.AnimationSwapPlace.AnimationSwapPlaceTokenizer;
import de.kurka.mobile.showcase.client.activities.button.ButtonPlace.ButtonPlaceTokenizer;
import de.kurka.mobile.showcase.client.activities.buttonbar.ButtonBarPlace.ButtonBarPlaceTokenizer;
import de.kurka.mobile.showcase.client.activities.elements.ElementsPlace.ElementsPlaceTokenizer;
import de.kurka.mobile.showcase.client.activities.popup.PopupPlace.PopupPlaceTokenizer;
import de.kurka.mobile.showcase.client.activities.progressbar.ProgressBarPlace.ProgressBarPlaceTokenizer;
import de.kurka.mobile.showcase.client.activities.scrollwidget.ScrollWidgetPlace.ScrollWidgetPlaceTokenizer;
import de.kurka.mobile.showcase.client.activities.searchbox.SearchBoxPlace.SearchBoxPlaceTokenizer;
import de.kurka.mobile.showcase.client.activities.slider.SliderPlace.SliderPlaceTokenizer;
import de.kurka.mobile.showcase.client.activities.tabbar.TabBarPlace.TabBarPlaceTokenizer;
import de.kurka.mobile.showcase.client.places.HomePlace.HomePlaceTokenizer;

/**
 * @author Daniel Kurka
 *
 */
@WithTokenizers({ HomePlaceTokenizer.class, UIPlaceTokenizer.class, ScrollWidgetPlaceTokenizer.class, AboutPlaceTokenizer.class, ButtonPlaceTokenizer.class, AnimationDissolvePlaceTokenizer.class,
		AnimationFadePlaceTokenizer.class, AnimationFlipPlaceTokenizer.class, AnimationPlaceTokenizer.class, AnimationPopPlaceTokenizer.class, AnimationSlidePlaceTokenizer.class,
		AnimationSlideUpPlaceTokenizer.class, AnimationSwapPlaceTokenizer.class, ButtonBarPlaceTokenizer.class, ElementsPlaceTokenizer.class, PopupPlaceTokenizer.class,
		ProgressBarPlaceTokenizer.class, SearchBoxPlaceTokenizer.class, SliderPlaceTokenizer.class, TabBarPlaceTokenizer.class })
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}

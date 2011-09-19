package de.kurka.mobile.contact.client;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

import de.kurka.mobile.contact.client.activities.HomePlace;

@WithTokenizers({ HomePlace.HomePlaceTokenizer.class })
public interface ContactPlaceHistoryMapper extends PlaceHistoryMapper {

}

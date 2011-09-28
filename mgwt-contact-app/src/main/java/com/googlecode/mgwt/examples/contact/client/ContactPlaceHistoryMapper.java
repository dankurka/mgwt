package com.googlecode.mgwt.examples.contact.client;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.googlecode.mgwt.examples.contact.client.activities.HomePlace;


@WithTokenizers({ HomePlace.HomePlaceTokenizer.class })
public interface ContactPlaceHistoryMapper extends PlaceHistoryMapper {

}

package com.googlecode.mgwt.examples.contact.client;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.googlecode.mgwt.examples.contact.client.activities.HomePlace;
import com.googlecode.mgwt.examples.contact.client.activities.ShowGroupPlace;

@WithTokenizers({ HomePlace.HomePlaceTokenizer.class, ShowGroupPlace.ShowGroupPlaceTokenizer.class })
public interface ContactPlaceHistoryMapper extends PlaceHistoryMapper {

}

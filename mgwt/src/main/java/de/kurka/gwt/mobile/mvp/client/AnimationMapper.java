/**
 * 28.12.2010
 * created by kurt
 */
package de.kurka.gwt.mobile.mvp.client;

import com.google.gwt.place.shared.Place;

/**
 * @author kurt
 *
 */
public interface AnimationMapper {
	public Animation getAnimation(Place oldPlace, Place newPlace);
}

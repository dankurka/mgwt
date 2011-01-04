/**
 * 27.12.2010
 * created by kurt
 */
package de.kurka.gwt.mobile.mvp.client;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author kurt
 *
 */
public interface AnimatableDisplay extends IsWidget {
	public void setFirstWidget(IsWidget w);

	public void setSecondWidget(IsWidget w);

	/**
	 * @param animation
	 * @param currentIsFirst
	 */
	public void animate(Animation animation, boolean currentIsFirst);
}

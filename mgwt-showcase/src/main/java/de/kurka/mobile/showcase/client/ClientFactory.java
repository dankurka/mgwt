/**
 * 15.11.2010
 * created by kurt
 */
package de.kurka.mobile.showcase.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;

import de.kurka.mobile.showcase.client.activities.AboutView;
import de.kurka.mobile.showcase.client.activities.AnimationDoneView;
import de.kurka.mobile.showcase.client.activities.AnimationView;
import de.kurka.mobile.showcase.client.activities.ButtonBarView;
import de.kurka.mobile.showcase.client.activities.ElementsView;
import de.kurka.mobile.showcase.client.activities.HomeView;
import de.kurka.mobile.showcase.client.activities.ScrollWidgetView;
import de.kurka.mobile.showcase.client.activities.SearchBoxView;
import de.kurka.mobile.showcase.client.activities.UIView;

/**
 * @author kurt
 *
 */
public interface ClientFactory {
	public HomeView getHomeView();

	public EventBus getEventBus();

	public PlaceController getPlaceController();

	/**
	 * @return
	 */
	public UIView getUIView();

	public AboutView getAboutView();

	public AnimationView getAnimationView();

	public AnimationDoneView getAnimationDoneView();

	public ScrollWidgetView getScrollWidgetView();

	public ElementsView getElementsView();

	public ButtonBarView getButtonBarView();

	public SearchBoxView getSearchBoxView();

}

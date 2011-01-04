/**
 * 30.12.2010
 * created by kurt
 */
package de.kurka.mobile.showcase.client.activities;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.SimpleTouchHandler;
import de.kurka.mobile.showcase.client.ClientFactory;
import de.kurka.mobile.showcase.client.places.UIPlace;

/**
 * @author kurt
 *
 */
public class ElementsActivity extends AbstractActivity {

	private final ClientFactory clientFactory;
	private HandlerRegistration handler;

	/**
	 * 
	 */
	public ElementsActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;

	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		ElementsView view = clientFactory.getElementsView();

		handler = view.getBackButton().addSimpleTouchHandler(new SimpleTouchHandler() {

			@Override
			public void onTouch() {
				clientFactory.getPlaceController().goTo(new UIPlace());

			}
		});

		panel.setWidget(view);
	}

	@Override
	public void onStop() {
		super.onStop();

		if (handler != null) {
			handler.removeHandler();
			handler = null;
		}
	}

}

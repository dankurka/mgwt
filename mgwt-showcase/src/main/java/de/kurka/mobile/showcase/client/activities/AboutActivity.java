/**
 * 28.12.2010
 * created by kurt
 */
package de.kurka.mobile.showcase.client.activities;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.SimpleTouchHandler;
import de.kurka.mobile.showcase.client.ClientFactory;
import de.kurka.mobile.showcase.client.places.HomePlace;

/**
 * @author kurt
 *
 */
public class AboutActivity extends AbstractActivity {

	private final ClientFactory clientFactory;
	private HandlerRegistration addSimpleTouchHandler;

	public AboutActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;

	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		AboutView aboutView = clientFactory.getAboutView();

		aboutView.setBackButtonText("Home");

		addSimpleTouchHandler = aboutView.getBackButton().addSimpleTouchHandler(new SimpleTouchHandler() {

			@Override
			public void onTouch() {
				clientFactory.getPlaceController().goTo(new HomePlace());

			}
		});

		panel.setWidget(aboutView);
	}

	@Override
	public void onStop() {

		super.onStop();
		if (addSimpleTouchHandler != null) {
			addSimpleTouchHandler.removeHandler();
			addSimpleTouchHandler = null;
		}
	}

}

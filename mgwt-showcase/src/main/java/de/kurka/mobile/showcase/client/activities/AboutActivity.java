/**
 * 28.12.2010
 * created by kurt
 */
package de.kurka.mobile.showcase.client.activities;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.SimpleTouchHandler;
import de.kurka.gwt.mobile.mvp.client.MGWTAbstractActivity;
import de.kurka.mobile.showcase.client.ClientFactory;
import de.kurka.mobile.showcase.client.places.HomePlace;

/**
 * @author Daniel Kurka
 *
 */
public class AboutActivity extends MGWTAbstractActivity {

	private final ClientFactory clientFactory;

	public AboutActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;

	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		AboutView aboutView = clientFactory.getAboutView();

		aboutView.setBackButtonText("Home");

		addHandlerRegistration(aboutView.getBackButton().addSimpleTouchHandler(new SimpleTouchHandler() {

			@Override
			public void onTouch() {
				clientFactory.getPlaceController().goTo(new HomePlace());

			}
		}));

		panel.setWidget(aboutView);
	}

}

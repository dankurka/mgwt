/**
 * 29.12.2010
 * created by kurt
 */
package de.kurka.mobile.showcase.client.activities;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.SimpleTouchHandler;
import de.kurka.mobile.showcase.client.ClientFactory;
import de.kurka.mobile.showcase.client.places.AnimationPlace;

/**
 * @author kurt
 *
 */
public class AnimationDoneActivity extends AbstractActivity {

	private final ClientFactory clientFactory;
	private HandlerRegistration handler;

	/**
	 * 
	 */
	public AnimationDoneActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;

	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		AnimationDoneView view = clientFactory.getAnimationDoneView();

		handler = view.getBackButton().addSimpleTouchHandler(new SimpleTouchHandler() {

			@Override
			public void onTouch() {
				clientFactory.getPlaceController().goTo(new AnimationPlace());

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

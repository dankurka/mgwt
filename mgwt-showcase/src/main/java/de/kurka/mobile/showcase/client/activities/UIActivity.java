/**
 * 28.12.2010
 * created by kurt
 */
package de.kurka.mobile.showcase.client.activities;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.SimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.widget.celllist.CellSelectedEvent;
import de.kurka.gwt.mobile.ui.client.widget.celllist.CellSelectedHandler;
import de.kurka.mobile.showcase.client.ClientFactory;
import de.kurka.mobile.showcase.client.places.ButtonBarPlace;
import de.kurka.mobile.showcase.client.places.ElementsPlace;
import de.kurka.mobile.showcase.client.places.HomePlace;
import de.kurka.mobile.showcase.client.places.ScrollWidgetPlace;
import de.kurka.mobile.showcase.client.places.SearchBoxPlace;
import de.kurka.mobile.showcase.client.places.TabBarPlace;

/**
 * @author kurt
 *
 */
public class UIActivity extends AbstractActivity {

	private final ClientFactory clientFactory;
	private HandlerRegistration addSimpleTouchHandler;
	private HandlerRegistration handler;

	public UIActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;

	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		UIView view = clientFactory.getUIView();

		view.setBackButtonText("Home");
		view.setTitle("UI");

		addSimpleTouchHandler = view.getBackButton().addSimpleTouchHandler(new SimpleTouchHandler() {

			@Override
			public void onTouch() {
				clientFactory.getPlaceController().goTo(new HomePlace());

			}
		});

		view.renderItems(createItems());

		handler = view.getList().addCellSelectedHandler(new CellSelectedHandler() {

			@Override
			public void onCellSelected(CellSelectedEvent event) {
				int index = event.getIndex();

				switch (index) {
				case 0:
					clientFactory.getPlaceController().goTo(new ScrollWidgetPlace());

					break;
				case 1:
					clientFactory.getPlaceController().goTo(new ElementsPlace());

					break;

				case 2:

					clientFactory.getPlaceController().goTo(new ButtonBarPlace());
					break;

				case 3:

					clientFactory.getPlaceController().goTo(new SearchBoxPlace());
					break;

				case 4:

					clientFactory.getPlaceController().goTo(new TabBarPlace());
					break;

				default:
					break;
				}

			}
		});

		panel.setWidget(view);

	}

	/**
	 * @return
	 */
	private List<Item> createItems() {
		ArrayList<Item> list = new ArrayList<Item>();

		list.add(new Item("Scroll Widget"));
		list.add(new Item("Elements"));
		list.add(new Item("ButtonBar"));
		list.add(new Item("Searchbox"));
		list.add(new Item("TabBar"));

		return list;
	}

	@Override
	public void onStop() {
		super.onStop();
		if (addSimpleTouchHandler != null) {
			addSimpleTouchHandler.removeHandler();
			addSimpleTouchHandler = null;
		}

		if (handler != null) {
			handler.removeHandler();
			handler = null;
		}
	}

}

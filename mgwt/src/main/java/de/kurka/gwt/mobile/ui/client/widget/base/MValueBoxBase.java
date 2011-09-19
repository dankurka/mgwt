package de.kurka.gwt.mobile.ui.client.widget.base;

import java.text.ParseException;

import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.ui.client.adapters.ValueBoxEditor;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.AutoDirectionHandler;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.i18n.shared.HasDirectionEstimator;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ValueBoxBase;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;

import de.kurka.gwt.mobile.dom.client.event.touch.HasTouchHandlers;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchCancelHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchEndHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchMoveHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchStartHandler;
import de.kurka.gwt.mobile.ui.client.theme.base.InputCss;
import de.kurka.gwt.mobile.ui.client.widget.touch.TouchPanel;

public class MValueBoxBase<T> extends Composite implements HasTouchHandlers, HasPlaceHolder, HasChangeHandlers, HasName, HasDirectionEstimator, HasValue<T>, AutoDirectionHandler.Target,
		IsEditor<ValueBoxEditor<T>> {

	private TouchPanel main;
	protected final ValueBoxBase<T> box;
	private FlowPanel cover;

	public MValueBoxBase(InputCss css, final ValueBoxBase<T> box) {
		this.box = box;
		box.addStyleName(css.box());
		main = new TouchPanel();
		initWidget(main);

		css.ensureInjected();

		cover = new FlowPanel();
		cover.addStyleName(css.cover());

		//		if (MGWTUtil.getFeatureDetection().isIOs() && false) {
		//			main.add(cover);
		//		}

		main.add(box);

		//		if (MGWTUtil.getFeatureDetection().isIOs() && false) {
		//			cover.addDomHandler(new ClickHandler() {
		//
		//				@Override
		//				public void onClick(ClickEvent event) {
		//					cover.getElement().getStyle().setDisplay(Display.NONE);
		//					box.setFocus(true);
		//
		//				}
		//			}, ClickEvent.getType());
		//
		//			box.addBlurHandler(new BlurHandler() {
		//
		//				@Override
		//				public void onBlur(BlurEvent event) {
		//					cover.getElement().getStyle().setDisplay(Display.BLOCK);
		//
		//				}
		//			});
		//
		//			box.addFocusHandler(new FocusHandler() {
		//
		//				@Override
		//				public void onFocus(FocusEvent event) {
		//					cover.getElement().getStyle().setDisplay(Display.NONE);
		//
		//				}
		//			});
		//		}
	}

	public void setPlaceHolder(String value) {
		getElement().setAttribute("placeholder", value);
	}

	public String getPlaceHolder() {
		return getElement().getAttribute("placeholder");
	}

	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return box.addChangeHandler(handler);
	}

	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<T> handler) {
		return box.addValueChangeHandler(handler);
	}

	public ValueBoxEditor<T> asEditor() {
		return box.asEditor();
	}

	public void cancelKey() {
		box.cancelKey();
	}

	public int getCursorPos() {
		return box.getCursorPos();
	}

	public Direction getDirection() {
		return box.getDirection();
	}

	public DirectionEstimator getDirectionEstimator() {
		return box.getDirectionEstimator();
	}

	public String getName() {
		return box.getName();
	}

	public String getSelectedText() {
		return box.getSelectedText();
	}

	public int getSelectionLength() {
		return box.getSelectionLength();
	}

	public String getText() {
		return box.getText();
	}

	public T getValue() {
		return box.getValue();
	}

	public T getValueOrThrow() throws ParseException {
		return box.getValueOrThrow();
	}

	public boolean isReadOnly() {
		return box.isReadOnly();
	}

	@Override
	public void onBrowserEvent(Event event) {
		box.onBrowserEvent(event);
	}

	public void selectAll() {
		box.selectAll();
	}

	public void setAlignment(TextAlignment align) {
		box.setAlignment(align);
	}

	public void setCursorPos(int pos) {
		box.setCursorPos(pos);
	}

	public void setDirection(Direction direction) {
		box.setDirection(direction);
	}

	public void setDirectionEstimator(boolean enabled) {
		box.setDirectionEstimator(enabled);
	}

	public void setDirectionEstimator(DirectionEstimator directionEstimator) {
		box.setDirectionEstimator(directionEstimator);
	}

	public void setName(String name) {
		box.setName(name);
	}

	public void setReadOnly(boolean readOnly) {
		box.setReadOnly(readOnly);
	}

	public void setSelectionRange(int pos, int length) {
		box.setSelectionRange(pos, length);
	}

	public void setText(String text) {
		box.setText(text);
	}

	public void setValue(T value) {
		box.setValue(value);
	}

	public void setValue(T value, boolean fireEvents) {
		box.setValue(value, fireEvents);
	}

	@Override
	public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
		return box.addKeyUpHandler(handler);
	}

	@Override
	public HandlerRegistration addTouchStartHandler(TouchStartHandler handler) {
		return main.addTouchStartHandler(handler);
	}

	@Override
	public HandlerRegistration addTouchMoveHandler(TouchMoveHandler handler) {
		return main.addTouchMoveHandler(handler);
	}

	@Override
	public HandlerRegistration addTouchCancelHandler(TouchCancelHandler handler) {
		return main.addTouchCancelHandler(handler);
	}

	@Override
	public HandlerRegistration addTouchEndHandler(TouchEndHandler handler) {
		return main.addTouchEndHandler(handler);
	}

	@Override
	public HandlerRegistration addTouchHandler(TouchHandler handler) {
		return main.addTouchHandler(handler);
	}

}

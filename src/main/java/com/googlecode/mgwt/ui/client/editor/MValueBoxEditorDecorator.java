/**
 * 
 */
package com.googlecode.mgwt.ui.client.editor;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.ui.client.adapters.ValueBoxEditor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.ValueBoxBase;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.base.MValueBoxBase;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;

/**
 * This MValueBoxEditorDecorator is more or less a copy clone of
 * {@link ValueBoxEditor} and is needed to do JSR-303 BeanValidation. Only
 * difference is the child added here must be of type {@link MValueBoxBase}.
 * 
 * <p>
 * <h3>Use in UiBinder Templates</h3>
 * <p>
 * The decorator may have exactly one {@link MValueBoxBase} added though an
 * <code>&lt;e:valuebox></code> child tag.
 * <p>
 * For example:
 * 
 * <pre>
 * &#064;UiField
 * MValueBoxEditorDecorator&lt;String&gt; name;
 * </pre>
 * 
 * <pre>
 * &lt;e:MValueBoxEditorDecorator ui:field='name'>
 *   &lt;e:mvaluebox>
 *     &lt;i:MTextBox />
 *   &lt;/e:mvaluebox>
 * &lt;/e:MValueBoxEditorDecorator>
 * </pre>
 * 
 * @param <T>
 *            the type which is edited, i.e. String for {@link MTextBox}.
 * 
 * @author Christoph Guse
 * 
 */
public class MValueBoxEditorDecorator<T> extends Composite implements
	HasEditorErrors<T>, IsEditor<ValueBoxEditor<T>> {

    interface Binder extends UiBinder<Widget, MValueBoxEditorDecorator<?>> {
	Binder BINDER = GWT.create(Binder.class);
    }

    private ValueBoxEditor<T> editor;

    @UiField
    SimplePanel contents;

    @UiField
    DivElement errorLabel;

    /**
     * Constructs a ValueBoxEditorDecorator, UI is taken from
     * MValueBoxEditorDecorator.ui.xml.
     */
    @UiConstructor
    public MValueBoxEditorDecorator() {
	initWidget(Binder.BINDER.createAndBindUi(this));
    }

    /**
     * Constructs a ValueBoxEditorDecorator using a {@link ValueBoxBase} widget
     * and a {@link ValueBoxEditor} editor.
     * 
     * @param widget
     *            the widget
     * @param editor
     *            the editor
     */
    public MValueBoxEditorDecorator(MValueBoxBase<T> widget,
	    ValueBoxEditor<T> editor) {
	this();
	contents.add(widget);
	this.editor = editor;
    }

    /**
     * Returns the associated {@link ValueBoxEditor}.
     * 
     * @return a {@link ValueBoxEditor} instance
     * @see #setEditor(ValueBoxEditor)
     */
    @Override
    public ValueBoxEditor<T> asEditor() {
	return editor;
    }

    /**
     * Sets the associated {@link ValueBoxEditor}.
     * 
     * @param editor
     *            a {@link ValueBoxEditor} instance
     * @see #asEditor()
     */
    public void setEditor(ValueBoxEditor<T> editor) {
	this.editor = editor;
    }

    /**
     * Set the widget that the EditorPanel will display. This method will
     * automatically call {@link #setEditor}.
     * 
     * @param widget
     *            a {@link ValueBoxBase} widget
     */
    @UiChild(limit = 1, tagname = "mvaluebox")
    public void setMValueBox(MValueBoxBase<T> widget) {
	contents.add(widget);
	setEditor(widget.asEditor());
    }

    /**
     * The default implementation will display, but not consume, received errors
     * whose {@link EditorError#getEditor() getEditor()} method returns the
     * Editor passed into {@link #setEditor}.
     * 
     * @param errors
     *            a List of {@link EditorError} instances
     */
    @Override
    public void showErrors(List<EditorError> errors) {
	StringBuilder sb = new StringBuilder();
	for (EditorError error : errors) {
	    if (error.getEditor().equals(editor)) {
		sb.append("\n").append(error.getMessage());
	    }
	}

	if (sb.length() == 0) {
	    errorLabel.setInnerText("");
	    errorLabel.getStyle().setDisplay(Display.NONE);
	    return;
	}

	errorLabel.setInnerText(sb.substring(1));
	errorLabel.getStyle().setDisplay(Display.INLINE_BLOCK);
    }

    /**
     * Shows the given error message.
     * 
     * @param errorMessage
     */
    public void showError(String errorMessage) {
	errorLabel.setInnerText(errorMessage);
	errorLabel.getStyle().setDisplay(Display.INLINE_BLOCK);
    }

}

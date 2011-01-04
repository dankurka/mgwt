/**
 * 13.11.2010
 * created by kurt
 */
package de.kurka.gwt.mobile.ui.client.widget.celllist;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * @author kurt
 *
 */
public interface Cell<T> {
	public void render(SafeHtmlBuilder safeHtmlBuilder, T model);

	public boolean canBeSelected(T model);

}

package com.googlecode.mgwt.ui.client.widget.experimental;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.ListCss;
import com.googlecode.mgwt.ui.client.widget.CellList;
import com.googlecode.mgwt.ui.client.widget.celllist.Cell;

public class GroupingCellList<G, T> extends CellList<T> implements HasSelectionHandlers<T> {

	private final Cell<G> header;
	private final Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	private final Map<Integer, T> modelMap = new HashMap<Integer, T>();

	public interface CellGroup<G, T> {
		public String getKey();

		public G getGroup();

		public List<T> getMember();
	}

	interface HeaderTemplate extends SafeHtmlTemplates {
		@SafeHtmlTemplates.Template("<li class=\"{1}\" >{0}</li>")
		SafeHtml li(SafeHtml cellContents, String classes);

	}

	protected static final HeaderTemplate HEADER_LI_TEMPLATE = GWT.create(HeaderTemplate.class);

	public GroupingCellList(Cell<T> cell, Cell<G> header) {
		this(cell, header, MGWTStyle.getTheme().getMGWTClientBundle().getListCss());
	}

	public GroupingCellList(Cell<T> cell, Cell<G> header, ListCss css) {
		super(cell, css);
		this.header = header;

		setGroup(false);
	}

	public void renderGroup(List<CellGroup<G, T>> groups) {
		SafeHtmlBuilder sb = new SafeHtmlBuilder();

		int count = 0;

		map.clear();
		modelMap.clear();

		int groupCounter = 0;
		int renderedGroups = 0;

		for (CellGroup<G, T> cellGroup : groups) {

			if (cellGroup.getMember().isEmpty()) {
				groupCounter++;
				continue;
			}

			// render header of group
			SafeHtmlBuilder headerBuilder = new SafeHtmlBuilder();
			header.render(headerBuilder, cellGroup.getGroup());

			sb.append(HEADER_LI_TEMPLATE.li(headerBuilder.toSafeHtml(), css.listHeadElement()));

			// render members of group
			List<T> models = cellGroup.getMember();
			for (T model : models) {
				SafeHtmlBuilder cellBuilder = new SafeHtmlBuilder();

				String clazz = "";
				if (cell.canBeSelected(model)) {
					clazz = css.canbeSelected() + " ";
				}

				if (isGroup()) {
					clazz = css.group() + " ";
				}

				cell.render(cellBuilder, model);

				sb.append(LI_TEMPLATE.li(count, clazz, cellBuilder.toSafeHtml()));

				modelMap.put(count, model);

				count++;
			}

			map.put(renderedGroups, groupCounter);

			renderedGroups++;
			groupCounter++;

		}

		final String html = sb.toSafeHtml().asString();

		getElement().setInnerHTML(html);

		if (count > 0) {
			String innerHTML = getElement().getInnerHTML();
			if ("".equals(innerHTML.trim())) {
				fixBug(html);
			}
		}
	}

	public String getHeaderSelector() {
		return "li." + css.listHeadElement();
	}

	public ListCss getListCss() {
		return css;

	}

	public Map<Integer, Integer> getMapping() {
		return map;
	}

	public String renderGroupHeader(G group) {
		SafeHtmlBuilder headerBuilder = new SafeHtmlBuilder();
		header.render(headerBuilder, group);
		return headerBuilder.toSafeHtml().asString();
	}

	@Override
	protected void fireSelectionAtIndex(int index) {
		T t = modelMap.get(index);
		if (t != null) {
			SelectionEvent.fire(this, t);
		}
	}

	@Override
	public HandlerRegistration addSelectionHandler(SelectionHandler<T> handler) {
		return addHandler(handler, SelectionEvent.getType());
	}
}

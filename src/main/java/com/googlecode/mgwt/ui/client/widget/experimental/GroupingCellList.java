package com.googlecode.mgwt.ui.client.widget.experimental;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.googlecode.mgwt.ui.client.theme.base.ListCss;
import com.googlecode.mgwt.ui.client.widget.CellList;
import com.googlecode.mgwt.ui.client.widget.celllist.Cell;

public class GroupingCellList<G, T> extends CellList<T> {

	private final Cell<G> header;

	public interface CellGroup<G, T> {
		public G getGroup();

		public List<T> getMember();
	}

	interface HeaderTemplate extends SafeHtmlTemplates {
		@SafeHtmlTemplates.Template("<li>{0}</li>")
		SafeHtml li(SafeHtml cellContents);

	}

	protected static final HeaderTemplate HEADER_LI_TEMPLATE = GWT.create(HeaderTemplate.class);

	public GroupingCellList(Cell<T> cell, Cell<G> header, ListCss css) {
		super(cell, css);
		this.header = header;
	}

	public void renderGroup(List<CellGroup<G, T>> groups) {
		SafeHtmlBuilder sb = new SafeHtmlBuilder();

		int count = 0;

		for (CellGroup<G, T> cellGroup : groups) {
			// render header of group
			SafeHtmlBuilder headerBuilder = new SafeHtmlBuilder();
			header.render(headerBuilder, cellGroup.getGroup());

			sb.append(HEADER_LI_TEMPLATE.li(headerBuilder.toSafeHtml()));

			// render members of group
			List<T> models = cellGroup.getMember();
			for (T model : models) {
				SafeHtmlBuilder cellBuilder = new SafeHtmlBuilder();

				String clazz = "";
				if (cell.canBeSelected(model)) {
					clazz = css.group() + " ";
				}

				cell.render(cellBuilder, model);

				sb.append(LI_TEMPLATE.li(count, clazz, cellBuilder.toSafeHtml()));
				count++;
			}

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
}

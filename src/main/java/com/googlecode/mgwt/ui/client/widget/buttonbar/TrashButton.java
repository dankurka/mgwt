/*
 * Copyright 2011 Daniel Kurka
 * 
 * Licensed under thimport com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.TrashButtonCss;
 obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.TrashButtonCss;

/**
 * <p>TrashButton class.</p>
 *
 * @author kurt
 * @version $Id: $
 */
public class TrashButton extends ButtonBarButtonBase {

	/**
	 * <p>Constructor for TrashButton.</p>
	 */
	public TrashButton() {
		this(MGWTStyle.getTheme().getMGWTClientBundle().getTrashButtonCss());
	}

	/**
	 * <p>Constructor for TrashButton.</p>
	 *
	 * @param trashButtonCss a {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.TrashButtonCss} object.
	 */
	public TrashButton(TrashButtonCss trashButtonCss) {
		super(trashButtonCss);
		addStyleName(trashButtonCss.trash());
	}

}

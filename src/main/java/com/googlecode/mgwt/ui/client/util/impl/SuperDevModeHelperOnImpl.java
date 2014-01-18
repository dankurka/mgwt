package com.googlecode.mgwt.ui.client.util.impl;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.TextDecoration;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;

public abstract class SuperDevModeHelperOnImpl implements SuperDevModeHelper {

	@Override
	public void showDevMode() {
		final Element overlayDiv =  Document.get().createDivElement();
		final Element dialog =  Document.get().createDivElement();
		final Element onButton = createButton("Super Dev Mode On");
		final Element offButton = createButton("Super Dev Mode Off");
		final Element closeButton = createButton("Close Dialog");
		
		overlayDiv.getStyle().setZIndex(1000);
		overlayDiv.getStyle().setPosition(Position.ABSOLUTE);
		overlayDiv.getStyle().setTop(0, Unit.PX);
		overlayDiv.getStyle().setLeft(0, Unit.PX);
		overlayDiv.getStyle().setRight(0, Unit.PX);
		overlayDiv.getStyle().setBottom(0, Unit.PX);
		overlayDiv.getStyle().setOpacity(0.5);
		overlayDiv.getStyle().setBackgroundColor("#000");
		
		Document.get().getBody().appendChild(overlayDiv);
		
		DOM.sinkEvents(overlayDiv, Event.ONCLICK);
		DOM.setEventListener(overlayDiv, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				switch (event.getTypeInt()) {
				case Event.ONCLICK:
					cleanUp(overlayDiv, dialog, onButton, offButton, closeButton);
					break;
				default:
					break;
				}
			}
		});
		
		
		dialog.getStyle().setZIndex(1001);
		dialog.getStyle().setPosition(Position.FIXED);
		dialog.getStyle().setTop(20, Unit.PT);
		dialog.getStyle().setLeft(20, Unit.PT);
		dialog.getStyle().setBackgroundColor("white");
		
		dialog.getStyle().setBorderColor("#ccc");
		dialog.getStyle().setBorderStyle(BorderStyle.SOLID);
		dialog.getStyle().setBorderWidth(4, Unit.PX);
		dialog.getStyle().setPadding(1, Unit.EM);
		
		Document.get().getBody().appendChild(dialog);
		
		
		DOM.sinkEvents(onButton, Event.ONCLICK);
		DOM.setEventListener(onButton, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				cleanUp(overlayDiv, dialog, onButton, offButton, closeButton);
				devModeOn();
			}
		});
		
		dialog.appendChild(onButton);
		
		dialog.appendChild(DOM.createElement("br"));
		dialog.appendChild(DOM.createElement("br"));
		dialog.appendChild(DOM.createElement("br"));
		
		
		DOM.sinkEvents(offButton, Event.ONCLICK);
		DOM.setEventListener(offButton, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				cleanUp(overlayDiv, dialog, onButton, offButton, closeButton);
				devModeOff();
			}
		});
		
		dialog.appendChild(offButton);
		
		dialog.appendChild(DOM.createElement("br"));
		dialog.appendChild(DOM.createElement("br"));
		dialog.appendChild(DOM.createElement("br"));
		
		
		DOM.sinkEvents(closeButton, Event.ONCLICK);
		DOM.setEventListener(closeButton, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				cleanUp(overlayDiv, dialog, onButton, offButton, closeButton);
				
			}
		});
		
		dialog.appendChild(closeButton);		

	}
	
	protected native void devModeOff() /*-{
		var toRemove = []
          
          
          for(var i = 0; i<$wnd.sessionStorage.length; i++) {
          	var key = $wnd.sessionStorage.key(i);
          	if (key.indexOf('__gwtDevModeHook:') == 0) {
          		toRemove.push(key);
          	}
          }
          for (var j = 0; j<toRemove.length; j++) {
          	$wnd.sessionStorage.removeItem(toRemove[j]);
          } 
          $wnd.location.reload();
		
	}-*/;

	protected void devModeOn() {
		String serverUrl = getServerUrl();
		if (!serverUrl.endsWith("/")) {
			serverUrl += "/";
		}
		setBookMarklet(serverUrl);

		Element head = getHead();

		ScriptElement scriptElement = Document.get().createScriptElement();
		scriptElement.setSrc(serverUrl + "dev_mode_on.js");
		head.appendChild(scriptElement);
		
	}

	protected void cleanUp(Element overlayDiv, Element dialog, Element onButton, Element offButton, Element closeButton) {
		DOM.setEventListener(offButton, null);
		DOM.setEventListener(onButton, null);
		DOM.setEventListener(overlayDiv, null);
		
		overlayDiv.removeFromParent();
		dialog.removeFromParent();
		
	}

	protected Element createButton(String text){
		AnchorElement buttonElement = Document.get().createAnchorElement();
		buttonElement.getStyle().setFontSize(12, Unit.PT);
		buttonElement.getStyle().setColor("#000");
		buttonElement.getStyle().setTextDecoration(TextDecoration.NONE);
		buttonElement.getStyle().setBackgroundColor("#ddd");
		buttonElement.getStyle().setMarginLeft(1, Unit.EM);
		
		buttonElement.getStyle().setBorderColor("black");
		buttonElement.getStyle().setBorderWidth(1, Unit.PX);
		buttonElement.getStyle().setBorderStyle(BorderStyle.SOLID);
		buttonElement.getStyle().setPadding(3, Unit.PT);
		buttonElement.setHref("#");
		
		buttonElement.setInnerText(text);
		return buttonElement;
	}
	
	

	protected abstract String getServerUrl();

	protected Element getHead() {
		NodeList<Element> elementsByTagName = Document.get().getElementsByTagName("head");

		if (elementsByTagName.getLength() != 1) {
			throw new RuntimeException("there is no head element");
		}

		return elementsByTagName.getItem(0);
	}

	protected native void setBookMarklet(String serverUrl)/*-{
		$wnd.__gwt_bookmarklet_params = {
			'server_url' : serverUrl
		};
	}-*/;

	protected native void makeDialog() /*-{
		function makeOverlay() {
			var overlay = document.createElement('div');
			overlay.style.zIndex = 1000;
			overlay.style.position = 'absolute';
			overlay.style.top = 0;
			overlay.style.left = 0;
			overlay.style.bottom = 0;
			overlay.style.right = 0;
			overlay.style.backgroundColor = '#000';
			overlay.style.opacity = '0.5';
			return overlay;
		}

		function makeDialog() {
			var dialog = document.createElement('div');
			dialog.style.zIndex = 1001;
			dialog.style.position = 'fixed';
			dialog.style.top = '20pt';
			dialog.style.left = '20pt';
			dialog.style.backgroundColor = 'white';
			dialog.style.border = '4px solid #ccc';
			dialog.style.padding = '1em';
			dialog.style.borderRadius = '5px';
			return dialog;
		}
		
		function makeOnButton(){
		
		}
		
		function makeOffButton(){
		
		}

		function showModuleDialog() {

			// Assemble the dialog.
			var dialog = makeDialog();
			
			dialog.appendChild(makeOnButton());
			dialog.appendChild(makeOffButton());

			// Grey out everything under the dialog.
			var overlay = makeOverlay();

			var body = document.getElementsByTagName('body')[0];
			body.appendChild(overlay);
			body.appendChild(dialog);

			var dismissModuleDialog = function() {
				body.removeChild(dialog);
				body.removeChild(overlay);
				globals.dismissModuleDialog = function() {
				}; // uninstall
			};

			// Clicking outside the module dialog dismisses it.
			overlay.onclick = function() {
				dismissModuleDialog();
			};
		}
		
	showModuleDialog();
	}-*/;

}

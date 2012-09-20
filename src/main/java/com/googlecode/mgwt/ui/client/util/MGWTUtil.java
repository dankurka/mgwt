package com.googlecode.mgwt.ui.client.util;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.Timer;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.OsDetection;

public class MGWTUtil {
	public static void forceFullRepaint(){
		OsDetection d = MGWT.getOsDetection();
		if(d.isAndroid()){
			final String zIndex = Document.get().getBody().getStyle().getZIndex();
			Document.get().getBody().getStyle().setZIndex(-1);
			
			new Timer(){

				@Override
				public void run() {
					Document.get().getBody().getStyle().setProperty("zIndex", zIndex);
					
				}
				
			}.schedule(50);
			
		}
	}
}

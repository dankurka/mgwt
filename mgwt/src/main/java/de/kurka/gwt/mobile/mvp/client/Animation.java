/**
 * 28.12.2010
 * created by kurt
 */
package de.kurka.gwt.mobile.mvp.client;

/**
 * @author kurt
 *
 */
public class Animation {
	public static final String ANIMATION_SLIDE = "slide";
	public static final String ANIMATION_SLIDE_UP = "slideup";
	public static final String ANIMATION_DISSOLVE = "dissolve";
	public static final String ANIMATION_FADE = "fade";
	public static final String ANIMATION_FLIP = "flip";
	public static final String ANIMATION_POP = "pop";
	public static final String ANIMATION_SWAP = "swap";
	public static final String ANIMATION_CUBE = "cube";

	private String type = ANIMATION_SLIDE;
	private boolean direction;

	/**
	 * 
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param animationSlide
	 */
	public void setType(String type) {
		this.type = type;

	}

	/**
	 * @return the direction - true for backwards
	 */
	public boolean isDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(boolean direction) {
		this.direction = direction;
	}
}

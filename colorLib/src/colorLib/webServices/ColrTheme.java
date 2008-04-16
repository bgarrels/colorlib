package colorLib.webServices;

import processing.core.PApplet;
import colorLib.Palette;

/**
 * @author Andreas Kšberle
 * @nosuperclasses
 */
public class ColrTheme extends Palette {

	protected String[] themeTags;
	
	/**
	 * @param i_p
	 */
	protected ColrTheme(final PApplet i_p) {
		super(i_p);
		themeTags = new String[0];
	}
	
	/**
	 * @return String[]: tags for this theme
	 */
	public String[] getThemeTags() {
		return themeTags;
	}

	protected void addThemeTags(final String i_themeTags) {
		if (themeTags != null || !i_themeTags.equalsIgnoreCase("")){
			themeTags=PApplet.concat(themeTags, i_themeTags.split(" "));
	    }
	}
	
}

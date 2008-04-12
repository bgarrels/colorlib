package colorLib.webServices;

import processing.core.PApplet;
import colorLib.Palette;

/**
 * @author Andreas K�berle
 * @nosuperclasses
 */
public class ColrTheme extends Palette {

	protected String[] themeTags;
	
	ColrTheme(final PApplet i_p) {
		super(i_p);
	}
	
	public String[] getThemeTags() {
		return themeTags;
	}

	public void setThemeTags(final String themeTags) {
		if (themeTags != null || !themeTags.equalsIgnoreCase("")){
			this.themeTags = themeTags.split(",");
	    }
	}
	
}

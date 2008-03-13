package colorLib.webServices;

import processing.core.PApplet;
import colorLib.Palette;

public class ColrTheme extends Palette {

	private String[] themeTags;
	
	ColrTheme(PApplet i_p) {
		super(i_p);
	}
	
	public String[] getThemeTags() {
		return themeTags;
	}

	public void setThemeTags(String themeTags) {
		if (themeTags != null || !themeTags.equalsIgnoreCase("")){
			this.themeTags = themeTags.split(",");
	    }
	}
	
}

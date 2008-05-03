package test;

import processing.core.*;
import colorLib.*;
import colorLib.webServices.*;

public class ColrTest extends PApplet {



	public void setup() {
		size(500, 500);
		Colr c =new Colr(this);
//		ColrTheme[] ct = c.searchThemes("tiny");
//		for (int i = 0; i < ct.length; i++) {
//			pushMatrix();
//			translate(20, i*10);
//			ct[i].drawSwatches();
//			popMatrix();
//		}
		ColrTheme ct =c.searchColors("summer");
		String[]tags=ct.getThemeTags();
		for (int i = 0; i < tags.length; i++) {
			println(tags[i]);
		}
	}

	public void draw() {

	}
}

package test;

import colorLib.Palette;
import colorLib.webServices.*;
import processing.core.PApplet;


public class WebServiceTest extends PApplet {
	
	public void  setup() {
		WebService[] services = new WebService[3];
		services[1] = new Kuler(this);
		((Kuler) services[1]).setKey("40A705C90BF2C3F8FB4CA322B29E3026");
		services[2] = new Colr(this);
		services[0] = new ClLovers(this);
		Palette[] p = new Palette[0];
		for (int i = 0; i < services.length; i++) {
			services[i].printXML(true);
			services[i].searchForThemes("candy");
		}
	}
}

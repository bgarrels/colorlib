package test;

import colorLib.webServices.*;
import processing.core.*;

public class ClLoversTest extends PApplet {

	static public void main(String args[]) {
		PApplet.main(new String[] { /* "--present", */"test.ClLoversTest" });
	}

	public void setup() {
		size(1000, 600);
		background(0);
		smooth();
		ClLovers cl = new ClLovers(this);
		cl.setNumResults(50);
		ClLoversTheme[] themes = cl.searchForThemes("spring");
		for (int i = 0; i < themes.length; i++) {
			pushMatrix();
			 translate(10+(i%7)*130 , 10+floor(i/7)* 70);
			 themes[i].drawSwatches();
			 popMatrix();
		}
	}

	public void draw() {

	}
}

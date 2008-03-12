package test;

import kuler.Kuler;
import processing.core.*;
import processing.xml.XMLElement;
import colorLib.*;
import processing.xml.*;
public class Test extends PApplet {
	
	Palette p;
	Kuler k;
	static public void main(String args[]) {
		PApplet.main(new String[] { /* "--present", */"test.Test" });
	}

	public void setup() {
		size(500, 500);
		p=new Palette(this);
		p.makeComplementary(color(6, 131, 189));
		//k = new Kuler(this, "berlin");
		noStroke();
		background(0);

		
		background(0);
	}

	public void draw() {
		p.draw();
	}
	
	public void keyPressed(){
		if(key=='+'){
			p.saturate();
		}else if(key=='-'){
			p.desaturate();
		}
		println(p.getNearestHues());
		saveFrame("german.tif");
	}
}


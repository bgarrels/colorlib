package test;

import processing.core.*;
import processing.xml.XMLElement;
import colorLib.*;
import colorLib.webServices.Kuler;
import processing.xml.*;
public class Test extends PApplet {
	
	Palette p;
	Kuler k;
	static public void main(String args[]) {
		PApplet.main(new String[] { /* "--present", */"test.Test" });
	}

	public void setup() {
		size(500, 500);
		noStroke();
//		p=new Palette(this);
//		p.makeComplementary(color(6, 131, 189));
		k = new Kuler(this);
		k.getRandom();
		k.draw();
	}

	public void draw() {
		
	}
//	
//	public void keyPressed(){
//		if(key=='+'){
//			p.saturate();
//		}else if(key=='-'){
//			p.desaturate();
//		}
//		println(p.getNearestHues());
//		saveFrame("german.tif");
//	}
}


package test;

import processing.core.*;
import colorLib.*;
import colorLib.webServices.*;

public class Test extends PApplet {
	
	Palette p;
	Kuler k;
	Colr c;
	static public void main(String args[]) {
		PApplet.main(new String[] { /* "--present", */"test.Test" });
	}

	public void setup() {
		size(1400, 490);
		noStroke();
		background(100);
//		p=new Palette(this);
//		p.makeComplementary(color(6, 131, 189));
//		c = new Colr(this);
		k = new Kuler(this);
		k.search("coffee","tag");
		k.draw();
	}

	public void draw() {
		
	}
//	
	public void keyPressed(){
//		if(key=='+'){
//			p.saturate();
//		}else if(key=='-'){
//			p.desaturate();
//		}
//		println(p.getNearestHues());
		saveFrame("german.tif");
	}
}


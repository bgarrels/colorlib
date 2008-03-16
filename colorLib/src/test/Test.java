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
		size(1300, 490);
		noStroke();
		background(100);
//		p=new Palette(this);
//		p.makeComplementary(color(6, 131, 189));
//		c = new Colr(this);
//		smooth();
//		beginRecord(PDF, "output.pdf");
//		pushMatrix();
//		Kuler good = new Kuler(this);
//		good.search("good","tag");
//		translate(0, 0);
//		good .draw();
//		popMatrix();
//		pushMatrix();
//		Kuler bad = new Kuler(this);
//		translate(430, 0);
//		bad.search("bad","tag");
//		bad .draw();
//		popMatrix();
//		pushMatrix();
//		Kuler ugly = new Kuler(this);
//		translate(860, 0);
//		ugly.search("ugly","tag");
//		ugly .draw();
//		popMatrix();
//		endRecord();
		p=new Palette(this);
		p.makeComplementary(color(6, 131, 189));
		p.drawSwatches();
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


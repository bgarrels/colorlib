package test;

import colorLib.Palette;
import processing.core.*;


public class MedianCutTest extends PApplet {

	static public void main(String args[]) {
		PApplet.main(new String[] { /* "--present", */"test.MedianCutTest" });
	}

	public void setup() {
		size(500, 500);
		int[] colors = new int[25];
		for (int i = 0; i < 25; i++) {
			if(i<10){
				colors[i]=color(200+i*5, 0, 0);
			}else if (i<20){
				colors[i]=color(0, 0, 200+(i%10)*5);
			}else{
				colors[i]=color(0, 200+(i%10)*5, 0);
			}
		}

		Palette p=new Palette(this, loadImage("test32.gif"),10);
		translate(width/4, height/4);
		p.drawWheel();
		p=new Palette(this, colors);
		translate(width/2, height/4);
		p.drawWheel();
		
		
	}

	public void draw() {

	}
}

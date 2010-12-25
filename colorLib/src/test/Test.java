package test;

import processing.core.*;
import colorLib.*;
import colorLib.webServices.*;

public class Test extends PApplet
{
	
	Palette p;
	Kuler   k;
	Colr    c;
	Swatch  s;
	
	static public void main(String args[])
	{
		PApplet.main(new String[] { /* "--present", */"test.Test" });
	}

	public void setup()
	{
		size(400, 400);
		noStroke();
		smooth();
		
		p = new Palette(this);
		p.addColor( color(255, 0, 0) );
		p.addColor( color(0, 255, 0) );
		p.addColor( color(0, 0, 255) );
		p.addColor( color(255, 255, 0) );
		p.addColor( color(255, 0, 255) );
		p.addColor( color(0, 255, 255) );
		
	}
	
	public void draw()
	{
		background(255);
		p.drawSwatches();
		
	}

}
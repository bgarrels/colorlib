package test;

import processing.core.*;
import colorLib.*;
import colorLib.webServices.*;

public class Test extends PApplet {
	
	Palette p;
	Kuler   k;
	Colr    c;
	SwatchXXX  s;
	
	static public void main(String args[]) {
		PApplet.main(new String[] { /* "--present", */"test.Test" });
	}

	public void setup() {
		size(400, 400);
		noStroke();
		smooth();
		
		// red
		s = new SwatchXXX(this, color(255, 0, 0));
		fill(s.c);
		rect(0, 0, width/4, height/4);
		
		println("RGB RED");
		println("-----------------------");
		println("Black:   " + s.black());
		println("Yellow:  " + s.yellow());
		println("Magenta: " + s.magenta());
		println("Cyan:    " + s.cyan());
		println(" ");

		// green
		s = new SwatchXXX(this, color(0, 255, 0));
		fill(s.c);
		rect(width/4, 0, width/4, height/4);
		
		println("RGB GREEN");
		println("-----------------------");
		println("Black:   " + s.black());
		println("Yellow:  " + s.yellow());
		println("Magenta: " + s.magenta());
		println("Cyan:    " + s.cyan());
		println(" ");

		// blue
		s = new SwatchXXX(this, color(0, 0, 255));
		fill(s.c);
		rect(width/2, 0, width/4, height/4);
		
		println("RGB BLUE");
		println("-----------------------");
		println("Black:   " + s.black());
		println("Yellow:  " + s.yellow());
		println("Magenta: " + s.magenta());
		println("Cyan:    " + s.cyan());		
		println(" ");

		// blue
		s = new SwatchXXX(this, color(255, 255, 255));
		fill(s.c);
		rect((float) (width*0.75), 0, width/4, height/4);

		println("RGB WHITE");
		println("-----------------------");
		println("Black:   " + s.black());
		println("Yellow:  " + s.yellow());
		println("Magenta: " + s.magenta());
		println("Cyan:    " + s.cyan());		
		println(" ");

		// cyan
		s = new SwatchXXX(this, color(0, 255, 255));
		fill(s.c);
		rect(0, height/4, width/4, height/4);
		
		println("RGB RED");
		println("-----------------------");
		println("Black:   " + s.black());
		println("Yellow:  " + s.yellow());
		println("Magenta: " + s.magenta());
		println("Cyan:    " + s.cyan());
		println(" ");

		// magenta
		s = new SwatchXXX(this, color(255, 0, 255));
		fill(s.c);
		rect(width/4, height/4, width/4, height/4);
		
		println("RGB MAGENTA");
		println("-----------------------");
		println("Black:   " + s.black());
		println("Yellow:  " + s.yellow());
		println("Magenta: " + s.magenta());
		println("Cyan:    " + s.cyan());
		println(" ");

		// yellow
		s = new SwatchXXX(this, color(255, 255, 0));
		fill(s.c);
		rect(width/2, height/4, width/4, height/4);
		
		println("RGB YELLOW");
		println("-----------------------");
		println("Black:   " + s.black());
		println("Yellow:  " + s.yellow());
		println("Magenta: " + s.magenta());
		println("Cyan:    " + s.cyan());		
		println(" ");

		// black
		s = new SwatchXXX(this, color(0, 0, 0));
		fill(s.c);
		rect((float) (height*0.75), width/4, width/4, height/4);

		println("RGB BLACK");
		println("-----------------------");
		println("Black:   " + s.black());
		println("Yellow:  " + s.yellow());
		println("Magenta: " + s.magenta());
		println("Cyan:    " + s.cyan());		
		println(" ");
		
		
	}

}
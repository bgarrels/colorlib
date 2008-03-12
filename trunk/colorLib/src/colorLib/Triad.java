package colorLib;

import processing.core.*;

public class Triad extends Palette {

	public Triad(int i_color, PApplet i_p) {
		this(i_color, 1, i_p);
	}

	public Triad(int i_color, int i,  PApplet i_p) {
		super(i_p, 3);
		int colorMode = p.g.colorMode;
		p.colorMode(3);
		float h = p.hue(i_color);
		float s = p.saturation(i_color);
		float b = p.brightness(i_color);
		float degree = 255 / 3 * i;
		colors[0] =  i_color;
		colors[1] = p.color((h + degree) % 255, s, b);
		colors[2] = p.color((h - degree) % 255, s, b);
		p.colorMode(colorMode);
	}

}

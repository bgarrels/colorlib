package colorLib;

import processing.core.*;

/**
 * @author Andreas Kšberle
 * @author Jan Vantomme
 *
 */
public class Swatch
{

	/**
	 * holds a reference to the parent PApplet.
	 */
	protected PApplet p;
	
	/**
	 * color instance ...
	 */
	public int c;


	
	/**
	 * Creates a swatch object with a random color. This swatch object holds all information on
	 * a color. All color transformations are done on this object.
	 * @param i_p PApplet object, normally you will use 'this'.
	 */
	public Swatch(PApplet i_p){
		p = i_p;
		c = p.g.color(p.random(p.g.colorModeX), p.random(p.g.colorModeY), p.random(p.g.colorModeZ));
	}

	/**
	 * Creates a swatch object with a given color. This swatch object holds all information on
	 * a color. All color transformations are done on this object.
	 * @param i_p PApplet object, normally you will use 'this'.
	 */	
	public Swatch(PApplet i_p, int color){
		p = i_p;
		c = color;
	}
	
	/**
	 * Gets the black value for the color of this swatch.
	 * Based on the generic RGB to CMYK formula: black = minimum(1 - red, 1 - green, 1 - blue)
	 * @return
	 * @related cyan()
	 * @related magenta()
	 * @related yellow()
	 */
	public float black(){
		return PApplet.min(1 - ((c >> 16) & 0xFF) / 255.0f, 1 - ((c >> 8) & 0xFF) / 255.0f, 1 - ((c) & 0xFF) / 255.0f);
	}

	/**
	 * Gets the cyan value for a given RGB color.
	 * Based on the generic RGB to CMYK formula: cyan = (1 - red - black) / (1 - black)
	 * @related black()
	 * @related magenta()
	 * @related yellow()
	 */
	public float cyan()
	{
	    return (1 - ((c >> 16) & 0xFF) / 255.0f - black()) / (1 - black());
	}
	
	/**
	 * Gets the magenta value for a given RGB color.
	 * Based on the generic RGB to CMYK formula: magenta = (1 - green - black) / (1 - black)
	 * @return
	 * @related black()
	 * @related cyan()
	 * @related yellow()
	 */
	public float magenta(){
	    return (1 - ((c >> 8) & 0xFF) / 255.0f - black()) / (1 - black());
	}
	
	/**
	 * Gets the yellow value for a given RGB color.
	 * Based on the generic RGB to CMYK formula: yellow = (1 - blue - black) / (1 - black)
	 * @return
	 * @related black()
	 * @related cyan()
	 * @related magenta()
	 */
	public float yellow(){
		return (1 - ((c) & 0xFF) / 255.0f - black()) / (1 - black());
	}
	
	public void darken() {
		darken(10);
	}

	public void darken(float i_step) {
		lighten(-i_step);
	}

	public void lighten() {
		lighten(10);
	}

	public void lighten(float i_step) {
		c = setHSBColor(p.hue(c), p.saturation(c), p
				.brightness(c)
				+ i_step, p.alpha(c));
	}

	public void desaturate() {
		saturate(-10);
	}

	public void desaturate(int i_step) {
		saturate(-i_step);
	}

	public void saturate() {
		saturate(10);
	}

	public void saturate(int i_step) {
		c = setHSBColor(p.hue(c), p.saturation(c) + i_step, p
				.brightness(c));
	}

	private int setHSBColor(float h, float s, float b, float a) {
		int color;
		int colorMode = p.g.colorMode;
		p.colorMode(PApplet.HSB);
		color = p.color(h, s, b, a);
		p.colorMode(colorMode);
		return color;
	}

	private int setHSBColor(float h, float s, float b) {
		return setHSBColor(h, s, b, 255);
	}

	public int getColor() {
		return c;
	}
	
	public void  setColor(int i_c){
		c=i_c;
	}
	
	public void  rotateRGB(float i_angle) {
		c =  setHSBColor((p.hue(c) + i_angle) % 256, p.saturation(c), p.brightness(c));
	}
	
	public void rotateRYB(float i_angle){
		 float hue = ((p.hue(c))/256f)*360;
	     i_angle %= 360;
	     float angle = 0;
	     int [][]  wheel = {
	            {  0,   0}, { 15,   8},
	            { 30,  17}, { 45,  26},
	            { 60,  34}, { 75,  41},
	            { 90,  48}, {105,  54},
	            {120,  60}, {135,  81},
	            {150, 103}, {165, 123},
	            {180, 138}, {195, 155},
	            {210, 171}, {225, 187},
	            {240, 204}, {255, 219},
	            {270, 234}, {285, 251},
	            {300, 267}, {315, 282},
	            {330, 298}, {345, 329},
	            {360, 0  }
	     };

	        for (int i =0; i<wheel.length-1; i++){
	            int x0 = wheel[i][0] ; 
	            int y0 = wheel[i][1];
	            int x1 = wheel[i+1][0] ; 
	            int y1 = wheel[i+1][1];
	            if (y1 < y0){
	                y1 += 360;
	            }
	            if (y0 <= hue && hue <= y1){
	            	angle = 1f * x0 + (x1-x0) * (hue-y0) / (y1-y0);
	                break;
	            }
	        }
	        System.out.println("hue: "+hue+"angle: "+angle);
	        angle = (angle+i_angle) % 360;

	        for (int i =0; i<wheel.length-1; i++){
	        	int x0 = wheel[i][0] ; 
	            int y0 = wheel[i][1];
	            int x1 = wheel[i+1][0] ; 
	            int y1 = wheel[i+1][1];
	            if (y1 < y0)
	                y1 += 360;
	            if ( x0 <= angle && angle <= x1){
	                hue = 1f * y0 + (y1-y0) * (angle-x0) / (x1-x0);
	                break;
	            }
	        }
	    
	        hue %= 360;
	        c =  setHSBColor(hue*256/360, p.saturation(c), p.brightness(c), p.alpha(c));
	 }

}
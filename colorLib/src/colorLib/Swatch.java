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
	 * color variable of the Swatch object.
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
	 * @return  the black value of the given color, a number between 0 and 1.
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
	 * @return  the cyan value of the given color, a number between 0 and 1.
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
	 * @return  the magenta value of the given color, a number between 0 and 1.
	 * @related black()
	 * @related cyan()
	 * @related yellow()
	 */
	public float magenta()
	{
	    return (1 - ((c >> 8) & 0xFF) / 255.0f - black()) / (1 - black());
	}
	
	/**
	 * Gets the yellow value for a given RGB color.
	 * Based on the generic RGB to CMYK formula: yellow = (1 - blue - black) / (1 - black)
	 * @return  the yellow value of the given color, a number between 0 and 1.
	 * @related black()
	 * @related cyan()
	 * @related magenta()
	 */
	public float yellow(){
		return (1 - ((c) & 0xFF) / 255.0f - black()) / (1 - black());
	}
	
	/**
	 * Darkens the color by 10 steps
	 * @related lighten()
	 */
	public void darken() {
		darken(10);
	}

	/**
	 * Darkens the color by a given number
	 * @related lighten()
	 * @param i_step 
	 */
	public void darken(float i_step) {
		lighten(-i_step);
	}

	/**
	 * Lightens the color by 10 steps
	 * @related darken()
	 */
	public void lighten() {
		lighten(10);
	}

	/**
	 * Lightens the color by a given number
	 * @related darken()
	 * @param i_step 
	 */
	public void lighten(float i_step) {
		c = setHSBColor(p.hue(c), p.saturation(c), p
				.brightness(c)
				+ i_step, p.alpha(c));
	}

	/**
	 * Desaturates the color by 10 steps
	 * @related saturate()
	 */
	public void desaturate() {
		saturate(-10);
	}

	/**
	 * Desaturates the color by a given number
	 * @related saturate()
	 * @param i_step 
	 */
	public void desaturate(int i_step) {
		saturate(-i_step);
	}

	/**
	 * Saturates the color by 10 steps
	 * @related desaturate()
	 */
	public void saturate() {
		saturate(10);
	}

	/**
	 * Saturates the color by a given number
	 * @related desaturate()
	 * @param i_step 
	 */
	public void saturate(int i_step) {
		c = setHSBColor(p.hue(c), p.saturation(c) + i_step, p
				.brightness(c));
	}

	/**
	 * Sets a new color based on HSB values.
	 * @param h hue value for the color that will be set by the function
	 * @param s saturation value for the color that will be set by the function
	 * @param b brightness value for the color that will be set by the function
	 * @param a alpha value for the color that will be set by the function
	 * @return a new color
	 */
	private int setHSBColor(float h, float s, float b, float a) {
		int color;
		int colorMode = p.g.colorMode;
		p.colorMode(PApplet.HSB);
		color = p.color(h, s, b, a);
		p.colorMode(colorMode);
		return color;
	}

	/**
	 * Sets a new color based on HSB values.
	 * @param h hue value for the color that will be set by the function
	 * @param s saturation value for the color that will be set by the function
	 * @param b brightness value for the color that will be set by the function
	 * @return a new color
	 */
	private int setHSBColor(float h, float s, float b) {
		return setHSBColor(h, s, b, 255);
	}

	/**
	 * Returns the color of the swatch.
	 * @return
	 */
	public int getColor() {
		return c;
	}

	/**
	 * Sets the color of the swatch
	 * @param i_c A color passed from processing.
	 */
	public void setColor(int i_c){
		c=i_c;
	}

	/**
	 * Rotates a color around the RGB (Red-Green-Blue) color wheel.
	 * @param i_angle
	 */
	public void rotateRGB(float i_angle) {
		c =  setHSBColor((p.hue(c) + i_angle) % 256, p.saturation(c), p.brightness(c));
	}
	
	/**
	 * Rotates a color around the RYB (Red-Yellow-Blue) color wheel.
	 * More information about the RYB color wheel can be found at <a href="http://en.wikipedia.org/wiki/RYB_color_model">Wikipedia</a>.
	 * @param i_angle
	 */
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
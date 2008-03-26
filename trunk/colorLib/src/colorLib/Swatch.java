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
	public Swatch(PApplet i_p)
	{
		p = i_p;
		c = p.g.color(p.random(p.g.colorModeX), p.random(p.g.colorModeY), p.random(p.g.colorModeZ));
	}

	/**
	 * Creates a swatch object with a given color. This swatch object holds all information on
	 * a color. All color transformations are done on this object.
	 * @param i_p PApplet object, normally you will use 'this'.
	 */	
	public Swatch(PApplet i_p, int color)
	{
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
	public float black()
	{
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
	public float magenta()
	{
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
	public float yellow()
	{
		return (1 - ((c) & 0xFF) / 255.0f - black()) / (1 - black());
	}

}
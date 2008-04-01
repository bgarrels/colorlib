package colorLib;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * @author andreaskoberle
 *
 */
public class Palette {

	private static final long serialVersionUID = 1L;

	protected Swatch[] swatches;

	protected PApplet p;
	
	PImage dropShadow;

	/**
	 * Creates a new palette object which holds your colors. 
	 * All color manipulation is done over this object.
	 * By default it creates an empty palette with a placeholder for 10 colors. 
	 * You can also pass an array of colors or an PImage object to create a new palette.
	 * If you pass a PImage, all duplicate colors will be deleted.
	 * At least you use a given <a href="www.colorschemer.com">color shemer</a> .cs file or 
	 * an .act file which you can create in Photoshop for example.
	 * @param i_p a PApplet object, normally use 'this'
	 */
	public Palette(PApplet i_p) {
		p=i_p;
		swatches=new Swatch[0];
	}

	/**
	 * @param i_length length of the created 
	 */
	public Palette(PApplet i_p, int i_length) {
		this(i_p, new int[i_length]);
	}

	/**
	 * @param i_colors int[],an array of colors
	 */
	public Palette(PApplet i_p, int[] i_colors) {
		p=i_p;
		swatches=new Swatch[i_colors.length];
		System.arraycopy(i_colors,0 ,swatches, 0, i_colors.length);
	}

	/**
	 * @param i_image PImage, 
	 */
	public Palette(PApplet i_p, PImage i_image) {
		p = i_p;
		swatches = new Swatch[i_image.pixels.length];
		for (int i = 0; i < swatches.length; i++) {
			swatches[i] = new Swatch(p, i_image.pixels[i]);
		}
		deletedDuplicate();
	}

	/**
	 * @param i_filename String, an .cs or .act file located in the data folder of your sketch
	 */
	public Palette(PApplet i_p, String i_filename) {
		p=i_p;
		byte[] b = p.loadBytes(i_filename);
		
		if (i_filename.endsWith(".cs")) {
			createPaletteFromFile(b, 8, 26, b[2] & 0xff);
		} else if (i_filename.endsWith(".act")) {
			createPaletteFromFile(b, 0, 3, 255);
		}else {
			throw new IllegalArgumentException( "Only .cs and .act files are supported" ); 
		}
	}
	
	private void createPaletteFromFile(byte[] b, int start, int steps, int length) {
		int cnt = 0;
		for (int i = 0; i < length; i++) {
			if (b[start  + i * steps] != -1 && b[start  + i * steps +1] != -1 && b[start  + i * steps+2] != -1){
				cnt++;
			}
		}
		swatches=new Swatch[cnt];
		cnt = 0;
		for (int i = 0; i < length; i++) {
			if (b[start  + i * steps] != -1 && b[start  + i * steps +1] != -1 && b[start  + i * steps+2] != -1){
				swatches[cnt] = new Swatch(p,  (0xff<<24)
							+ ((b[start  + i * steps] & 0xff) << 16)
							+ ((b[start + i * steps+1] & 0xff) << 8)
							+ (b[start + i * steps+2] & 0xff));
				cnt++;	
			}
		}
		
	}
	
	/**
	 * Creates a 2 color palette with the passed color and it's complementary on the <a href="http://en.wikipedia.org/wiki/RYB">RYB</a> color wheel.
	 * @param i_color
	 * @related makeComplementary ()
	 * @related makeSplittedComplementary ()
	 * @related makeTriad ()
	 * @related makeTetrad ()
	 */
	public void makeComplement(final int i_color){
		swatches=new Swatch[2];
		swatches[0]= new Swatch(p,i_color);
		swatches[1]=new Swatch(p, i_color);
		swatches[1].rotateRYB(180);
	}
	
	/**
	 * @param i_color
	 * @related makeComplement ()
	 * @related makeSplittedComplementary ()
	 * @related makeTriad ()
	 * @related makeTetrad ()
	 */
	public void makeComplementary(int i_color){
		swatches=new Swatch[6];
		swatches[0]= new Swatch(p,i_color);
		float brightness=0;
		if (p.brightness(i_color) >102){
			brightness = 25 + p.brightness(i_color)*0.25f;
		}else{
			brightness = 255 - p.brightness(i_color)*0.25f;
		}
		swatches[1]=new Swatch(p,setHSBColor(p.hue(i_color), p.saturation(i_color), brightness));
		swatches[2]=new Swatch(p,setHSBColor(p.hue(i_color), 25+p.saturation(i_color)*.3f, p.brightness(i_color)+76.5f));
		swatches[3]=new Swatch(p, i_color);
		swatches[3].rotateRYB(180);
		i_color = swatches[3].getColor();
		if (p.brightness(i_color) >102){
			brightness = 25 + p.brightness(i_color)*0.25f;
		}else{
			brightness = 255 - p.brightness(i_color)*0.25f;
		}
		swatches[4]= new Swatch(p,setHSBColor(p.hue(i_color), p.saturation(i_color), brightness));
		swatches[5]= new Swatch(p,setHSBColor(p.hue(i_color), 25+p.saturation(i_color)*.25f, p.brightness(i_color)+76.5f));
	}
	
	/**
	 * @param i_color
	 * @related makeComplement ()
	 * @related makeComplementary ()
	 * @related makeTriad ()
	 * @related makeTetrad ()
	 */
	public void makeSplittedComplementary(int i_color){
		swatches=new Swatch[3];
		swatches[0]=new Swatch(p,i_color);
		swatches[1]=new Swatch(p,i_color);
		swatches[1].rotateRYB(30);
		swatches[1].lighten(25.5f);
		swatches[2]=new Swatch(p,i_color);
		swatches[2].rotateRYB(-30);
		swatches[2].lighten(25.5f);
	}
	
	/**
	 * @param i_color
	 * @param i_angle
	 * @related makeComplement ()
	 * @related makeComplementary ()
	 * @related makeSplittedComplementary ()
	 * @related makeTetrad ()
	 */
	public void makeTriad(int i_color, int i_angle){
		swatches = new Swatch[3];
		swatches[0] = new Swatch(p,i_color);
		swatches[1] = new Swatch(p,i_color);
		swatches[1].rotateRYB(i_angle);
		swatches[2] = new Swatch(p,i_color);
		swatches[2].rotateRYB(-i_angle);
	}
	
	/**
	 * @param i_color
	 */
	public void makeTriad(int i_color){
		makeTriad(i_color, 120);
	}
	
	/**
	 * @param i_color
	 * @related makeComplement ()
	 * @related makeComplementary ()
	 * @related makeSplittedComplementary ()
	 * @related makeTriad ()
	 */
	public void makeTetrad(int i_color){
		swatches = new Swatch[4];
		swatches[0] = new Swatch(p,i_color);
		for (int i = 1; i < 4; i++) {
			swatches[i] = new Swatch(p,i_color);
			swatches[i].rotateRYB(90*i);
		}
	}
	
	/**
	 * Decrease the brighness of all colors in the palette by the passed value. By default its 10.
	 * @related saturate ()
	 * @related lighten ()
	 * @related desaturate ()
	 */
	public void darken() {
		lighten(-10);
	}

	/**
	 * @param i_step
	 */
	public void darken(int i_step) {
			lighten(-i_step);
	}
	
	/**
	 * Increase the brighness of all colors in the palette by the passed value. By default its 10.
	 * @related saturate ()
	 * @related darken ()
	 * @related desaturate ()
	 */
	public void lighten() {
		lighten(10);
	}

	/**
	 * @param i_step
	 */
	public void lighten(int i_step) {
		for (int i = 0; i < swatches.length; i++) {
			swatches[i].lighten(i_step);
		}
	}

	/**
	 * Increase the saturation of all colors in the palette by the passed value. By default its 10.
	 */
	public void saturate() {
		saturate(10);
	}

	/**
	 * @param i_step
	 * @related lighten ()
	 * @related darken ()
	 * @related desaturate ()
	 */
	public void saturate(int i_step) {
		for (int i = 0; i < swatches.length; i++) {
			swatches[i].saturate( i_step);
		}
	}

	/**
	 * Increase the saturation of all colors in the palette by the passed value. By default its 10.
	 * @related saturate ()
	 * @related lighten ()
	 * @related darken ()
	 */
	public void desaturate() {
		saturate(-10);
	}

	/**
	 * @param i_step
	 */
	public void desaturate(int i_step) {
		saturate(-i_step);
	}

	/**
	 * Rotates all colors by the passed angle on the RGB color wheel.
	 * @param i_angle
	 * @related rotateRYB ()
	 */
	public void rotateRGB(int i_angle) {
		for (int i = 0; i < swatches.length; i++) {
			swatches[i].rotateRGB(i_angle);
		}
	}
	


	/**
	 * Rotates all colors by the passed angle on the <a href="http://en.wikipedia.org/wiki/RYB">RYB</a> color wheel.
	 * @param i_angle
	 * @related rotateRGB () 
	 */
	public void  rotateRYB(float i_angle){
		for (int i = 0; i < swatches.length; i++) {
			swatches[i].rotateRYB(i_angle);
		}
	}

	public void sortByHue() {

	}

	/**
	 * sorts the  palette by saturation with element at last index
	 * containing the most saturated item of the palette
	 * @related sortByLuminance ()
	 * @related sortByProximity ()
	 */
	public void sortBySaturation() {
		int[] sorted = new int[swatches.length];
		Hashtable ht = new Hashtable();
		for (int i = 0; i < swatches.length; i++) {
			int c = swatches[i].getColor();
			int r = (c >> 16) & 0xff;
			int g = (c >> 8) & 0xff;
			int b = c & 0xff;
			int maxComp = PApplet.max(r, g, b);
			if (maxComp > 0) {
				sorted[i] = (int) ((maxComp - PApplet.min(r, g, b)) / (float) maxComp * 0x7fffffff);
			} else {
				sorted[i] = 0;
			}
			ht.put(new Integer(sorted[i]), swatches[i]);
		}
		sorted = PApplet.sort(sorted);
		for (int i = 0; i < sorted.length; i++) {
			swatches[i]=(Swatch) ht.get(new Integer(sorted[i]));
		}
	}

	/**
	 * Sorts the palette by luminance with element at last index
	 * containing the "brightest" item of the palette
	 * @related sortBySaturation () 
	 * @related sortByProximity ()
	 */
	public void sortByLuminance() {
		int[] sorted = new int[swatches.length];
		Hashtable ht = new Hashtable();
		for (int i = 0; i < swatches.length; i++) {
			int c = swatches[i].getColor();
			sorted[i] = (77 * (c >> 16 & 0xff) + 151
					* (c >> 8 & 0xff) + 28 * (c & 0xff));
			ht.put(new Integer(sorted[i]), swatches[i]);
		}
		sorted = PApplet.sort(sorted);
		for (int i = 0; i < sorted.length; i++) {
			 swatches[i] =  (Swatch) ht.get(new Integer(sorted[i]));
		}

	}

	/**
	 * Sorts the palette by proximity to a colour with element at
	 * first index containing the "closest" item of the palette
	 * 
	 * @param i_color
	 * @related sortByLuminance ()
	 * @related sortBySaturation () 
	 */

	public void sortByProximity(int i_color) {
		int[] sorted = new int[swatches.length];
		Hashtable ht = new Hashtable();
		int br = (i_color >> 16) & 0xff;
		int bg = (i_color >> 8) & 0xff;
		int bb = i_color & 0xff;
		for (int i = 0; i < swatches.length; i++) {
			int c = swatches[i].getColor();
			int r = (c >> 16) & 0xff;
			int g = (c >> 8) & 0xff;
			int b = c & 0xff;
			sorted[i] = (br - r) * (br - r) + (bg - g) * (bg - g) + (bb - b)
					* (bb - b);
			ht.put(new Integer(sorted[i]), (swatches[i]));
		}
		sorted = PApplet.sort(sorted);
		for (int i = 0; i < sorted.length; i++) {
			swatches[i] = ((Swatch) ht.get(new Integer(sorted[i])));
		}

	}

	/**
	 * Interpolates all colors of the palette with passed color.
	 * @param i_color
	 * @param distance
	 */
	public void interpolate(int i_color, float distance) {
		distance = PApplet.constrain(distance, 0, 1);
		for (int i = 0; i < swatches.length; i++) {
			int c = swatches[i].getColor();
			int a1 = (c >> 24) & 0xff;
			int r1 = (c >> 16) & 0xff;
			int g1 = (c >> 8) & 0xff;
			int b1 = c & 0xff;

			int a2 = (i_color >> 24) & 0xff;
			int r2 = (i_color >> 16) & 0xff;
			int g2 = (i_color >> 8) & 0xff;
			int b2 = i_color & 0xff;

			swatches[i].setColor( ((int) (a1 * (1f - distance) + a2 * distance) << 24)
					| ((int) (r1 * (1f - distance) + r2 * distance) << 16)
					| ((int) (g1 * (1f - distance) + g2 * distance) << 8)
					| (int) (b1 * (1f - distance) + b2 * distance));
		}
	}
	/**
	 * Returns the darkest color of the palette
	 * @return color
	 */
	public int getDarkest() {
		int darkest = swatches[0].getColor();
		float brightness  = p.brightness(darkest);
		for (int i = 1; i < swatches.length; i++) {
			int c = swatches[i].getColor();
			if (brightness > p.brightness(c)) {
				darkest = c;
				brightness  = p.brightness(darkest);
			}
		}
		return darkest;
	}

	/**
	 * Returns the lightest color of the palette
	 * @return color
	 */
	public int getLightest() {
		int lightest = swatches[0].getColor();
		float brightness  = p.brightness(lightest);
		for (int i = 1; i < swatches.length; i++) {
			int c = swatches[i].getColor();
			if (brightness < p.brightness(c)) {
				lightest =c;
				brightness = p.brightness(lightest);
			}
		}
		return lightest;
	}

	/**
	 * Returns the average color of the palette
	 * @return color
	 */
	public int getAverage() {
		int a = 0;
		int r = 0;
		int g = 0;
		int b = 0;
		for (int i = 0; i < swatches.length; i++) {
			int c = swatches[i].getColor();
			a += c >> 24 & 0xff;
			r += c >> 16 & 0xff;
			g += c >> 8 & 0xff;
			b += c & 0xff;
		}
		return ((int) (a / swatches.length) << 24)
				| ((int) (r / swatches.length) << 16)
				| ((int) (g / swatches.length) << 8) 
				| (int) (b / swatches.length);
	}

	/**
	 * Returns the swatch at the given position in the color array of the palette.
	 * @param position
	 * @return color
	 */
	public Swatch getSwatch(int position) {
		return swatches[position];
	}
	
	/**
	 * Returns the color at the given position in the color array of the palette.
	 * @param position
	 * @return color
	 */
	public int getColor(int position) {
		return swatches[position].getColor();
	}

	/**
	 * Returns an array holding all the swatches of the palette.
	 * @return color Array
	 */
	public Swatch[] getSwatches() {
		Swatch[] i_colors = new Swatch[swatches.length];
		System.arraycopy(swatches, 0, i_colors, 0, swatches.length);
		return i_colors;
	}
	
	/**
	 * Returns an array holding all the color of the palette.
	 * @return color Array
	 */
	public int[] getColors() {
		int[] i_colors = new int[swatches.length];
		for (int i = 0; i < i_colors.length; i++) {
			i_colors[i]=swatches[i].getColor();
		}
		return i_colors;
	}

	/**
	 * Returns an array with the nearest hue to the palettes color as an array of the following strings:
	 * "red", "orange", "yellow", "lime", "green","teal", "cyan", "azure", "blue", "indigo", "purple", "pink"
	 * @return String Array
	 */
	public String[] getNearestHues(){
		String[] nearestHues = new String[swatches.length];
		for (int i = 0; i < swatches.length; i++) {
			nearestHues[i]=getNearestHue(swatches[i].getColor());
		}
		return nearestHues;
	}

	private String getNearestHue(int i_color) {
		String[] hueNames = { "red", "orange", "yellow", "lime", "green","teal", "cyan", "azure", "blue", "indigo", "purple", "pink" };
		
		int r = (i_color >> 16) & 0xff;
		int g = (i_color >> 8) & 0xff;
		int b = i_color & 0xff;
		if(r==b && b==g){
			if(r==0){
				return "black";
			}else if(r==255){
				return "white";
			}else {
				return "grey";
			}
		}
		
		String nearest = "";
		float d = 256;
		float hue = p.hue(i_color);
		for (int i = 0; i < hueNames.length; i++) {
			float i_d = Math.abs(hue - 255 / 12 * i);
			if (i_d < d) {
				nearest = hueNames[i];
				d = i_d;
			} else {
				break;
			}
		}
		return nearest;
	}

	/**
	 * Save the palette as an or .act file in your sketch folder
	 * @param i_name
	 */
	public void save(String i_name) {

	}

	/**
	 * Adds one or more colors at the end of your palette.
	 * @param i_color
	 */
	public void addColor(int i_color) {
		swatches = (Swatch[]) PApplet.append(swatches, new Swatch(p,i_color));
	}

	/**
	 * @param i_colors
	 */
	public void addColors(int[] i_colors) {
		for (int i = 0; i < i_colors.length; i++) {
			addColor(i_colors[i]);
		}
	}

	public void addColors(String[] i_colorNames) {

	}
	
	/**
	 * Sets the color on the passed position on palette. Or 
	 * @param i_color
	 * @param i_position
	 */
	public void setColor(int i_color, int i_position) {
		
	}

	public void setColor(String i_colorName) {

	}

	/**
	 * @param i_colors
	 */
	public void setColors(int[] i_colors) {
		swatches = new Swatch[i_colors.length];
		for (int i = 0; i < i_colors.length; i++) {
			addColor(i_colors[i]);
		}
	}

	public void setColors(String[] i_colorNames) {
			
	}
	
	/**
	 * Delete all duplicate colors on the palette.
	 */
	public void deletedDuplicate(){
		HashSet h = new HashSet();
		for (int i = 0; i < swatches.length; i++) {
			h.add(new Integer(swatches[i].getColor()));
		}
		swatches = new Swatch[h.size()];
		Iterator iter = h.iterator();
		int cnt=0;
		while (iter.hasNext()) {
			swatches[cnt] = new Swatch(p, ((Integer) iter.next()).intValue());
			cnt++;
		}
	}
	
	/**
	 * Draw all colors
	 */
	public void drawSwatches(){
		float paletteLength = 120f;
		int paletteHeight = 38;
		if(dropShadow==null){
			dropShadow = p.loadImage("dropshadow.png");
		}
		p.image(dropShadow,-3, -2);
		float swatchLength  = paletteLength/swatches.length;
		for (int i = 0; i < swatches.length; i++) {
			p.fill(swatches[i].getColor());
			p.noStroke();
			p.quad(swatchLength*i, 0, swatchLength*(i+1), 0, swatchLength*(i+1), paletteHeight, swatchLength*i, paletteHeight);
			p.line(swatchLength*i, 0,swatchLength*i, paletteHeight);
			for (int j =3, k=0; j > -1; j--, k++) {
				int c = swatches[i].getColor();
				p.stroke(setHSBColor(p.hue(c), p.saturation(c), p
						.brightness(c)+ 10*k));
				
				p.line(swatchLength*i,j,swatchLength*(i+1)-1,j);
				
				p.stroke(setHSBColor(p.hue(c), p.saturation(c), p
						.brightness(c)- 10*j));
				
				p.line(swatchLength*i,paletteHeight-k,swatchLength*(i+1)-1,paletteHeight-k);
			}

			
			}
	}
	
	/**
	 * Draws a color wheel with all colors of the palette.
	 */
	public void drawWheel(){
		float swatchLength  = PApplet.TWO_PI/swatches.length;
		for (int i = 0; i < swatches.length; i++) {
			p.smooth();
			p.noFill();
			p.strokeCap(PApplet.SQUARE);
			p.strokeWeight(10);
			p.stroke(swatches[i].getColor());
			p.arc(50, 55, 50, 50, swatchLength*i, swatchLength*(i+1));
		}
	}

	
	private int setHSBColor(float h, float s, float b, float a){
		int color;
		int colorMode = p.g.colorMode;
		p.colorMode(PApplet.HSB);
		color = p.color(h,s,b,a);
		p.colorMode(colorMode);
		return color;
	}
	
	private int setHSBColor(float h, float s, float b){
		return setHSBColor(h,s,b,255);
	}

}

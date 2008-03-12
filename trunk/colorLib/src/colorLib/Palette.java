package colorLib;

import processing.core.*;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import com.apple.audio.jdirect.ArrayCopy;
public class Palette {

	private static final long serialVersionUID = 1L;

	protected int[] colors;

	protected PApplet p;

	public Palette(PApplet i_p) {
		this(i_p, 10);
	}

	public Palette(PApplet i_p, int i_length) {
		this(i_p, new int[i_length]);
	}

	public Palette(PApplet i_p, int[] i_colors) {
		p=i_p;
		colors=new int[i_colors.length];
		System.arraycopy(i_colors,0 ,colors, 0, i_colors.length);
	}

	public Palette(PApplet i_p, String[] i_colorNames) {
		this(i_p);
	}

	public Palette(PApplet i_p, PImage i_image) {
		p = i_p;
		colors = i_image.pixels;
		deletedDuplicate();
	}

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
		colors=new int[cnt];
		cnt = 0;
		for (int i = 0; i < length; i++) {
			if (b[start  + i * steps] != -1 && b[start  + i * steps +1] != -1 && b[start  + i * steps+2] != -1){
				colors[cnt] =   (0xff<<24)
							+ ((b[start  + i * steps] & 0xff) << 16)
							+ ((b[start + i * steps+1] & 0xff) << 8)
							+ (b[start + i * steps+2] & 0xff);
				cnt++;	
			}
		}
		
	}
	
	public void makeComplement(final int i_color){
		colors=new int[2];
		colors[0]=i_color;
		colors[1]=rotateRYB(180, i_color);
	}
	
	public void makeComplementary(int i_color){
		colors=new int[6];
		colors[0]=i_color;
		float brightness=0;
		if (p.brightness(i_color) >102){
			brightness = 25 + p.brightness(i_color)*0.25f;
		}else{
			brightness = 255 - p.brightness(i_color)*0.25f;
		}
		colors[1]=setHSBColor(p.hue(i_color), p.saturation(i_color), brightness);
		colors[2]=setHSBColor(p.hue(i_color), 25+p.saturation(i_color)*.3f, p.brightness(i_color)+76.5f);
		colors[3]=rotateRYB(180, i_color);
		i_color = colors[3];
		if (p.brightness(i_color) >102){
			brightness = 25 + p.brightness(i_color)*0.25f;
		}else{
			brightness = 255 - p.brightness(i_color)*0.25f;
		}
		colors[4]=setHSBColor(p.hue(i_color), p.saturation(i_color), brightness);
		colors[5]=setHSBColor(p.hue(i_color), 25+p.saturation(i_color)*.25f, p.brightness(i_color)+76.5f);
	}
	
	public void makeTriad(int i_color){
		colors = new int[3];
		float degree = 255 / 3f ;
		colors[0] = i_color;
		for (int i = 1; i < 3; i++) {
			colors[i] = rotateRGB(degree*i, i_color);
		}
	}
	
	public void makeTetrad(int i_color){
		colors = new int[4];
		float degree = 255 / 4f ;
		colors[0] = i_color;
		for (int i = 1; i < 4; i++) {
			colors[i] = rotateRGB(degree*i, i_color);
		}
	}
	
	public void darken() {
		lighten(-10);
	}

	public void darken(int i_step) {
			lighten(-i_step);
	}
	
	public void lighten() {
		lighten(10);
	}

	public void lighten(int i_step) {
		for (int i = 0; i < colors.length; i++) {
			colors[i] = setHSBColor(p.hue(colors[i]), p.saturation(colors[i]), p
					.brightness(colors[i])
					+ i_step);
			
		}
	}

	public void saturate() {
		saturate(10);
	}

	public void saturate(int i_step) {
		for (int i = 0; i < colors.length; i++) {
			colors[i] = setHSBColor(p.hue(colors[i]), p.saturation(colors[i])
					+ i_step, p.brightness(colors[i]));
		}
	}

	public void desaturate() {
		saturate(-10);
	}

	public void desaturate(int i_step) {
		saturate(-i_step);
	}

	public void rotateRGB(int i_angle) {
		for (int i = 0; i < colors.length; i++) {
			rotateRGB(i_angle, colors[i]);
		}
	}
	
	private int rotateRGB(float i_angle, int i_color) {
		return setHSBColor((p.hue(i_color) + i_angle) % 256, p.saturation(i_color), p.brightness(i_color));
	}

	public void  rotateRYB(float i_angle){
		for (int i = 0; i < colors.length; i++) {
			colors[i]=rotateRYB(i_angle, colors[i]);
		}
	}
	
	 private int rotateRYB(float i_angle, int i_color){
		 float hue = (p.hue(i_color)/255f)*360;
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
	        return setHSBColor(hue*255/360, p.saturation(i_color), p.brightness(i_color), p.alpha(i_color));
	 }
	 
	public void sortByHue() {

	}

	/**
	 * sorts a given colour palette by saturation with element at last index
	 * containing the most saturated item of the palette
	 */
	public void sortBySaturation() {
		int[] sorted = new int[colors.length];
		Hashtable ht = new Hashtable();
		for (int i = 0; i < colors.length; i++) {
			int r = (colors[i] >> 16) & 0xff;
			int g = (colors[i] >> 8) & 0xff;
			int b = colors[i] & 0xff;
			int maxComp = max(r, g, b);
			if (maxComp > 0) {
				sorted[i] = (int) ((maxComp - min(r, g, b)) / (float) maxComp * 0x7fffffff);
			} else {
				sorted[i] = 0;
			}
			ht.put(new Integer(sorted[i]), new Integer(colors[i]));
		}
		sorted = PApplet.sort(sorted);
		for (int i = 0; i < sorted.length; i++) {
			colors[i] = ((Integer) ht.get(new Integer(sorted[i]))).intValue();
		}
	}

	/**
	 * sorts a given colour palette by luminance with element at last index
	 * containing the "brightest" item of the palette
	 */
	public void sortByLuminance() {
		int[] sorted = new int[colors.length];
		Hashtable ht = new Hashtable();
		for (int i = 0; i < colors.length; i++) {
			sorted[i] = (77 * (colors[i] >> 16 & 0xff) + 151
					* (colors[i] >> 8 & 0xff) + 28 * (colors[i] & 0xff));
			ht.put(new Integer(sorted[i]), new Integer(colors[i]));
		}
		sorted = PApplet.sort(sorted);
		for (int i = 0; i < sorted.length; i++) {
			colors[i] = ((Integer) ht.get(new Integer(sorted[i]))).intValue();
		}

	}

	/**
	 * sorts a given colour palette by proximity to a colour with element at
	 * first index containing the "closest" item of the palette
	 * 
	 * @param basecol
	 */

	public void sortByProximity(int i_color) {
		int[] sorted = new int[colors.length];
		Hashtable ht = new Hashtable();
		int br = (i_color >> 16) & 0xff;
		int bg = (i_color >> 8) & 0xff;
		int bb = i_color & 0xff;
		for (int i = 0; i < colors.length; i++) {
			int r = (colors[i] >> 16) & 0xff;
			int g = (colors[i] >> 8) & 0xff;
			int b = colors[i] & 0xff;
			sorted[i] = (br - r) * (br - r) + (bg - g) * (bg - g) + (bb - b)
					* (bb - b);
			ht.put(new Integer(sorted[i]), new Integer(colors[i]));
		}
		sorted = PApplet.sort(sorted);
		for (int i = 0; i < sorted.length; i++) {
			colors[i] = ((Integer) ht.get(new Integer(sorted[i]))).intValue();
		}

	}

	public void interpolate(int i_color, float distance) {
		distance = constrain(distance, 0, 1);
		for (int i = 0; i < colors.length; i++) {
			int a1 = (colors[i] >> 24) & 0xff;
			int r1 = (colors[i] >> 16) & 0xff;
			int g1 = (colors[i] >> 8) & 0xff;
			int b1 = colors[i] & 0xff;

			int a2 = (i_color >> 24) & 0xff;
			int r2 = (i_color >> 16) & 0xff;
			int g2 = (i_color >> 8) & 0xff;
			int b2 = i_color & 0xff;

			colors[i] = ((int) (a1 * (1f - distance) + a2 * distance) << 24)
					| ((int) (r1 * (1f - distance) + r2 * distance) << 16)
					| ((int) (g1 * (1f - distance) + g2 * distance) << 8)
					| (int) (b1 * (1f - distance) + b2 * distance);
		}
	}

	public int getDarkest() {
		int darkest = colors[0];
		for (int i = 1; i < colors.length; i++) {
			if (p.brightness(darkest) > p.brightness(colors[i])) {
				darkest = colors[i];
			}
		}
		return darkest;
	}

	public int getLightest() {
		int lightest = colors[0];
		for (int i = 1; i < colors.length; i++) {
			if (p.brightness(lightest) < p.brightness(colors[i])) {
				lightest = colors[i];
			}
		}
		return lightest;
	}

	public int getAverage() {
		int a = 0;
		int r = 0;
		int g = 0;
		int b = 0;
		for (int i = 0; i < colors.length; i++) {
			a += colors[i] >> 24 & 0xff;
			r += colors[i] >> 16 & 0xff;
			g += colors[i] >> 8 & 0xff;
			b += colors[i] & 0xff;
		}
		return ((int) (a / colors.length) << 24)
				| ((int) (r / colors.length) << 16)
				| ((int) (g / colors.length) << 8) 
				| (int) (b / colors.length);
	}

	public int getColor(int i) {
		return colors[i];
	}

	public int[] getColors() {
		int[] i_colors = new int[colors.length];
		System.arraycopy(colors, 0, i_colors, 0, colors.length);
		return i_colors;
	}

	public String[] getNearestHues(){
		String[] nearestHues = new String[colors.length];
		for (int i = 0; i < colors.length; i++) {
			nearestHues[i]=getNearestHue(colors[i]);
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

	public void save(String i_fileTyp) {

	}

	public void addColor(int i_color) {
		colors = PApplet.append(colors, i_color);
	}

	public void addColor(String i_colorName) {

	}

	public void addColors(int[] i_colors) {
		colors = PApplet.concat(colors, i_colors);
	}

	public void addColors(String[] i_colorNames) {

	}
	
	public void setColor(int i_color, int i_position) {
		colors = PApplet.append(colors, i_color);
	}

	public void setColor(String i_colorName) {

	}

	public void setColors(int[] i_colors) {
		colors = i_colors;
	}

	public void setColors(String[] i_colorNames) {
			
	}
	
	public void deletedDuplicate(){
		HashSet h = new HashSet();
		for (int i = 0; i < colors.length; i++) {
			h.add(new Integer(colors[i]));
		}
		colors = new int[h.size()];
		Iterator iter = h.iterator();
		int cnt=0;
		while (iter.hasNext()) {
			colors[cnt] = ((Integer) iter.next()).intValue();
			cnt++;
		}
	}
	
	public void draw(){
		for (int i = 0; i < colors.length; i++) {
			p.fill(colors[i]);
			p.rect(i*20, 0, 20, 100);
		}
	}

	static private final int max(int a, int b, int c) {
		return (a > b) ? ((a > c) ? a : c) : ((b > c) ? b : c);
	}

	static private final int min(int a, int b, int c) {
		return (a < b) ? ((a < c) ? a : c) : ((b < c) ? b : c);
	}

	static private final float constrain(float amt, float low, float high) {
		return (amt < low) ? low : ((amt > high) ? high : amt);
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
		System.out.println("h: "+h+"s: "+s+"b: "+b);
		return setHSBColor(h,s,b,255);
	}

}

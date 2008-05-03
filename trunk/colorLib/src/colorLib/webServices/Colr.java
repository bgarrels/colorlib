package colorLib.webServices;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import processing.core.PApplet;
import processing.xml.XMLElement;
import colorLib.Palette;
import colorLib.Swatch;
/**
 * Colr is a container to query the <a href="http://www.colr.org">Colr service</a>. 
 * As the <a href="http://www.colr.org/api.html">API of Colr</a> is very limited there are only three methods to use.
 * @author Andreas Kšberle
 * @author Jan Vantomme
 * @example Colr
 */
public class Colr {
	
	final PApplet p;
	public boolean printXML = false;
	private String serverPage;
	
	/**
	 * @param i_p
	 */
	public Colr(final PApplet i_p){
		p=i_p;
	}
	
	/**
	 * Query the <a href="http://www.colr.org">Colr service</a> with a color and returns a string array with all related tags.
	 * @param hex String: hex value of a color
	 * @return String[]: string array with all tags associated  with this color
	 * @related searchThemes ( )
	 * @related searchColors ( )
	 * @example Colr_searchTags
	 */
	public String[] searchTags(final String hex){
		String url = "http://www.colr.org/rss/color/";
		XMLElement xml = new XMLElement(p, url + hex);
		String tags = (xml.getChildren("channel/items/item/description/tags"))[0].getContent();
		String[] tagArray = new String[0];
		if (tags != null || !tags.equalsIgnoreCase("")){
			tagArray= tags.split(",");
	    }else{
	    	PApplet.println("There are no tags for the color");
	    }
		if(printXML)printXML(url + hex);
		return tagArray;
	}
	
	/**
	 * @param i_color color: color 
	 */
	public String[] searchTags(final int i_color){
		return searchTags(PApplet.hex(i_color, 6));
	}
	
	/**
	 * @param i_color Swatch: swatch
	 */
	public String[] searchTags(final Swatch i_color){
		return searchTags(PApplet.hex(i_color.getColor(), 6));
	}
	
	/**
	 * Query the <a href="http://www.colr.org">Colr service</a> with a tag and 
	 * return a ColrTheme holding all colors and all tags associated with this colors.
	 * @param tag String: tag to query the service
	 * @return ColrTheme: a ColrTheme holding all colors and all tags associated with this colors
	 * @related searchTags ( )
	 * @related searchThemes ( )
	 * @example Colr
	 */
	public ColrTheme searchColors(String tag){
		String url = "http://www.colr.org/rss/tag/"+ tag;
		PApplet.println(url);
		XMLElement xml = new XMLElement(p, url);
		ColrTheme theme = new ColrTheme(p);
		XMLElement[] colors = (xml.getChildren("channel/items/item"));	
		for (int i = 0; i < colors.length; i++) {
			XMLElement item= colors[i];
			String title = item.getChildren("title")[0].getContent();
			PApplet.println(title);
			if(title.matches("[0-9A-Fa-f]{6}")){
				PApplet.println(title);
				theme.addColor(PApplet.unhex("FF"+title));
				theme.addThemeTags(item.getChildren("description/tags")[0].getContent());
			}
		}
		if(printXML)printXML(url);
		return theme;
	}
	
	/**
	 * Query the Colr service with the given tag. 
	 * Returns an array with all returned shemes as ColrThemes which stores the colors and 
	 * the tags associated with the scheme on Colr. 
	 * @param tag
	 * @return ColrTheme[]: array contains all theme matching the query
	 * @related searchTags ( )
	 * @related searchColors ( )
	 * @example Colr_searchThemes
	 */
	public ColrTheme[] searchThemes(String tag){
		String url = "http://www.colr.org/rss/tag/"+ tag;
		PApplet.println(url);
		XMLElement xml = new XMLElement(p, url);
		ArrayList themes = new ArrayList();
		XMLElement[] items = (xml.getChildren("channel/items/item"));
		for (int i = 0; i < items.length; i++) {
			XMLElement item = items[i];
			String title = item.getChildren("title")[0].getContent();
			if(title.startsWith("scheme")){
				ColrTheme theme = new ColrTheme(p);
				String[] colors = item.getChildren("description/colors")[0].getContent().split(" ");
				for (int j = 0; j < colors.length; j++) {
					String cl = colors[j];
					if(cl.matches("[0-9A-Fa-f]{6}")){
						theme.addColor(PApplet.unhex("FF"+cl));
					}
				}
				theme.addThemeTags(item.getChildren("description/tags")[0].getContent());
				themes.add(theme);
			}
		}
		if(printXML)printXML(url + tag);
		return ((ColrTheme[]) themes.toArray(new ColrTheme[themes.size()]));
	}
	
	private void printXML(String url){
		String lines[] = p.loadStrings(url);
		for (int i=0; i < lines.length; i++) {
			  PApplet.println(lines[i]);
		}
	}
	
	
	
}

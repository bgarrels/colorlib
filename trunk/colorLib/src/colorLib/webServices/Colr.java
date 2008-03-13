package colorLib.webServices;
import java.util.Hashtable;

import colorLib.*;
import processing.core.*;
import processing.xml.*;
import processing.core.*;
public class Colr {
	
	PApplet p;
	Hashtable colorTags;
	public Colr(PApplet i_p){
		p=i_p;
		String lines[] = p.loadStrings("http://www.colr.org/rss/tag/spring");
		for (int i=0; i < lines.length; i++) {
			  PApplet.println(lines[i]);
			}
	}
	
	public void getTagsForColor(String hex){
		String url = "http://www.colr.org/rss/color/";
		XMLElement xml = new XMLElement(p, url + hex);
		String tags = (xml.getChildren("channel/item/description/tags"))[0].getContent();
		if (tags != null || !tags.equalsIgnoreCase("")){
			colorTags.put(new Integer(PApplet.unhex("FF"+hex)), tags.split(","));
	    }
	}
	
	public void getTagsForColor(int color){
		getTagsForColor(PApplet.hex(color, 6));
	}
	
	public void getColorsForTag(String tag){
		String url = "http://www.colr.org/rss/tag/";
		XMLElement xml = new XMLElement(p, url + tag);
		XMLElement[] item = xml.getChildren("channel/item");
		for (int i = 0; i < item.length; i++) {
			if(containsString(item[i].getChildren("title")[0].getContent(), "scheme")){
				addTheme(item[i]);
			}else{
				addColor(item[i]);
			}
		}
	}
	
	private void addColor(XMLElement element) {
		// TODO Auto-generated method stub
		
	}

	private void addTheme(XMLElement item) {
		ColrTheme theme = new ColrTheme(p);
		theme.setThemeTags(item.getChildren("tags")[0].getContent());
	}

	private boolean containsString( String s, String subString ) {
		return s.indexOf( subString ) > -1 ? true : false;
	}
	
}

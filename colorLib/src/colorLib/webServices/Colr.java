package colorLib.webServices;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import colorLib.*;
import processing.core.*;
import processing.xml.*;
import processing.core.*;
public class Colr {
	
	PApplet p;
	ArrayList themes;
	
	public Colr(PApplet i_p){
		p=i_p;
		String lines[] = p.loadStrings("http://www.colr.org/rss/tag/spring");
		for (int i=0; i < lines.length; i++) {
			  PApplet.println(lines[i]);
			}
		themes = new ArrayList();
	}
	
	public void getTagsForColor(String hex){
		String url = "http://www.colr.org/rss/color/";
		XMLElement xml = new XMLElement(p, url + hex);
		String tags = (xml.getChildren("channel/item/description/tags"))[0].getContent();
		if (tags != null || !tags.equalsIgnoreCase("")){
			//colorTags.put(new Integer(PApplet.unhex("FF"+hex)), tags.split(","));
	    }
	}
	
	public void getTagsForColor(int color){
		getTagsForColor(PApplet.hex(color, 6));
	}
	
	public void getColorsForTag(String tag){
		String url = "http://www.colr.org/rss/tag/";
		XMLElement xml = new XMLElement(p, url + tag);
		XMLElement[] item = xml.getChildren("channel/items/item");
		for (int i = 0; i < item.length; i++) {
			if(containsString(item[i].getChildren("title")[0].getContent(), "scheme")){
				addTheme(item[i]);
			}else{
				//addColor(item[i]);
			}
		}
	}
	
	private void addColor(String colorString, ColrTheme theme) {
		if (colorString != null || !colorString.equalsIgnoreCase("")){
			String[] colors = colorString.split(" ");
			for (int i = 0; i < colors.length; i++) {
				theme.addColor(PApplet.unhex("FF"+colors[i]));
				PApplet.println("color: "+colors[i]);
			}
		}
	}

	private void addTheme(XMLElement item) {
		ColrTheme theme = new ColrTheme(p);
		theme.setThemeTags(item.getChildren("description/tags")[0].getContent());
		addColor(item.getChildren("description/colors")[0].getContent(), theme);
		themes.add(theme);
	}

	private boolean containsString( String s, String subString ) {
		return s.indexOf( subString ) > -1 ? true : false;
	}
	
	public void draw(){
		Iterator iter = themes.iterator();
		int cnt=0;
		int y=0;
		while (iter.hasNext()) {
			ColrTheme theme = (ColrTheme) iter.next();
			p.pushMatrix();
//			parent.translate(10+(cnt%10)*140, 10+y*48);
			p.translate(10+(cnt%10)*70, 10+y*70);
			theme.drawWheel();
			p.popMatrix();
			cnt++;
//			if(cnt%10==0)y++;
			if(cnt%10==0)y++;
		}
		System.out.print(cnt);
	}
	
}

package colorLib.webServices;

import java.util.ArrayList;
import java.util.Iterator;

import processing.core.*;
import processing.xml.*;

public class Kuler {

	private PApplet parent;

	ArrayList themes;

	/**
	 * @param parent
	 */
	public Kuler(PApplet parent) {
		this.parent = parent;
	}

	/**
	 * sets the url for getting the highest rated colors
	 */
	public void getHighestRated() {
		makePalettes("listtype=rating");
	}

	/**
	 * sets the url for getting the popular colors from the last 30 days
	 */
	public void getPopular() {
		makePalettes("listtype=rating&timespan=30");
	}

	/**
	 * sets the url for getting the popular colors for the specified number of
	 * days
	 */
	public void getPopular(int days) {
		makePalettes("listtype=rating&timespan=" + days);
	}

	/**
	 * sets the url for getting the most recent colors
	 */
	public void getRecent() {
		makePalettes("listtype=recent");
	}

	/**
	 * sets the url for getting random colors
	 */
	public void getRandom() {
		makePalettes("listtype=random");
	}

	/**
	 * setting the url for searching kuler
	 */
	public void search(String searchQuery) {
		makePalettes(searchQuery);
	}

	/**
	 * setting the url for searching kuler for a keyword and also set a
	 * filter... possible filters are themeID, userID, email, tag, hex and title
	 * 
	 * searches kuler for a string with a search filter
	 */
	public void search(String searchQuery, String searchFilter) {
		makePalettes("&searchQuery="+searchFilter + ":" + searchQuery);
	}

	/**
	 * fills the color palettes array with colors from the xml file.
	 * 
	 * @param querry
	 */
	public void makePalettes(String querry) {
		themes = new ArrayList();
		String url = "http://kuler.adobe.com/kuler/API/rss/search.cfm?itemsPerPage=40";
		PApplet.println(querry);
		XMLElement xml = new XMLElement(parent, url + querry);
		XMLElement[] themeItems = xml.getChildren("channel/item/kuler:themeItem");
		
		for (int i = 0; i < themeItems.length; i++) {
			KulerTheme kulerTheme = new KulerTheme(parent);
			XMLElement themeItem = themeItems[i];
			kulerTheme.setThemeID(themeItem.getChildren("kuler:themeID")[0].getContent());
			kulerTheme.setThemeTitle(themeItem.getChildren("kuler:themeTitle")[0].getContent());
			kulerTheme.setAuthorID(themeItem.getChildren("kuler:themeAuthor/kuler:authorID")[0].getContent());
			kulerTheme.setAuthorLabel(themeItem.getChildren("kuler:themeAuthor/kuler:authorLabel")[0].getContent());
			kulerTheme.setThemeTags(themeItem.getChildren("kuler:themeTags")[0].getContent());
			kulerTheme.setThemeRating(themeItem.getChildren("kuler:themeRating")[0].getContent());
			kulerTheme.setThemeDownloadCount(themeItem.getChildren("kuler:themeDownloadCount")[0].getContent());
			kulerTheme.setThemeCreatedAt(themeItem.getChildren("kuler:themeCreatedAt")[0].getContent());
			kulerTheme.setThemeEditedAt(themeItem.getChildren("kuler:themeEditedAt")[0].getContent());
			
			XMLElement[] themeSwatches = themeItem.getChildren("kuler:themeSwatches/kuler:swatch/kuler:swatchHexColor");
			int[] colors = new int[themeSwatches.length];
			for (int j = 0; j < themeSwatches.length; j++) {
				colors[j] = PApplet.unhex("FF"+themeSwatches[j].getContent());
				System.out.println(themeSwatches[j].getContent());
			}
			kulerTheme.setColors(colors);
			
			kulerTheme.printSettings();
			themes.add(kulerTheme);
		}
	}
	
	/**
	 * @return an array of all kulerthemes
	 */
	public KulerTheme[] getThemes() {
		return  (KulerTheme[]) themes.toArray(new KulerTheme[themes.size()]);
	}
	
	// TODO Search for tag in all kulerThemes
	/**
	 * @param tag
	 * @return an array of kulerThemes that matches the search tag
	 */
	public KulerTheme[] searchForTag(String tag) {
		return new KulerTheme[0];
	}
	
	public void draw(){
		Iterator iter = themes.iterator();
		int cnt=0;
		int y=0;
		while (iter.hasNext()) {
			KulerTheme theme = (KulerTheme) iter.next();
			parent.pushMatrix();
//			parent.translate(10+(cnt%10)*140, 10+y*48);
			parent.translate(10+(cnt%5)*70, 10+y*70);
			theme.drawWheel();
			parent.popMatrix();
			cnt++;
//			if(cnt%10==0)y++;
			if(cnt%5==0)y++;
		}
		System.out.print(cnt);
	}
	

}

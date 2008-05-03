package colorLib.webServices;

import java.util.ArrayList;
import java.util.Iterator;

import processing.core.PApplet;
import processing.xml.*;

/**
 * The Kuler object is an container to query adobes <a
 * href="http://kuler.adobe.com">kuler</a> service and get the response as an
 * KulerTheme object. Take a look at the <a
 * href="http://labs.adobe.com/wiki/index.php/Kuler">API</a> to see the
 * possibilities of the kuler service.<br/> At kuler the palette a maximal
 * count of 5 colors per theme. The default size of the themes per query is 20,
 * but you can increase the count up 100. To query more then 100 you have to
 * increase the startIndex. So a query with maxItems=100 and startIndex=1 will
 * give you themes fromm count 100 to 200. To use the class in an applet on your server
 * you have to set 
 * 
 * @author Andreas Kšberle
 * @author Jan Vantomme
 * @nosuperclasses
 * @exmaple Kuler_search
 */
public class Kuler {

	private PApplet p;

	private int maxItems = 20;

	private int startIndex = 0;

	private boolean printXML = false;
	
	private String serverPage = "http://kuler.adobe.com/kuler/API/rss/";

	private String pageTyp=".cfm";
	/**
	 * @param parent PApplet: typically use "this"
	 */
	public Kuler(final PApplet parent) {
		p = parent;
	}

	/**
	 * Get the highest rated colors as an array of <a href="kulertheme_class_kulertheme.htm">KulerThemes</a>.
	 * 
	 * @related getPopular ( )
	 * @related getPopular ( )
	 * @related getRecent ( )
	 * @related getRandom ( )
	 * @related search ( )
	 * @return KulerTheme[], an array of all themes which match the query
	 */
	public KulerTheme[] getHighestRated() {
		return makePalettes("&listtype=rating", "get");
	}

	/**
	 * Get the popular colors for the specified number of days (default is 30
	 * days) as an array of <a href="kulertheme_class_kulertheme.htm">KulerThemes</a>.
	 * 
	 * @related getHighestRated ( )
	 * @related getRecent ( )
	 * @related getRandom ( )
	 * @related search ( )
	 * @return KulerTheme[], an array of all themes which match the query
	 * @example Kuler_popular
	 */
	public KulerTheme[] getPopular() {
		return makePalettes("&listtype=rating&timespan=30", "get");
	}

	/**
	 * @param days int: Days
	 */
	public KulerTheme[] getPopular(final int days) {
		return makePalettes("&listtype=rating&timespan=" + days, "get");
	}

	/**
	 * Get the most recent colors as an array of <a href="kulertheme_class_kulertheme.htm">KulerThemes</a>.
	 * 
	 * @related getHighestRated ( )
	 * @related getPopular ( )
	 * @related getRandom ( )
	 * @related search ( )
	 * @return KulerTheme[], an array of all themes which match the query
	 * @example Kuler_recent
	 */
	public KulerTheme[] getRecent() {
		return makePalettes("&listtype=recent", "get");
	}

	/**
	 * Gets random colorsas an array of <a href="kulertheme_class_kulertheme.htm">KulerThemes</a>.
	 * 
	 * @related getHighestRated ( )
	 * @related getPopular ( )
	 * @related getRecent ( )
	 * @related search ( )
	 * @return KulerTheme[], an array of all themes which match the query
	 * @example Kuler_random
	 */
	public KulerTheme[] getRandom() {
		return makePalettes("&listtype=random", "get");
	}

	/**
	 * Use your own query string or creates one by a given filter name and a
	 * query. Possible filters are "themeID", "userID", "email", "tag", "hex"
	 * and "title". Take a look at the <a
	 * href="http://labs.adobe.com/wiki/index.php/Kuler#Search_RSS_Feeds">API</a> to see the
	 * possibilities of the kuler service.<br/> The result is an array of 
	 * <a href="kulertheme_class_kulertheme.htm">KulerThemes</a>.
	 * 
	 * @param searchQuery  String: your query
	 * @related getHighestRated ( )
	 * @related getPopular ( )
	 * @related getRecent ( )
	 * @related getRandom ( )
	 * @return KulerTheme[], an array of all themes which match the query
	 * @example Kuler_search
	 */
	public KulerTheme[] search(final String searchQuery) {
		return makePalettes(searchQuery, "search");
	}

	/**
	 * @param query  String: query that is use with a searchFilter
	 * @param filter String: one of the following filters: "themeID", "userID", "email", "tag", "hex" and "title"
	 */
	public KulerTheme[] search(final String query, final String filter) {
		return makePalettes("&searchQuery=" + query + ":" + filter, "search");
	}

	private KulerTheme[] makePalettes(final String querry, final String typ) {
		ArrayList themes = new ArrayList();
		StringBuffer url = new StringBuffer(serverPage).
				append(typ).
				append(pageTyp).
				append("?itemsPerPage=").
				append(maxItems).
				append("&startIndex=").
				append(startIndex).
				append(querry);
		PApplet.println(url.toString());
		if(printXML)printXML(url.toString());
		XMLElement xml = new XMLElement(p, url.toString());
		XMLElement[] themeItems = xml.getChildren("channel/item/kuler:themeItem");
		for (int i = 0; i < themeItems.length; i++) {
			
			XMLElement themeItem = themeItems[i];
			XMLElement[] themeSwatches = themeItem.getChildren("kuler:themeSwatches/kuler:swatch/kuler:swatchHexColor");
			int[] colors = new int[themeSwatches.length];
			for (int j = 0; j < themeSwatches.length; j++) {
				colors[j] = PApplet.unhex("FF" + themeSwatches[j].getContent());
			}
			KulerTheme kulerTheme = new KulerTheme(p, colors);
			
			kulerTheme.setThemeID(themeItem.getChildren("kuler:themeID")[0].getContent());
			kulerTheme.setThemeTitle(themeItem.getChildren("kuler:themeTitle")[0].getContent());
			kulerTheme.setAuthorID(themeItem.getChildren("kuler:themeAuthor/kuler:authorID")[0].getContent());
			kulerTheme.setAuthorLabel(themeItem.getChildren("kuler:themeAuthor/kuler:authorLabel")[0].getContent());
			kulerTheme.setThemeTags(themeItem.getChildren("kuler:themeTags")[0].getContent());
			kulerTheme.setThemeRating(themeItem.getChildren("kuler:themeRating")[0].getContent());
			kulerTheme.setThemeDownloadCount(themeItem.getChildren("kuler:themeDownloadCount")[0].getContent());
			kulerTheme.setThemeCreatedAt(themeItem.getChildren("kuler:themeCreatedAt")[0].getContent());
			kulerTheme.setThemeEditedAt(themeItem.getChildren("kuler:themeEditedAt")[0].getContent());

			
			themes.add(kulerTheme);
		}
		
		return (KulerTheme[]) themes.toArray(new KulerTheme[themes.size()]);
	}

	/**
	 * Get the maximum number of items to display on a page, in the range
	 * [1..100]. Default is 20.
	 * 
	 * @return int: maximum items per query
	 * @related setMaxItems ( )
	 * @related getStartIndex ( )
	 * @related setStartIndex ( )
	 * @example Kuler_maxItems
	 */
	public int getMaxItems() {
		return maxItems;
	}

	/**
	 * Set the maximum number of items to display on a page, in the range
	 * [1..100]. Default is 20.
	 * 
	 * @param maxItems int: maximum items per query
	 * @related getMaxItems ( )
	 * @related getStartIndex ( )
	 * @related setStartIndex ( )
	 */
	public void setMaxItems(int maxItems) {
		this.maxItems = maxItems;
	}

	/**
	 * A 0-based index into the list that specifies the first item to display.
	 * Default is 0, which displays the first item in the list.
	 * 
	 * @return int: start index
	 * @related getMaxItems ( )
	 * @related setMaxItems ( )
	 * @related setStartIndex ( )
	 * @example Kuler_maxItems
	 */
	public int getStartIndex() {
		return startIndex;
	}

	/**
	 * A 0-based index into the list that specifies the first item to display.
	 * Default is 0, which displays the first item in the list.
	 * 
	 * @param startIndex int: start index
	 * @related getMaxItems ( )
	 * @related setMaxItems ( )
	 * @related getStartIndex ( )
	 */
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	
	private void printXML(String url){
		String lines[] = p.loadStrings(url);
		PApplet.println("test");
		for (int i=0; i < lines.length; i++) {
			  PApplet.println(lines[i]);
		}
	}
	
	/**
	 * Use this methode to print the result xml in the console. 
	 * @param b boolean: set true if you want the result xml printed in the console 
	 */
	public void printXML(boolean b){
		printXML=b;
	}

	/**
	 * As you cant connect to the kuler service directly if you run your sketch as an applet,
	 * you can set a path to a php or whatever page that's querry the kuler service and
	 * response the xml to the applet.
	 * @param serverPage
	 */
	public void setserverPage(String serverPath, String pageTyp) {
		this.serverPage = serverPath;
		this.pageTyp = pageTyp;
	}

}

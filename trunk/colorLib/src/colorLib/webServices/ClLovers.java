package colorLib.webServices;

import java.util.ArrayList;

import processing.core.PApplet;
import xml.*;
import colorLib.Palette;

/**
 * @author Andreas Koeberle
 * @nosuperclass
 */
/**
 * @author andreaskoberle
 *
 */
public class ClLovers {
	private String lover, orderCol, sortBy;

	private int[] hueRange, briRange;

	private int numResults, resultOffset;

	final PApplet p;

	private boolean printXML;
	/**
	 * @param i_p
	 */
	public ClLovers(PApplet i_p) {
		p = i_p;
		hueRange = new int[2];
		hueRange[0]=0;
		hueRange[1]=359;
		briRange = new int[2];
		briRange[0]=0;
		briRange[1]=99;
		numResults=20;
		resultOffset=0;
	}

	/**
	 * @param i_keywords
	 * @return
	 */
	public ClLoversTheme getColors(final String i_keywords) {
		StringBuffer url = new StringBuffer("http://www.colourlovers.com/api/colors?").
		append("hueRange=").append(hueRange[0]).append(",").append(hueRange[1]).
		append("&briRange=").append(briRange[0]).append(",").append(briRange[1]).
		append("&numResults=").append(numResults).
		append("&resultOffset=").append(resultOffset).
		append("&keywords=").append(i_keywords);
		PApplet.println(url);
		String[] s = p.loadStrings(url.toString());
		StringBuffer buf =new StringBuffer();
		for (int i = 0; i < s.length; i++) {
			buf.append(s[i].replaceAll("CDATA.]", "CDATA[empty]"));
		}
		XMLElement xml = new XMLElement(buf.toString());
		XMLElement[] colors = xml.getChildren("color");
		ClLoversTheme theme = new ClLoversTheme(p);
		if (colors.length > 0) {
			for (int i = 0; i < colors.length; i++) {
				XMLElement color = colors[i];
//				theme.setTitle(color.getChildren("title")[0].getContent());
//				theme.setUserName(color.getChildren("userName")[0].getContent());
//				theme.setNumViews(color.getChildren("numViews")[0].getContent());
//				theme.setNumComments(color.getChildren("numComments")[0].getContent());
//				theme.setNumHearts(color.getChildren("numHearts")[0].getContent());
//				theme.setRank(color.getChildren("rank")[0].getContent());
//				theme.setDateCreated(color.getChildren("dateCreated")[0].getContent());
//				theme.setDescription(color.getChildren("description")[0].getContent());
				theme.addColor(PApplet.unhex("FF" +color.getChildren("hex")[0].getContent()));
			}
		}
		if(printXML)printXML(url.toString());
		return theme;
	}
	
	/**
	 * @param i_keywords
	 */
	public void getColors(final String[] i_keywords) {
		StringBuffer keywords = new StringBuffer();
		for (int i = 0; i < i_keywords.length; i++) {
			if (i > 0)
				keywords.append("+");
			keywords.append(i_keywords[i]);
		}
		getColors(keywords.toString());
	}
	
	/**
	 * @param i_keywords
	 * @return
	 */
	public ClLoversTheme[] getPalettes(final String i_keywords) {
		StringBuffer url = new StringBuffer("http://www.colourlovers.com/api/palettes?").
		append("hueRange=").append(hueRange[0]).append(",").append(hueRange[1]).
		append("&briRange=").append(briRange[0]).append(",").append(briRange[1]).
		append("&numResults=").append(numResults).
		append("&resultOffset=").append(resultOffset).
		append("&keywords=").append(i_keywords);
		PApplet.println(url);
		String[] s = p.loadStrings(url.toString());
		StringBuffer buf =new StringBuffer();
		for (int i = 0; i < s.length; i++) {
			buf.append(s[i].replaceAll("CDATA.]", "CDATA[empty]"));
		}
		XMLElement xml = new XMLElement(buf.toString());
		XMLElement[] palettes = xml.getChildren("palette");
		ArrayList themes = new ArrayList();
		if (palettes.length > 0) {
			for (int i = 0; i < palettes.length; i++) {
				ClLoversTheme theme = new ClLoversTheme(p);
				XMLElement palette = palettes[i];
				theme.setTitle(palette.getChildren("title")[0].getContent());
				theme.setUserName(palette.getChildren("userName")[0].getContent());
				theme.setNumViews(palette.getChildren("numViews")[0].getContent());
				theme.setNumComments(palette.getChildren("numComments")[0].getContent());
				theme.setNumHearts(palette.getChildren("numHearts")[0].getContent());
				theme.setRank(palette.getChildren("rank")[0].getContent());
				theme.setDateCreated(palette.getChildren("dateCreated")[0].getContent());
				theme.setDescription(palette.getChildren("description")[0].getContent());
				XMLElement[] colors = palette.getChildren("colors/hex");
				for (int j = 0; j < colors.length; j++) {
					theme.addColor(PApplet.unhex("FF" +colors[j].getContent()));
				}
				if(printXML)printXML(url.toString());
				themes.add(theme);
			}
		}
		return (ClLoversTheme[]) themes.toArray(new ClLoversTheme[themes.size()]);
	}
	
	/**
	 * @param i_keywords
	 */
	public void getPalettes(final String[] i_keywords) {
		StringBuffer keywords = new StringBuffer();
		for (int i = 0; i < i_keywords.length; i++) {
			if (i > 0)
				keywords.append("+");
			keywords.append(i_keywords[i]);
		}
		getPalettes(keywords.toString());
	}

	/**
	 * @return
	 */
	public int[] getBriRange() {
		return briRange;
	}

	/**
	 * @param briRange
	 */
	public void setBriRange(int[] briRange) {
		if(briRange.length!=2){
			throw new IllegalArgumentException( "The passed array hasn't the size of 2" ); 
		}else{
			this.briRange = briRange;
		}
	}

	/**
	 * @return
	 */
	public int[] getHueRange() {
		return hueRange;
	}

	/**
	 * @param hueRange
	 */
	public void setHueRange(int[] hueRange) {
		if(hueRange.length!=2){
			throw new IllegalArgumentException( "The passed array hasn't the size of 2" ); 
		}else{
			this.hueRange = hueRange;
		}
	}

	/**
	 * @return
	 */
	public String getLover() {
		return lover;
	}

	/**
	 * @param lover
	 */
	public void setLover(String lover) {
		this.lover = lover;
	}

	/**
	 * @return
	 */
	public String getOrderCol() {
		return orderCol;
	}

	/**
	 * @param orderCol
	 */
	public void setOrderCol(String orderCol) {
		if(orderCol.equals("dateCreated") || 
		   orderCol.equals("score") || 
		   orderCol.equals("name") || 
		   orderCol.equals("numVotes") || 
		   orderCol.equals("numViews")){
			this.orderCol = orderCol;
		}else{
			throw new IllegalArgumentException( "Only 'dateCreated', 'score', 'name', 'numVotes' or 'numViews' are allowed" ); 
		}
	}

	/**
	 * @return
	 */
	public String getSortBy() {
		return sortBy;
	}

	/**
	 * @param sortBy
	 */
	public void setSortBy(String sortBy) {
		if(orderCol.equals("ASC") || orderCol.equals("DESC")){
			this.sortBy = sortBy;
		}else{
			throw new IllegalArgumentException( "Only 'ASC' or 'DESC' are allowed" ); 
		}	
	}

	/**
	 * @return
	 */
	public int getNumResults() {
		return numResults;
	}

	/**
	 * @param numResults
	 */
	public void setNumResults(int numResults) {
		this.numResults = PApplet.constrain(numResults, 0, 100);
	}

	/**
	 * @return
	 */
	public int getResultOffset() {
		return resultOffset;
	}

	/**
	 * @param resultOffset
	 */
	public void setResultOffset(int resultOffset) {
		this.resultOffset = resultOffset;
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
}

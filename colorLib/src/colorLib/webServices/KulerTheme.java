package colorLib.webServices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import processing.core.PApplet;
import colorLib.Palette;

/**
 * @author Andreas Kšberle
 * @author Jan Vantomme
 * @nosuperclasses
 */
public class KulerTheme extends ColrTheme {
	private String themeID, themeTitle, authorLabel, authorID;

	private int themeRating;
	private int themeDownloadCount;

	private Date themeCreatedAt, themeEditedAt;

	protected KulerTheme(final PApplet i_p) {
		super(i_p);
	}

	/**
	 * Returns the id of the author, which can be used for another query against the kuler service.
	 * @return String: authorID
	 * @related getAuthorLabel ( ) 
	 * @related getThemeCreatedAt ( ) 
	 * @related getThemeDownloadCount ( ) 
	 * @related getThemeEditedAt ( ) 
	 * @related getThemeID ( ) 
	 * @related getThemeRating ( ) 
	 * @related getThemeTitle ( ) 
	 */
	public String getAuthorId() {
		return authorID;
	}

	protected void setAuthorID(final String authorID) {
		this.authorID = authorID;
	}

	/**
	 * Returns the name of the author.
	 * @return String: authorLabel
	 * @related getAuthorId ( )
	 * @related getThemeCreatedAt ( ) 
	 * @related getThemeDownloadCount ( ) 
	 * @related getThemeEditedAt ( ) 
	 * @related getThemeID ( ) 
	 * @related getThemeRating ( ) 
	 * @related getThemeTitle ( ) 
	 */
	public String getAuthorLabel() {
		return authorLabel;
	}

	protected void setAuthorLabel(final String authorLabel) {
		this.authorLabel = authorLabel;
	}

	/**
	 * Returns the date the theme was created.
	 * @return 	String: date, return the date formated with the passed dateFormat, default is "yyyy MM dd HH:mm",
	 * 		   	see <a href="http://java.sun.com/docs/books/tutorial/i18n/format/simpleDateFormat.html">suns java tutorial</a> for 
	 * 			posibble formats
	 * @related getAuthorId ( )
	 * @related getAuthorLabel ( ) 
	 * @related getThemeDownloadCount ( ) 
	 * @related getThemeEditedAt ( ) 
	 * @related getThemeID ( ) 
	 * @related getThemeRating ( ) 
	 * @related getThemeTitle ( ) 
	 */
	public String getThemeCreatedAt(String dateFormat) {
		SimpleDateFormat df = new SimpleDateFormat(dateFormat);
		return df.format(themeCreatedAt);
	}
	
	public String getThemeCreatedAt() {
		return getThemeCreatedAt("yyyy-MM-dd HH:mm:ss.S");
		
	}

	protected void setThemeCreatedAt(final String themeCreatedAt) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {
			this.themeCreatedAt = format.parse(themeCreatedAt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Returns how often the themes was downloaded. 
	 * @return int: download count
	 * @related getAuthorId ( )
	 * @related getAuthorLabel ( ) 
	 * @related getThemeCreatedAt ( ) 
	 * @related getThemeEditedAt ( ) 
	 * @related getThemeID ( ) 
	 * @related getThemeRating ( ) 
	 * @related getThemeTitle ( ) 
	 */
	public int getThemeDownloadCount() {
		return themeDownloadCount;
	}

	protected void setThemeDownloadCount(final String themeDownloadCount) {
		this.themeDownloadCount = Integer.valueOf(themeDownloadCount).intValue();
	}

	/**
	 * Returns the date the theme was last modified.
	 * @return Date: date
	 * @related getAuthorId ( )
	 * @related getAuthorLabel ( ) 
	 * @related getThemeCreatedAt ( ) 
	 * @related getThemeDownloadCount ( ) 
	 * @related getThemeID ( ) 
	 * @related getThemeRating ( ) 
	 * @related getThemeTitle ( ) 
	 */
	public Date getThemeEditedAt() {
		return themeEditedAt;
	}

	protected void setThemeEditedAt(final String themeEditedAt) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {
			this.themeEditedAt = format.parse(themeEditedAt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the id of the theme, which can be used for another query against the kuler service.
	 * @return String: ID
	 * @related getAuthorId ( )
	 * @related getAuthorLabel ( ) 
	 * @related getThemeCreatedAt ( ) 
	 * @related getThemeDownloadCount ( ) 
	 * @related getThemeEditedAt ( ) 
	 * @related getThemeRating ( ) 
	 * @related getThemeTitle ( ) 
	 */
	public String getThemeID() {
		return themeID;
	}

	protected void setThemeTags(String i_themeTags) {
		if (themeTags != null || !i_themeTags.equalsIgnoreCase("")){
			themeTags=PApplet.concat(themeTags, i_themeTags.split(","));
	    }
	}
	
	protected void setThemeID(final String themeID) {
		this.themeID = themeID;
	}

	/**
	 * Return the rating of the theme
	 * @return int: rating
	 * @related getAuthorId ( )
	 * @related getAuthorLabel ( ) 
	 * @related getThemeCreatedAt ( ) 
	 * @related getThemeDownloadCount ( ) 
	 * @related getThemeEditedAt ( ) 
	 * @related getThemeID ( ) 
	 * @related getThemeTitle ( ) 
	 */
	public float getThemeRating() {
		return themeRating;
	}

	protected void setThemeRating(final String themeRating) {
		this.themeRating = Float.valueOf(themeRating).intValue();
	}

	/**
	 * Return the title of the theme.
	 * @return String: title
	 * @related getAuthorId ( )
	 * @related getAuthorLabel ( ) 
	 * @related getThemeCreatedAt ( ) 
	 * @related getThemeDownloadCount ( ) 
	 * @related getThemeEditedAt ( ) 
	 * @related getThemeID ( ) 
	 * @related getThemeRating ( ) 
	 */
	public String getThemeTitle() {
		return themeTitle;
	}

	protected void setThemeTitle(final String themeTitle) {
		this.themeTitle = themeTitle;
	}
	
	

}

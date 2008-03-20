package colorLib.webServices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import processing.core.PApplet;
import colorLib.Palette;

public class KulerTheme extends ColrTheme {
	private String themeID, themeTitle, authorLabel, authorID;

	private float themeRating;
	private int themeDownloadCount;

	private Date themeCreatedAt, themeEditedAt;

	KulerTheme(final PApplet i_p) {
		super(i_p);
	}

	public String getAuthorId() {
		return authorID;
	}

	public void setAuthorID(final String authorID) {
		this.authorID = authorID;
	}

	public String getAuthorLabel() {
		return authorLabel;
	}

	public void setAuthorLabel(final String authorLabel) {
		this.authorLabel = authorLabel;
	}

	public Date getThemeCreatedAt() {
		return themeCreatedAt;
	}

	public void setThemeCreatedAt(final String themeCreatedAt) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {
			this.themeCreatedAt = format.parse(themeCreatedAt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int getThemeDownloadCount() {
		return themeDownloadCount;
	}

	public void setThemeDownloadCount(final String themeDownloadCount) {
		this.themeDownloadCount = Integer.valueOf(themeDownloadCount).intValue();
	}

	public Date getThemeEditedAt() {
		return themeEditedAt;
	}

	public void setThemeEditedAt(final String themeEditedAt) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {
			this.themeEditedAt = format.parse(themeEditedAt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getThemeID() {
		return themeID;
	}

	public void setThemeID(final String themeID) {
		this.themeID = themeID;
	}

	public float getThemeRating() {
		return themeRating;
	}

	public void setThemeRating(final String themeRating) {
		this.themeRating = Float.valueOf(themeRating).intValue();
	}

	

	public String getThemeTitle() {
		return themeTitle;
	}

	public void setThemeTitle(final String themeTitle) {
		this.themeTitle = themeTitle;
	}
	
	public void printSettings(){
		System.out.println("themeID :" + themeID);
		System.out.println("themeTitle :" + themeTitle);
		System.out.println("authorLabel :" + authorLabel);
		System.out.println("authorID :" + authorID);
		System.out.println("themeRating :" + themeRating);
		System.out.println("themeRating :" + themeRating);
		System.out.println("themeTags :");
		for (int i = 0; i < themeTags.length; i++) {
			System.out.print(themeTags[i]);
		}
		System.out.println("---------------------------------------");
	}

}

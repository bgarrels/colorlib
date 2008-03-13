package colorLib.webServices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import processing.core.PApplet;
import colorLib.Palette;

public class KulerTheme extends ColrTheme {
	private String themeID, themeTitle, authorLabel, authorID;

	private String[] themeTags;

	private float themeRating;
	private int themeDownloadCount;

	private Date themeCreatedAt, themeEditedAt;

	KulerTheme(PApplet i_p) {
		super(i_p);
	}

	public String getAuthorId() {
		return authorID;
	}

	public void setAuthorID(String authorID) {
		this.authorID = authorID;
	}

	public String getAuthorLabel() {
		return authorLabel;
	}

	public void setAuthorLabel(String authorLabel) {
		this.authorLabel = authorLabel;
	}

	public Date getThemeCreatedAt() {
		return themeCreatedAt;
	}

	public void setThemeCreatedAt(String themeCreatedAt) {
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

	public void setThemeDownloadCount(String themeDownloadCount) {
		this.themeDownloadCount = Integer.valueOf(themeDownloadCount).intValue();
	}

	public Date getThemeEditedAt() {
		return themeEditedAt;
	}

	public void setThemeEditedAt(String themeEditedAt) {
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

	public void setThemeID(String themeID) {
		this.themeID = themeID;
	}

	public float getThemeRating() {
		return themeRating;
	}

	public void setThemeRating(String themeRating) {
		this.themeRating = Float.valueOf(themeRating).intValue();
	}

	

	public String getThemeTitle() {
		return themeTitle;
	}

	public void setThemeTitle(String themeTitle) {
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

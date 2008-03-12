package kuler;

import java.util.Date;
import processing.core.PApplet;
import colorLib.Palette;

public class KulerTheme extends Palette {
	private String themeID, themeTitle, authorLabel, authorId;

	private String[] themeTags;

	private int themeRating, themeDownloadCount;

	private Date themeCreatedAt, themeEditedAt;

	KulerTheme(PApplet i_p) {
		super(i_p, 5);
	}
	
	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
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

	public void setThemeCreatedAt(Date themeCreatedAt) {
		this.themeCreatedAt = themeCreatedAt;
	}

	public int getThemeDownloadCount() {
		return themeDownloadCount;
	}

	public void setThemeDownloadCount(int themeDownloadCount) {
		this.themeDownloadCount = themeDownloadCount;
	}

	public Date getThemeEditedAt() {
		return themeEditedAt;
	}

	public void setThemeEditedAt(Date themeEditedAt) {
		this.themeEditedAt = themeEditedAt;
	}

	public String getThemeID() {
		return themeID;
	}

	public void setThemeID(String themeID) {
		this.themeID = themeID;
	}

	public int getThemeRating() {
		return themeRating;
	}

	public void setThemeRating(int themeRating) {
		this.themeRating = themeRating;
	}

	public String[] getThemeTags() {
		return themeTags;
	}

	public void setThemeTags(String[] themeTags) {
		this.themeTags = themeTags;
	}

	public String getThemeTitle() {
		return themeTitle;
	}

	public void setThemeTitle(String themeTitle) {
		this.themeTitle = themeTitle;
	}


}

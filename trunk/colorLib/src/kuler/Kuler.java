package kuler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import colorLib.*;

import processing.core.*;
import processing.xml.*;

public class Kuler {
	PApplet p;
	ArrayList themes;
	public Kuler(PApplet i_p, String i_tag) {
		p = i_p;
		themes = new ArrayList();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		XMLElement root = new XMLElement(p,
				"http://kuler.adobe.com/kuler/API/rss/search.cfm?itemsPerPage=100&searchQuery=tag:"
						+ i_tag);
		XMLElement channel = root.getChild(0); 
		int channelSize = channel.getChildCount();
		for (int i = 0; i < channelSize; i++) {
			XMLElement item = channel.getChild(i);
			if(item.getName().equals("item")){
				KulerTheme kulerTheme = new KulerTheme(p);
				int itemLength = item.getChildCount();
				for (int j = 0; j < itemLength; j++) {
					XMLElement themeItem = item.getChild(j);
					if(themeItem.getName().equals("kuler:themeItem")){
						int themeItemLength = themeItem.getChildCount();
						for (int k = 0; k < themeItemLength; k++) {
							XMLElement kulerValue = themeItem.getChild(k);
							if(kulerValue.getName().equals("kuler:themeID")){
								kulerTheme.setThemeID(kulerValue.getContent());
							}else if(kulerValue.getName().equals("kuler:themeTitle")){
								kulerTheme.setThemeTitle(kulerValue.getContent());
							}else if(kulerValue.getName().equals("kuler:themeAuthor")){
								int themeAuthorLength = kulerValue.getChildCount();
								for (int l = 0; l < themeAuthorLength; l++) {
									XMLElement kulerAuthor = kulerValue.getChild(l);
									if(kulerAuthor.getName().equals("kuler:authorID")){
										kulerTheme.setAuthorId(kulerValue.getContent());
									}
									if(kulerAuthor.getName().equals("kuler:authorLabel")){
										kulerTheme.setAuthorLabel(kulerValue.getContent());
									}
								}
							}else if(kulerValue.getName().equals("kuler:themeTags")){
								String[] tagArray = null;
								String tags = kulerValue.getContent();
								if (tags != null || !tags.equalsIgnoreCase("")){
									tagArray = tags.split(",");
							    }
								kulerTheme.setThemeTags(tagArray);
							}else if(kulerValue.getName().equals("kuler:themeRating")){
								kulerTheme.setThemeRating(new Integer(kulerValue.getContent()).intValue());
							}else if(kulerValue.getName().equals("kuler:themeDownloadCount")){
								kulerTheme.setThemeDownloadCount(new Integer(kulerValue.getContent()).intValue());
							}else if(kulerValue.getName().equals("kuler:themeCreatedAt")){
								try {
									kulerTheme.setThemeCreatedAt(format.parse( kulerValue.getContent() ));
								} catch (ParseException e) {
									e.printStackTrace();
								}
								kulerTheme.setThemeCreatedAt(new Date());
							}else if(kulerValue.getName().equals("kuler:themeEditedAt")){
								try {
									kulerTheme.setThemeEditedAt(format.parse( kulerValue.getContent() ));
								} catch (ParseException e) {
									e.printStackTrace();
								}
							}else if(kulerValue.getName().equals("kuler:themeSwatches")){
								int swatchesLength= kulerValue.getChildCount();
								int[] colors = new int[swatchesLength];
								for (int l = 0; l < swatchesLength; l++) {
									XMLElement swatch = kulerValue.getChild(l);
									int swatchLength = swatch.getChildCount();
									for (int m = 0; m < swatchLength; m++) {
										if(swatch.getChild(m).getName().equals("kuler:swatchHexColor")){
											colors[l] = (int) Long.parseLong( "FF"+swatch.getChild(m).getContent().trim(), 16  );
										}
									}
								}
								kulerTheme.setColors(colors);
							}
							
						}
					}
				}
				themes.add(kulerTheme);
			}
		}
	}
	
	public void draw(){
		Iterator iter = themes.iterator();
		int cnt=0;
		while (iter.hasNext()) {
			Gradient g = new Gradient((Palette) iter.next(), 100, false);
			p.pushMatrix();
			p.translate(0, cnt*100);
			g.draw();
			p.popMatrix();
			cnt++;
		}
	}

}

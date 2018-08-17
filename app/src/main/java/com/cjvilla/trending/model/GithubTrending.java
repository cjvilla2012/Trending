package com.cjvilla.trending.model;

import android.databinding.BaseObservable;

import org.parceler.Parcel;

@Parcel
public class GithubTrending extends BaseObservable{
	protected String author;
	protected String name;
	protected String url;
	protected String description;
	protected String language;
	protected int stars;

	public String getAuthor() {
		return author;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public String getDescription() {
		return description;
	}

	public String getLanguage() {
		return language;
	}

	public int getStars() {
		return stars;
	}
}

package com.cjvilla.trending.model;

import android.databinding.BaseObservable;

import org.parceler.Parcel;

@Parcel
public class GithubRepo extends BaseObservable {
	//Fields are declared protected to avoid Reflection with Parcels
	protected String full_name;
	protected String html_url;
	protected String description;
	protected int stargazers_count;
	protected int open_issues;
	protected GithubPerson owner;

	public String getFull_name() {
		return full_name;
	}

	public String getHtml_url() {
		return html_url;
	}

	public String getDescription() {
		return description;
	}

	public int getStargazers_count() {
		return stargazers_count;
	}

	public int getOpen_issues() {
		return open_issues;
	}

	public GithubPerson getOwner() {
		return owner;
	}
}


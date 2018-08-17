package com.cjvilla.trending.model;

import android.databinding.BaseObservable;

import org.parceler.Parcel;

@Parcel
public class GithubPerson extends BaseObservable {
	protected String login;
	protected String avatar_url;
	protected String name;
	protected String location;
	protected String email;
	protected int public_repos;
	protected int followers;
	protected int following;

	public String getLogin() {
		return login;
	}

	public String getAvatar_url() {
		return avatar_url;
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public String getEmail() {
		return email;
	}

	public int getRepositories() {
		return public_repos;
	}

	public int getFollowers() {
		return followers;
	}

	public int getFollowing() {
		return following;
	}

}

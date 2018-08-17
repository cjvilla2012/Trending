package com.cjvilla.trending.model;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cjvilla.trending.ui.activity.DisplayRepoActivity;

public class VMHandlers {
	private AppCompatActivity activity;

	public VMHandlers(AppCompatActivity activity) {
		this.activity=activity;
	}

	public void showRepo(View view, GithubTrending trending) {
		DisplayRepoActivity.navigate(activity,view,trending);
	}
}
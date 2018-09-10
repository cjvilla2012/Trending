/*
 * Copyright (c) 2018 CJ Villa. All rights reserved
 */

package com.cjvilla.trending.model;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cjvilla.trending.ui.activity.BusinessDisplayRepoActivity;

public class BusinessVMHandlers {
	private AppCompatActivity activity;

	public BusinessVMHandlers(AppCompatActivity activity) {
		this.activity = activity;
	}

	public void showRepo(View view, GithubRepo repo) {
		BusinessDisplayRepoActivity.navigate(activity, view, repo);
	}

}
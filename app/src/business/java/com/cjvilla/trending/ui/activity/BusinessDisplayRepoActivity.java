/*
 * Copyright (c) 2018 CJ Villa. All rights reserved
 */

package com.cjvilla.trending.ui.activity;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cjvilla.trending.R;
import com.cjvilla.trending.model.GithubRepo;

import org.parceler.Parcels;

public class BusinessDisplayRepoActivity extends DisplayRepoActivity {
	private static final String KEY_REPO = "repo";

	public static void navigate(AppCompatActivity activity, View transitionImage, GithubRepo repo) {
		Intent intent = new Intent(activity, BusinessDisplayRepoActivity.class);
		intent.putExtra(KEY_REPO, Parcels.wrap(repo));
		ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage,
				activity.getString(R.string.transition));
		ActivityCompat.startActivity(activity, intent, options.toBundle());
	}

	@Override
	protected void getRepo() {
		GithubRepo repo = Parcels.unwrap(getIntent().getParcelableExtra(KEY_REPO));
		displayRepo(repo);
	}

	@Override
	protected void detach() {
		//Does nothing because there is no presenter
	}
}

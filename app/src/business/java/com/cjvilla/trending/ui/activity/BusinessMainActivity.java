/*
 * Copyright (c) 2018 CJ Villa. All rights reserved
 */

package com.cjvilla.trending.ui.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;

import com.cjvilla.trending.R;
import com.cjvilla.trending.databinding.ViewRepoBinding;
import com.cjvilla.trending.model.BusinessVMHandlers;
import com.cjvilla.trending.model.GithubRepo;
import com.cjvilla.trending.ui.presenter.BusinessMainActivityPresenter;
import com.cjvilla.trending.ui.presenter.Contract;
import com.xwray.groupie.databinding.BindableItem;

import java.util.List;

/**
 * The Business app starts by showing a different Github feed: followers of a
 * Github id (default being "JakeWharton"). It also supports a Search bar to allow
 * finding another user.
 */
public class BusinessMainActivity extends MainActivity implements Contract.ReposView {
	private static final String DEFAULT_GITHUB_ID = "JakeWharton";

	private BusinessMainActivityPresenter presenter = new BusinessMainActivityPresenter();

	@Override
	public void onNewIntent(Intent intent) {
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			//performSearch(query);
		} else {
			showProgress(true);
			presenter.attach(this);
			presenter.loadRepos(DEFAULT_GITHUB_ID);
		}
	}

	@Override
	public void onStop() {
		super.onStop();
		showProgress(false);
		presenter.detach();
	}

	@Override
	public void displayRepos(List<GithubRepo> repos) {
		showProgress(false);
		initRecyclerView(repos);
	}

	private void initRecyclerView(List<GithubRepo> items) {
		binding.recycler.setAdapter(groupAdapter);
		binding.recycler.setLayoutManager(new GridLayoutManager(this, 2));
		groupAdapter.clear();
		for (GithubRepo repo : items) {
			groupAdapter.add(new RepoItem(repo));
		}
	}

	private class RepoItem extends BindableItem<ViewRepoBinding> {
		private GithubRepo githubRepo;

		private RepoItem(GithubRepo trending) {
			this.githubRepo = trending;
		}

		@Override
		public void bind(@NonNull ViewRepoBinding viewHolder, int position) {
			viewHolder.setViewModel(githubRepo);
			viewHolder.setViewHandler(new BusinessVMHandlers(BusinessMainActivity.this));
		}

		@Override
		public int getLayout() {
			return R.layout.view_repo;
		}
	}
}

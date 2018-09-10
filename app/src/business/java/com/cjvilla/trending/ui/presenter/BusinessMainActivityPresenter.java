/*
 * Copyright (c) 2018 CJ Villa. All rights reserved
 */

package com.cjvilla.trending.ui.presenter;

import com.cjvilla.trending.com.cjvilla.trending.ui.presenter.BaseView;
import com.cjvilla.trending.com.cjvilla.trending.ui.presenter.MainActivityPresenter;
import com.cjvilla.trending.model.GithubRepo;
import com.cjvilla.trending.net.repository.BusinessServerRepository;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

public class BusinessMainActivityPresenter extends MainActivityPresenter implements Contract.ReposPresenter {
	private BusinessServerRepository serverRepository;
	private Contract.ReposView reposView;

	@Override
	public void attach(BaseView view) {
		this.reposView = (Contract.ReposView) view;
		serverRepository = BusinessServerRepository.instance();
	}

	@Override
	public void loadRepos(String githubId) {
		if (compositeDisposable == null) {
			compositeDisposable = new CompositeDisposable();
		}
		compositeDisposable.add(serverRepository.getUserRepos(githubId)
				.subscribe(
						repos -> {
							reposView.displayRepos(repos);
						},
						throwable -> {
							//For any exception we return an empty list to stop the progress.
							//Obviously hideous for production
							reposView.displayRepos(new ArrayList<GithubRepo>());
							Timber.e(throwable);
						}
				));
	}
}

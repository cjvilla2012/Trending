/*
 * Copyright (c) 2018 CJ Villa. All rights reserved
 */

package com.cjvilla.trending.ui.presenter;

import com.cjvilla.trending.com.cjvilla.trending.ui.presenter.BaseView;
import com.cjvilla.trending.com.cjvilla.trending.ui.presenter.MainActivityPresenter;
import com.cjvilla.trending.net.repository.BusinessServerRepository;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

public class BusinessMainActivityPresenter extends MainActivityPresenter implements Contract.ReposPresenter {
	private BusinessServerRepository serverRepository;
	private Contract.ReposView reposView;

	@Override
	public void attach(BaseView view) {
		this.reposView = (Contract.ReposView) view;
		compositeDisposable = new CompositeDisposable();
		serverRepository = BusinessServerRepository.instance();
	}

	@Override
	public void loadRepos(String githubId) {
		compositeDisposable.add(serverRepository.getUserRepos(githubId)
				.subscribe(
						repos -> {
							reposView.displayRepos(repos);
						},
						throwable -> {
							Timber.e(throwable);
						}
				));
	}
}

/*
 * Copyright (c) 2018 CJ Villa. All rights reserved
 */

package com.cjvilla.trending.ui.presenter;

import com.cjvilla.trending.model.GithubRepo;

import java.util.List;

public interface Contract {
	interface ReposView {
		void displayRepos(List<GithubRepo> repos);
	}

	interface ReposPresenter {
		void loadRepos(String githubId);

	}
}

package com.cjvilla.trending.com.cjvilla.trending.ui.presenter;

import com.cjvilla.trending.model.GithubRepo;
import com.cjvilla.trending.model.GithubTrending;

import java.util.List;

public interface Contract {
	interface TrendingView extends BaseView {
		void displayTrending(List<GithubTrending> trending);
	}

	interface TrendingPresenter extends BasePresenter {
		void loadTrending();

	}

	interface RepoView extends BaseView {
		void displayRepo(GithubRepo repo);
	}

	interface RepoPresenter extends BasePresenter {
		void loadRepo(String owner, String name);

	}

}

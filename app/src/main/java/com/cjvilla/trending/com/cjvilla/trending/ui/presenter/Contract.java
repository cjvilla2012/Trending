package com.cjvilla.trending.com.cjvilla.trending.ui.presenter;

import com.cjvilla.trending.model.GithubRepo;
import com.cjvilla.trending.model.GithubTrending;

import java.util.List;

public interface Contract {
	interface TrendingView  {
		void displayTrending(List<GithubTrending> trending);
	}

	interface TrendingPresenter {

		void loadTrending();

	}

	interface RepoView  {
		void displayRepo(GithubRepo repo);
	}

	interface RepoPresenter {

		void loadRepo(String owner,String name);

	}

}

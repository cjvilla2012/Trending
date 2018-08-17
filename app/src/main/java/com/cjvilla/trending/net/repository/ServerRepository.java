package com.cjvilla.trending.net.repository;

import com.cjvilla.trending.model.GithubRepo;
import com.cjvilla.trending.model.GithubTrending;
import com.cjvilla.trending.net.api.ServerApi;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerRepository {
	private static ServerRepository serverRepository;

	public static ServerRepository instance() {
		if (serverRepository == null) {
			serverRepository = new ServerRepository();
		}
		return serverRepository;
	}

	private ServerRepository() {
	}

	/**
	 * Get a repo
	 * @return A list that may be empty
	 */
	public Observable<GithubRepo> getRepo(String author,String name) {
		return createRetrofit("https://api.github.com/repos/")
				.create(ServerApi.class)
				.getRepo(author,name)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io());
	}

	/**
	 * Get a list of trending repositories
	 * @return A list that may be empty
	 */
	public Observable<List<GithubTrending>> getTrending() {
		return createRetrofit("https://github-trending-api.now.sh/")
				.create(ServerApi.class)
				.getTrendingRepos()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io());
	}

	/**
	 * Create a simple Retrofit with no base endpoint
	 * @return
	 */
	private Retrofit createRetrofit(String baseUrl) {
		return new Retrofit.Builder()
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.baseUrl(baseUrl)
				.build();
	}
}

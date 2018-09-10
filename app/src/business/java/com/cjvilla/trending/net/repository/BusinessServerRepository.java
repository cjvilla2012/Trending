/*
 * Copyright (c) 2018 CJ Villa. All rights reserved
 */

package com.cjvilla.trending.net.repository;

import com.cjvilla.trending.model.GithubRepo;
import com.cjvilla.trending.net.api.BusinessServerApi;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BusinessServerRepository {
	private static BusinessServerRepository serverRepository;

	public static BusinessServerRepository instance() {
		if (serverRepository == null) {
			serverRepository = new BusinessServerRepository();
		}
		return serverRepository;
	}

	private BusinessServerRepository() {
	}

	/**
	 * Get a list of public repos for the specified Github Id.
	 *
	 * @param githubId The Github user
	 * @return A list that may be empty
	 */
	public Observable<List<GithubRepo>> getUserRepos(String githubId) {
		return ServerRepository.createRetrofit("https://api.github.com/")
				.create(BusinessServerApi.class)
				.getUserRepos(githubId)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io());
	}

}

/*
 * Copyright (c) 2018 CJ Villa. All rights reserved
 */

package com.cjvilla.trending.net.api;

import com.cjvilla.trending.model.GithubRepo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Get the public repos for the specified user.
 * This does not implement pagination, and a user may have no public repos.
 */
public interface BusinessServerApi {

	@GET("users/{githubId}/repos")
	Observable<List<GithubRepo>> getUserRepos(
			@Path("githubId") String githubId
	);
}

package com.cjvilla.trending.net.api;

import com.cjvilla.trending.model.GithubRepo;
import com.cjvilla.trending.model.GithubTrending;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/** Get the trending repos and then the details. There is no "trending" endpoint
 * in the standard Github API, so we used:
 * @see <a href="https://github.com/huchenme/github-trending-api">this</a>
 */
public interface ServerApi {

	@GET("{owner}/{repo}")
	Observable<GithubRepo> getRepo(
			@Path("owner") String owner,
			@Path("repo") String repo
	);

	@GET("repositories?since=weekly")
	Observable<List<GithubTrending>> getTrendingRepos();
}

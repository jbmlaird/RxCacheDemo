package bj.rxcachedemo.github;

import bj.rxcachedemo.json.GithubResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Josh Laird on 22/04/2017.
 * <p>
 * Interface to interact with OpenWeather API.
 */
public interface GithubApi
{
    @GET("search/repositories")
    Single<GithubResponse> searchGithub(@Query("q") String query);
}

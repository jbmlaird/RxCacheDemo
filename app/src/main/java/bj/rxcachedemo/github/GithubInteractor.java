package bj.rxcachedemo.github;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Inject;

import bj.rxcachedemo.ActivityScope;
import bj.rxcachedemo.cache.CacheProviders;
import bj.rxcachedemo.json.GithubResponse;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Josh Laird on 22/04/2017.
 * <p>
 * Where the magic happens.
 */
@ActivityScope
public class GithubInteractor
{
    private static final String GITHUB_BASE_URL = "https://api.github.com/";
    private GithubApi githubApi;
    private CacheProviders cacheProviders;

    @Inject
    public GithubInteractor(Context context)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(GITHUB_BASE_URL)
                .build();

        githubApi = retrofit.create(GithubApi.class);

        cacheProviders = new RxCache.Builder()
                .setMaxMBPersistenceCache(5) // if set to 0 it will only cache in memory
                .persistence(context.getFilesDir(), new GsonSpeaker())
                .using(CacheProviders.class);
    }

    public Observable<GithubResponse> searchGithub(String query, boolean update)
    {
        return cacheProviders.searchGithub(githubApi.searchGithub(query), new DynamicKey(query), new EvictDynamicKey(update))
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io());
    }
}

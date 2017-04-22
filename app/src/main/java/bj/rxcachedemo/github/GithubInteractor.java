package bj.rxcachedemo.github;

import android.content.Context;

import javax.inject.Inject;

import bj.rxcachedemo.ActivityScope;
import bj.rxcachedemo.cache.CacheProviders;
import bj.rxcachedemo.json.GithubResponse;
import bj.rxcachedemo.testingutils.MySchedulerProvider;
import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import retrofit2.Retrofit;

/**
 * Created by Josh Laird on 22/04/2017.
 * <p>
 * Where the magic happens.
 */
@ActivityScope
public class GithubInteractor
{
    private GithubApi githubApi;
    private CacheProviders cacheProviders;
    private MySchedulerProvider mySchedulerProvider;

    @Inject
    public GithubInteractor(Context context, Retrofit retrofit, MySchedulerProvider mySchedulerProvider)
    {
        this.mySchedulerProvider = mySchedulerProvider;

        githubApi = retrofit.create(GithubApi.class);

        cacheProviders = new RxCache.Builder()
                .setMaxMBPersistenceCache(5)
                .persistence(context.getFilesDir(), new GsonSpeaker())
                .using(CacheProviders.class);
    }

    public Observable<GithubResponse> searchGithub(String query, boolean update)
    {
        return cacheProviders.searchGithub(githubApi.searchGithub(query), new DynamicKey(query), new EvictDynamicKey(update))
                .observeOn(mySchedulerProvider.io())
                .subscribeOn(mySchedulerProvider.io());
    }
}

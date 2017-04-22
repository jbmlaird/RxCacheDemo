package bj.rxcachedemo.cache;

import java.util.concurrent.TimeUnit;

import bj.rxcachedemo.json.GithubResponse;
import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.LifeCache;

/**
 * Created by Josh Laird on 22/04/2017.
 */
public interface CacheProviders
{
    @LifeCache(duration = 5, timeUnit = TimeUnit.MINUTES)
    Observable<GithubResponse> searchGithub(Observable<GithubResponse> searchGithubObservable, DynamicKey query, EvictDynamicKey update);
}

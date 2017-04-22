package bj.rxcachedemo.main;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import bj.rxcachedemo.ActivityScope;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Josh Laird on 22/04/2017.
 */
@Module
public class MainModule
{
    private static final String GITHUB_BASE_URL = "https://api.github.com/";
    private MainContract.View mView;

    public MainModule(MainContract.View view)
    {
        mView = view;
    }

    @Provides
    @ActivityScope
    MainContract.View provideMainView()
    {
        return mView;
    }

    @Provides
    @ActivityScope
    Retrofit provideRetrofit()
    {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(GITHUB_BASE_URL)
                .build();
    }
}

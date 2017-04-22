package bj.rxcachedemo;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Josh Laird on 22/04/2017.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent
{
    void inject(App app);

    Context getContext();
}

package bj.rxcachedemo.main;

import bj.rxcachedemo.ActivityScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Josh Laird on 22/04/2017.
 */
@Module
public class MainModule
{
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
}

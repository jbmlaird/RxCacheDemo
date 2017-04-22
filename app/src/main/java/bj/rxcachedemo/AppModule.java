package bj.rxcachedemo;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Josh Laird on 22/04/2017.
 */
@Module
public class AppModule
{
    private Context mContext;

    public AppModule(Context context)
    {
        mContext = context;
    }

    @Provides
    @Singleton
    Context provideApplicationContext()
    {
        return mContext;
    }
}

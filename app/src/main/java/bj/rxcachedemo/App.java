package bj.rxcachedemo;

import android.app.Application;

/**
 * Created by Josh Laird on 22/04/2017.
 */
public class App extends Application
{
    public static AppComponent appComponent;

    @Override
    public void onCreate()
    {
        super.onCreate();
        setupGraph();
    }

    private void setupGraph()
    {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }
}

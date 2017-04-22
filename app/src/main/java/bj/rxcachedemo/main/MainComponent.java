package bj.rxcachedemo.main;

import bj.rxcachedemo.ActivityScope;
import bj.rxcachedemo.AppComponent;
import dagger.Component;

/**
 * Created by Josh Laird on 22/04/2017.
 */
@ActivityScope
@Component(modules = {MainModule.class}, dependencies = {AppComponent.class})
public interface MainComponent
{
    void inject(MainActivity mainActivity);
}

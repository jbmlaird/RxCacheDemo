package bj.rxcachedemo.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import bj.rxcachedemo.App;
import bj.rxcachedemo.AppComponent;

/**
 * Created by Josh Laird on 22/04/2017.
 */
public abstract class BaseActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setupComponent(App.appComponent);
    }

    public abstract void setupComponent(AppComponent appComponent);

    public abstract void displayProgressBar(boolean display);
}

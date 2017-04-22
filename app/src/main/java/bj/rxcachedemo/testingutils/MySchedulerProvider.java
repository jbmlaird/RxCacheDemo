package bj.rxcachedemo.testingutils;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Josh Laird on 22/04/2017.
 * <p>
 * Extend this to unit test Schedulers.
 */
public class MySchedulerProvider implements SchedulerProvider
{
    @Inject
    public MySchedulerProvider()
    {
    }

    @Override
    public Scheduler ui()
    {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler computation()
    {
        return Schedulers.io();
    }

    @Override
    public Scheduler trampoline()
    {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler newThread()
    {
        return Schedulers.newThread();
    }

    @Override
    public Scheduler io()
    {
        return Schedulers.io();
    }
}

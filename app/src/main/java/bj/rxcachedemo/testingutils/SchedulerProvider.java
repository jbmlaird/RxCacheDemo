package bj.rxcachedemo.testingutils;

import io.reactivex.Scheduler;

/**
 * Created by Josh Laird on 22/04/2017.
 */
public interface SchedulerProvider
{
    Scheduler ui();

    Scheduler computation();

    Scheduler trampoline();

    Scheduler newThread();

    Scheduler io();
}


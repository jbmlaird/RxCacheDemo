package bj.rxcachedemo.testingutils;

import javax.inject.Inject;

/**
 * Created by Josh Laird on 22/04/2017.
 * <p>
 * Injected for unit-testing.
 */
public class SystemWrapper
{
    @Inject
    public SystemWrapper()
    {

    }

    public long currentTimeMillis()
    {
        return System.currentTimeMillis();
    }
}

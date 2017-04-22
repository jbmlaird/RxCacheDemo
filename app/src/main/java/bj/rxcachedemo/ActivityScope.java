package bj.rxcachedemo;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Josh Laird on 22/04/2017.
 * <p>
 * Custom scope for Activities.
 */
@Scope
@Retention(RUNTIME)
public @interface ActivityScope
{
}
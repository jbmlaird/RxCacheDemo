package bj.rxcachedemo.main;

import javax.inject.Inject;

import bj.rxcachedemo.ActivityScope;
import bj.rxcachedemo.github.GithubInteractor;
import bj.rxcachedemo.testingutils.MySchedulerProvider;
import bj.rxcachedemo.testingutils.SystemWrapper;

/**
 * Created by Josh Laird on 22/04/2017.
 */
@ActivityScope
public class MainPresenter implements MainContract.Presenter
{
    private MainContract.View mView;
    private GithubInteractor githubInteractor;
    private MySchedulerProvider mySchedulerProvider;
    private SystemWrapper systemWrapper;

    @Inject
    public MainPresenter(MainContract.View mView, GithubInteractor githubInteractor, MySchedulerProvider mySchedulerProvider, SystemWrapper systemWrapper)
    {
        this.mView = mView;
        this.githubInteractor = githubInteractor;
        this.mySchedulerProvider = mySchedulerProvider;
        this.systemWrapper = systemWrapper;
    }

    @Override
    public void searchGithub(String query, boolean update)
    {
        long startTime = systemWrapper.currentTimeMillis();
        mView.displayProgressBar(true);
        githubInteractor.searchGithub(query, update)
                .observeOn(mySchedulerProvider.ui())
                .subscribe(githubResponse ->
                        {
                            mView.displayData(githubResponse, systemWrapper.currentTimeMillis() - startTime);
                            mView.displayGithubText(true);
                        },
                        error ->
                        {
                            error.printStackTrace();
                            mView.displayError(true);
                        });
    }
}

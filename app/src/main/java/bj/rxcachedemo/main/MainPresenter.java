package bj.rxcachedemo.main;

import javax.inject.Inject;

import bj.rxcachedemo.ActivityScope;
import bj.rxcachedemo.github.GithubInteractor;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Josh Laird on 22/04/2017.
 */
@ActivityScope
public class MainPresenter implements MainContract.Presenter
{
    private MainContract.View mView;
    private GithubInteractor githubInteractor;

    @Inject
    public MainPresenter(MainContract.View mView, GithubInteractor githubInteractor)
    {
        this.mView = mView;
        this.githubInteractor = githubInteractor;
    }

    @Override
    public void searchGithub(String query, boolean update)
    {
        long startTime = System.currentTimeMillis();
        mView.disableButtons(true);
        mView.displayError(false);
        mView.displayProgressBar(true);
        mView.displayGithubText(false);
        githubInteractor.searchGithub(query, update)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(githubResponse ->
                        {
                            mView.displayData(githubResponse, System.currentTimeMillis() - startTime);
                            mView.disableButtons(false);
                            mView.displayProgressBar(false);
                            mView.displayGithubText(true);
                        },
                        error ->
                        {
                            error.printStackTrace();
                            mView.displayGithubText(false);
                            mView.disableButtons(false);
                            mView.displayProgressBar(false);
                            mView.displayError(true);
                        });
    }
}

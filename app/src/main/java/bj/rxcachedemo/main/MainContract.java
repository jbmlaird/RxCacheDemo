package bj.rxcachedemo.main;

import bj.rxcachedemo.json.GithubResponse;

/**
 * Created by Josh Laird on 22/04/2017.
 */
public interface MainContract
{
    interface View
    {
        void displayData(GithubResponse githubResponse, long l);

        void displayError(boolean display);

        void displayProgressBar(boolean display);

        void displayGithubText(boolean display);

        void disableButtons(boolean disable);
    }

    interface Presenter
    {
        void searchGithub(String query, boolean update);
    }
}

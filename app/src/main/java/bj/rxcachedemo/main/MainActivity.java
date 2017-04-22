package bj.rxcachedemo.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import bj.rxcachedemo.AppComponent;
import bj.rxcachedemo.R;
import bj.rxcachedemo.common.BaseActivity;
import bj.rxcachedemo.json.GithubResponse;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainContract.View
{
    @Inject MainPresenter presenter;
    @BindView(R.id.btnAndroid) Button btnAndroid;
    @BindView(R.id.btnAndroidRefresh) Button btnAndroidRefresh;
    @BindView(R.id.btnIos) Button btnIos;
    @BindView(R.id.btnIosRefresh) Button btnIosRefresh;
    @BindView(R.id.tvGithubResponse) TextView tvGithubResponse;
    @BindView(R.id.tvError) TextView tvError;
    @BindView(R.id.pbMain) ProgressBar pbMain;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void setupComponent(AppComponent appComponent)
    {
        MainComponent component = DaggerMainComponent.builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build();

        component.inject(this);
    }

    @OnClick(R.id.btnAndroid)
    public void androidPressed()
    {
        presenter.searchGithub("android", false);
    }

    @OnClick(R.id.btnAndroidRefresh)
    public void androidRefreshPressed()
    {
        presenter.searchGithub("android", true);
    }

    @OnClick(R.id.btnIos)
    public void iosPressed()
    {
        presenter.searchGithub("ios", false);
    }

    @OnClick(R.id.btnIosRefresh)
    public void iosRefreshPressed()
    {
        presenter.searchGithub("ios", true);
    }

    @Override
    public void displayData(GithubResponse githubResponse, long responseTime)
    {
        tvGithubResponse.setText("Number of queries: " + String.valueOf(githubResponse.getTotalCount()) + "\nRequest time: " + String.valueOf(responseTime) + "ms");
    }

    @Override
    public void displayError(boolean display)
    {
        if (display)
        {
            tvError.setVisibility(View.VISIBLE);
            displayGithubText(false);
            disableButtons(false);
            displayProgressBar(false);
        }
        else
            tvError.setVisibility(View.GONE);
    }

    @Override
    public void displayProgressBar(boolean display)
    {
        if (display)
        {
            pbMain.setVisibility(View.VISIBLE);
            displayError(false);
            displayGithubText(false);
            disableButtons(true);
        }
        else
            pbMain.setVisibility(View.GONE);
    }

    @Override
    public void displayGithubText(boolean display)
    {
        if (display)
        {
            tvGithubResponse.setVisibility(View.VISIBLE);
            disableButtons(false);
            displayProgressBar(false);
        }
        else
            tvGithubResponse.setVisibility(View.GONE);
    }

    @Override
    public void disableButtons(boolean disable)
    {
        btnAndroid.setEnabled(!disable);
        btnAndroidRefresh.setEnabled(!disable);
        btnIos.setEnabled(!disable);
        btnIosRefresh.setEnabled(!disable);
    }
}

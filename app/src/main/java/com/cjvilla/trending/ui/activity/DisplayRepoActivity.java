package com.cjvilla.trending.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cjvilla.trending.R;
import com.cjvilla.trending.com.cjvilla.trending.ui.presenter.Contract;
import com.cjvilla.trending.com.cjvilla.trending.ui.presenter.DisplayRepoPresenter;
import com.cjvilla.trending.databinding.ActivityRepoBinding;
import com.cjvilla.trending.model.GithubRepo;
import com.cjvilla.trending.model.GithubTrending;

import org.parceler.Parcels;

public class DisplayRepoActivity extends BaseTrendingActivity implements Contract.RepoView {
	private static final String KEY_TRENDING="trending";
	private ActivityRepoBinding binding;
	private DisplayRepoPresenter displayRepoPresenter;

	public static void navigate(AppCompatActivity activity, View transitionImage, GithubTrending trending) {
		Intent intent = new Intent(activity, DisplayRepoActivity.class);
		intent.putExtra(KEY_TRENDING, Parcels.wrap(trending));
		ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage,
				activity.getString(R.string.transition));
		ActivityCompat.startActivity(activity, intent, options.toBundle());
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		binding = DataBindingUtil.setContentView(this, R.layout.activity_repo);
		super.onCreate(savedInstanceState);
		createToolbar(true);
		showTitle(null);
		displayRepoPresenter=new DisplayRepoPresenter();
		displayRepoPresenter.attach(this);
		GithubTrending trending=Parcels.unwrap(getIntent().getParcelableExtra(KEY_TRENDING));
		binding.progress.setVisibility(View.VISIBLE);
		displayRepoPresenter.loadRepo(trending.getAuthor(),trending.getName());
	}

	@Override
	public void displayRepo(GithubRepo repo) {
		binding.progress.setVisibility(View.GONE);
		binding.setViewModel(repo);
		binding.setOwnerModel(repo.getOwner());
	}
}

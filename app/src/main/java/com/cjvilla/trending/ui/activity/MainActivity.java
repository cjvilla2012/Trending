package com.cjvilla.trending.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.cjvilla.trending.R;
import com.cjvilla.trending.com.cjvilla.trending.ui.presenter.Contract;
import com.cjvilla.trending.com.cjvilla.trending.ui.presenter.MainActivityPresenter;
import com.cjvilla.trending.databinding.ActivityMainBinding;
import com.cjvilla.trending.databinding.ViewTrendingBinding;
import com.cjvilla.trending.model.GithubTrending;
import com.cjvilla.trending.model.VMHandlers;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.databinding.BindableItem;

import java.util.List;

/** Displays a list of trending repos as a 2-column Grid.*/
public class MainActivity extends BaseTrendingActivity implements Contract.TrendingView {
	protected ActivityMainBinding binding;
	protected GroupAdapter groupAdapter = new GroupAdapter();
	private MainActivityPresenter presenter=new MainActivityPresenter();

	@Override
	public void displayTrending(List<GithubTrending> items) {
		showProgress(false);
		initRecyclerView(items);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
		super.onCreate(savedInstanceState);
		createToolbar(false);
		onNewIntent(getIntent());
	}

	@Override
	public void onNewIntent(Intent intent) {
		showProgress(true);
		presenter.attach(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		showProgress(false);
		presenter.detach();
	}

	private void initRecyclerView(List<GithubTrending> items) {
		binding.recycler.setAdapter(groupAdapter);
		binding.recycler.setLayoutManager(new GridLayoutManager(this, 2));
		groupAdapter.clear();
		for (GithubTrending trending:items) {
			groupAdapter.add(new TrendingItem(trending));
		}
	}

	protected void showProgress(boolean show) {
		if (show) {
			binding.recycler.setVisibility(View.GONE);
			binding.progress.setVisibility(View.VISIBLE);
		} else {
			binding.recycler.setVisibility(View.VISIBLE);
			binding.progress.setVisibility(View.GONE);
		}
	}

	private class TrendingItem extends BindableItem<ViewTrendingBinding> {
		private GithubTrending githubTrending;

		private TrendingItem(GithubTrending trending) {
			this.githubTrending=trending;
		}

		@Override
		public void bind(@NonNull ViewTrendingBinding viewHolder, int position) {
			viewHolder.setViewModel(githubTrending);
			viewHolder.setViewHandler(new VMHandlers(MainActivity.this));
		}

		@Override
		public int getLayout() {
			return R.layout.view_trending;
		}
	}
}


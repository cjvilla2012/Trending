package com.cjvilla.trending.com.cjvilla.trending.ui.presenter;

import com.cjvilla.trending.net.repository.ServerRepository;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

public class MainActivityPresenter implements Contract.TrendingPresenter {
	protected CompositeDisposable compositeDisposable;
	private ServerRepository serverRepository;
	private Contract.TrendingView trendingView;

	@Override
	public void attach(BaseView view) {
		this.trendingView = (Contract.TrendingView) view;
		compositeDisposable=new CompositeDisposable();
		serverRepository = ServerRepository.instance();
		loadTrending();
	}

	public void detach() {
		if (compositeDisposable!=null) {
			compositeDisposable.clear();
			compositeDisposable=null;
		}
	}

	@Override
	public void loadTrending() {
		compositeDisposable.add(serverRepository.getTrending()
				.subscribe(
						trendings -> {
							trendingView.displayTrending(trendings);

						},
						throwable -> {
							Timber.e(throwable);
						}
				));
	}
}

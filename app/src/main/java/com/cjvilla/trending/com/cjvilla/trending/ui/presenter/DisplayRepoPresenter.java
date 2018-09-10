package com.cjvilla.trending.com.cjvilla.trending.ui.presenter;

import com.cjvilla.trending.net.repository.ServerRepository;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

public class DisplayRepoPresenter implements Contract.RepoPresenter {
	private CompositeDisposable compositeDisposable;
	private ServerRepository serverRepository;
	private Contract.RepoView repoView;

	@Override
	public void attach(BaseView view) {
		this.repoView = (Contract.RepoView) view;
		serverRepository = ServerRepository.instance();
	}

	@Override
	public void detach() {
		if (compositeDisposable!=null) {
			compositeDisposable.clear();
			compositeDisposable = null;
		}
	}

	@Override
	public void loadRepo(String owner,String name) {
		if (compositeDisposable == null) {
			compositeDisposable = new CompositeDisposable();
		}
		compositeDisposable.add(serverRepository.getRepo(owner,name)
				.subscribe(
						repo -> {
							repoView.displayRepo(repo);

						},
						throwable -> {
							Timber.e(throwable);
						}
				));
	}

}

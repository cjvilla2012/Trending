package com.cjvilla.trending.com.cjvilla.trending.ui.presenter;

import com.cjvilla.trending.net.repository.ServerRepository;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

public class DisplayRepoPresenter implements Contract.RepoPresenter {
	private CompositeDisposable compositeDisposable;
	private ServerRepository serverRepository;
	private Contract.RepoView repoView;

	public void attach(Contract.RepoView view) {
		this.repoView=view;
		compositeDisposable=new CompositeDisposable();
		serverRepository = ServerRepository.instance();
	}

	public void detach() {
		if (compositeDisposable!=null) {
			compositeDisposable.clear();
		}
	}

	@Override
	public void loadRepo(String owner,String name) {
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

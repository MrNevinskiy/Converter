package com.hw.converter.mvp.presenter;

import com.hw.converter.ConverterApp;
import com.hw.converter.mvp.model.Model;
import com.hw.converter.mvp.view.MainView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class Presenter extends MvpPresenter<MainView> {

    private Router router = ConverterApp.getApplication().getRouter();
    private Model model = new Model();
    private Disposable disposable = null;

    public void startConvert(String path) {
        model.finishConverter(path)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observable);
    }

    final Observer<Object> observable = new Observer<Object>() {

        @Override
        public void onSubscribe(@NonNull Disposable d) {
            disposable = d;
        }

        @Override
        public void onNext(@NonNull Object o) {
            getViewState().startConvert(o, "OK");
        }

        @Override
        public void onError(@NonNull Throwable e) {
        }

        @Override
        public void onComplete() {
        }
    };

    public void cancel() {
        if (disposable != null) {
            disposable.dispose();
            getViewState().cancelConvert("canceled");
        }
    }

    public void backClicked() {
        router.exit();
    }
}

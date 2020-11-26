package com.hw.converter.mvp.presenter;

import com.hw.converter.ConverterApp;
import com.hw.converter.mvp.model.Model;
import com.hw.converter.mvp.view.MainView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class Presenter extends MvpPresenter<MainView> {

    private Router router = ConverterApp.getApplication().getRouter();
    private Model model = new Model();
    private Disposable disposable = null;

    public void startConvert(String path) {
        disposable = model.finishConverter(path)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> getViewState().startConvert(o, "OK"));
    }

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

package com.hw.converter.mvp.presenter;

import com.hw.converter.ConverterApp;
import com.hw.converter.mvp.model.Model;
import com.hw.converter.mvp.view.MainView;

import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class Presenter extends MvpPresenter<MainView> {

    private Router router = ConverterApp.getApplication().getRouter();
    private Model model = new Model();

    public void start(String path){
        getViewState().startConvert((Object[]) model.startConverter(path), "finish");
    }
    public void cancel(){
        getViewState().cancelConvert((Object[]) model.cancelConverter(), "cancel");
    }

    public void backClicked() {
        router.exit();
    }
}

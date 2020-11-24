package com.hw.converter.mvp.view;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;

@AddToEndSingle
public interface MainView extends MvpView {
    void startConvert(Object object, String status);
    void cancelConvert(String status);
}

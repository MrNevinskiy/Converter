package com.hw.converter;

import android.app.Application;

import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

public class ConverterApp extends Application {
    public static ConverterApp INSTANCE;

    private Cicerone<Router> cicerone;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        initCicerone();
    }

    public static ConverterApp getApplication() {
        return INSTANCE;
    }

    private void  initCicerone(){
        cicerone = Cicerone.create();
    }

    public Router getRouter(){
        return cicerone.getRouter();
    }

    public NavigatorHolder getNavigatorHolder(){
        return cicerone.getNavigatorHolder();
    }
}

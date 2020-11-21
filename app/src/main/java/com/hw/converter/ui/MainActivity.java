package com.hw.converter.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hw.converter.ConverterApp;
import com.hw.converter.R;
import com.hw.converter.mvp.presenter.Presenter;
import com.hw.converter.mvp.view.MainView;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;

public class MainActivity extends MvpAppCompatActivity implements MainView, View.OnClickListener {

    @InjectPresenter
    Presenter presenter;

    private NavigatorHolder navigatorHolder = ConverterApp.getApplication().getNavigatorHolder();
    private Navigator navigator = new SupportAppNavigator(this, getSupportFragmentManager(), R.id.container);

    private Button start;
    private Button cancel;
    private TextView info_tv;
    private ImageView before_i;
    private ImageView after_i;

    private final String PATH = "/sdcard/DCIM/Camera/IMG_20201117_163205.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.start_bt);
        cancel = findViewById(R.id.cancel_bt);
        info_tv = findViewById(R.id.status_tv);
        before_i = findViewById(R.id.before_im);
        after_i = findViewById(R.id.after_im);

        start.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        navigatorHolder.setNavigator(navigator);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_bt:
                presenter.start(PATH);
                break;
            case R.id.cancel_bt:
                presenter.cancel();
                break;
        }
    }

    @Override
    public void startConvert(Object[] object, String status) {
        after_i.setImageBitmap((Bitmap) object[1]);
        before_i.setImageBitmap((Bitmap) object[0]);
        info_tv.setText(status);
    }

    @Override
    public void cancelConvert(Object[] object, String status) {
        after_i.setImageBitmap((Bitmap) object[1]);
        before_i.setImageBitmap((Bitmap) object[0]);
        info_tv.setText(status);
    }

    @Override
    public void onBackPressed() {
        presenter.backClicked();
    }
}
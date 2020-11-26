package com.hw.converter.mvp.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import io.reactivex.rxjava3.core.Single;

public class Model {

    public Object before = null;
    public Object after = null;


    public Single<Object> finishConverter(String path){
        try {
            File file = new File("/sdcard/Converter/");
            if (!file.exists()) {
                file.mkdirs();
            }
            FileInputStream fileInputStream = new FileInputStream(path);
            Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
            before = bitmap;
            FileOutputStream fileOutputStream = new FileOutputStream(file + "/new.png");
            bitmap.compress(Bitmap.CompressFormat.PNG, 50, fileOutputStream);
            after = bitmap;
            fileInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Single.just(after);
    }
}

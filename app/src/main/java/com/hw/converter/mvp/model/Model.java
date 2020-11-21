package com.hw.converter.mvp.model;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


public class Model {

    public Object before = null;
    public Object after = null;

    public Object startConverter(String path) {
        try {
            File file = new File("/sdcard/Converter/");
            if (!file.exists()) {
                file.mkdirs();
            }
            FileInputStream fileInputStream = new FileInputStream(path);
            Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
            before = bitmap;
            FileOutputStream fileOutputStream = new FileOutputStream(file + "/new.png");
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            after = bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new Object[]{before,after};
    }

    public Object cancelConverter() {
        return new Object[]{before,after};
    }


}

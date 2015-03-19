package com.lp.example.facem;

import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.SimpleCursorAdapter;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hp on 2015/3/3.
 */
public class MyPacelable implements Parcelable {


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public void setThread() {
        final ExecutorService executorService = Executors.newCachedThreadPool();
        AsyncTask asyncTask;
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(executorService.toString());
            }
        });
    }

}

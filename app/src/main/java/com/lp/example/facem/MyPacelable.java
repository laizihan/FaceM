package com.lp.example.facem;

import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.Window;
import android.view.WindowManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hp on 2015/3/3.
 */
public class MyPacelable implements Parcelable {

    public String husband;
    public String wife;
    public Integer[] a;
    private Window window;
    private WindowManager windowManager;
    



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString("laizihan");
        dest.writeString("llp");
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



    public static final Parcelable.Creator<MyPacelable> CREATOR = new Creator<MyPacelable>() {
        @Override
        public MyPacelable createFromParcel(Parcel source) {
            MyPacelable myPacelable = new MyPacelable();
            myPacelable.husband = source.readString();
            myPacelable.wife = source.readString();
            return myPacelable;
        }

        @Override
        public MyPacelable[] newArray(int size) {
            return new MyPacelable[size];
        }
    };




}

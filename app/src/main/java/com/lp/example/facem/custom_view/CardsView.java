package com.lp.example.facem.custom_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

import com.lp.example.facem.R;

/**
 * Created by laizihan on 5/26/15.
 */
public class CardsView extends View {

    private Bitmap[] bitmaps = new Bitmap[3];

    private int[] resIDs = new int[]{R.drawable.beauty, R.drawable.test, R.drawable.beauty};

    public CardsView(Context context) {
        super(context);
        Bitmap bm = null;
        for (int i = 0; i < resIDs.length; i++) {
            bm = BitmapFactory.decodeResource(getResources(), resIDs[i]);
            bitmaps[i] = Bitmap.createScaledBitmap(bm, 400, 600, false);
        }
        setBackgroundColor(0xffffff);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(20,120);
        for(int i =0;i<bitmaps.length;i++){

            canvas.translate(120,0);
            if (i <bitmaps.length-1){
                canvas.clipRect(0,0,120,bitmaps[i].getHeight());
            }
            canvas.drawBitmap(bitmaps[i],0, 0,null);
            canvas.restore();
        }
        canvas.restore();


    }


}

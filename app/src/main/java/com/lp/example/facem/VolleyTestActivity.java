package com.lp.example.facem;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class VolleyTestActivity extends AppCompatActivity {

    private RequestQueue mRequstQueue;
    private ImageView mImageView;
    private static String URL = "http://fc.topitme.com/c/2d/9b/11192749737ed9b2dco.jpg";
//    private static String URL = "http://pic.nipic.com/2008-05-07/20085722191339_2.jpg";
    private Button button;
    private ImageRequest imageRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_test);
        button = (Button) findViewById(R.id.hello_world);
        mImageView = (ImageView) findViewById(R.id.iv_full_image);
        mRequstQueue = Volley.newRequestQueue(this);
        imageRequest = new ImageRequest(URL, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                mImageView.setImageBitmap(bitmap);
            }
        }, 0, 0, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("volley", volleyError.getMessage());
            }
        });
//        final ImageLoader imageLoader = new ImageLoader(mRequstQueue,new BitmapCache());
        final ImageLoader imageLoader = new ImageLoader(mRequstQueue, new BitmapCache());
        final ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(mImageView, R.drawable.ic_action_search, R.drawable.ic_launcher);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageLoader.get(URL,imageListener);
            }
        });
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_volley_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            pareInt("3");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public int pareInt(@NonNull String numString){
        return Integer.parseInt(numString);
    }
}

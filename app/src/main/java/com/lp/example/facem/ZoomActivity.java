package com.lp.example.facem;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ZoomActivity extends ActionBarActivity {
    public static Boolean opened = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);
        Button selectImage = (Button) findViewById(R.id.select_image);
        final ViewGroup container = (ViewGroup) findViewById(R.id.ll_origin_image);
        final ImageButton thumbtn = (ImageButton) findViewById(R.id.thumb_Button);
        thumbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zoomImageFromThumb(v, R.drawable.beauty);
            }
        });
        final Button button = new Button(ZoomActivity.this);
        button.setLayoutParams(new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        button.setText("新添加的，你咬我");


        selectImage.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if (!opened) {
                    container.addView(button);
                    opened = true;
                } else {
                    container.removeView(button);
                    opened = false;
                }
            }
        });
//        button.setElevation(2.0f);
        Button volleyTestButton = new Button(this);
        volleyTestButton.setLayoutParams(new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        volleyTestButton.setText("走过去volley");
        volleyTestButton.setGravity(Gravity.BOTTOM);
        volleyTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ZoomActivity.this,VolleyTestActivity.class));
            }
        });
        container.addView(volleyTestButton);


    }


    private void zoomImageFromThumb(final View thumview, int res) {
        final ImageView expandedView = (ImageView) findViewById(R.id.expanded_image);
        expandedView.setImageResource(res);
        final Rect startBound = new Rect();
        final Rect finalBound = new Rect();
        final Point globalOffset = new Point();
        thumview.getGlobalVisibleRect(startBound);
        findViewById(R.id.container).getGlobalVisibleRect(finalBound, globalOffset);
        startBound.offset(-globalOffset.x, -globalOffset.y);
        finalBound.offset(-globalOffset.x, -globalOffset.y);
        final float startScale;
        if ((float) (finalBound.width() / finalBound.height()) > (float) (startBound.width() / startBound.height())) {
            startScale = (float) startBound.height() / finalBound.height();
            float startWidth = startScale * finalBound.width();
            float deltaWidth = (startWidth - startBound.width()) / 2;
            startBound.left -= deltaWidth;
            startBound.right += deltaWidth;

        } else {
            startScale = (float) startBound.width() / finalBound.width();
            float startHeight = startScale * finalBound.height();
            float deltaHeight = (startHeight - startBound.height()) / 2;
            startBound.top -= deltaHeight;
            startBound.bottom += deltaHeight;

        }
//        thumview.setAlpha(0f);
        thumview.setVisibility(View.VISIBLE);
        expandedView.setVisibility(View.VISIBLE);

        expandedView.setPivotX(0f);
        expandedView.setPivotY(0f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(expandedView, View.X, startBound.left, finalBound.left))
                .with(ObjectAnimator.ofFloat(expandedView, View.Y, startBound.top, finalBound.top))
                .with(ObjectAnimator.ofFloat(expandedView, View.SCALE_X, startScale, 1f))
                .with(ObjectAnimator.ofFloat(expandedView, View.SCALE_Y, startScale, 1f));
        animatorSet.setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime));
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());

        animatorSet.start();

        expandedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(ObjectAnimator.ofFloat(expandedView, View.X, startBound.left))
                        .with(ObjectAnimator.ofFloat(expandedView, View.Y, startBound.top))
                        .with(ObjectAnimator.ofFloat(expandedView, View.SCALE_X, startScale))
                        .with(ObjectAnimator.ofFloat(expandedView, View.SCALE_Y, startScale));
                animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                animatorSet.setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime));
//                animatorSet.setDuration(60000);
                animatorSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
//                        thumview.setAlpha(1f);
                        thumview.setVisibility(View.VISIBLE);
                        expandedView.setVisibility(View.GONE);
                    }
                });
                animatorSet.start();

            }
        });
        expandedView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ZoomActivity.this);
                builder.setTitle("保存");
                builder.show();
                return true;
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_zoom, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

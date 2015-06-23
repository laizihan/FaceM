package com.lp.example.facem;

import android.animation.Animator;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lp.example.facem.remote.MyService;


public class MainActivity extends AppCompatActivity {



    public ObjectAnimator animator;

    protected ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IMyService myService = IMyService.Stub.asInterface(service);
            try {
                System.out.println(myService.plus(2, 5));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView mWebview = (WebView) findViewById(R.id.web_view);
        mWebview.getSettings().setJavaScriptEnabled(true);
        Button next_btn = (Button) findViewById(R.id.next_activity);
        mWebview.loadUrl("http://www.baidu.com");
        bindService(new Intent(this, MyService.class),mServiceConnection,BIND_AUTO_CREATE);bindService(new Intent(this, MyService.class),mServiceConnection,BIND_AUTO_CREATE);
        final AppCompatButton button = (AppCompatButton) findViewById(R.id.btn_diao);
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                Log.e("btn", "onTouch excute on btn " + event.getAction());
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e("btn", "ontouch down");
//                        return false;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e("btn", "ontouch move");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e("btn", "ontouch up");
//                        startActivity(new Intent(MainActivity.this, PhotoWallActivity.class));
                        break;

                }
                return true;
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                animator.cancel();
                startActivity(new Intent(MainActivity.this, ZoomActivity.class));
            }
        });


        ViewGroup view = (RelativeLayout) findViewById(R.id.relative_layout);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"onclick 执行",Toast.LENGTH_SHORT).show();
                Log.e("btn", "onClick excute on btn");
            }
        });


        Button newButton = new Button(this) {

            @Override
            public boolean onTouchEvent(@NonNull MotionEvent event) {
//                int action = event.getAction();
                int action = event.getActionMasked();


                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e("tag", "action down laizihan");
                        return true;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        Log.e("tag", "action 2 fingers down laizihan");
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        Log.e("tag", "action move laizihan");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e("tag", "action up laizhan");
                        break;

                    case MotionEvent.ACTION_POINTER_UP:
                        Log.e("tag", "action up laizhan");
                        break;

                }

                return super.onTouchEvent(event);
            }
        };
        newButton.setText("新按钮新按钮新按钮新按钮");
        newButton.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.addView(newButton);

        newButton.setOnClickListener(new View.OnClickListener() {


            public int count;

            @Override
            public void onClick(View v) {
                 count = 0;
                animator = ObjectAnimator.ofFloat(v, "translationX", 0, 100f);
                animator.setInterpolator(new LinearInterpolator());
                animator.setEvaluator(new FloatEvaluator());
                animator.setDuration(5000);
                animator.addListener(animatorListener);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        count++;
                        Log.e("value", animation.getAnimatedValue().toString()+"--------"+count);
                    }
                });
                animator.start();
      /*          ObjectAnimator alpha = ObjectAnimator.ofFloat(v, "alpha", 0f, 0.1f, 0.5f, 1f, 0.5f, 0.3f);
                alpha.setInterpolator(new AccelerateDecelerateInterpolator());
                alpha.setEvaluator(new FloatEvaluator());
                alpha.setDuration(2000);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(animator).with(alpha);
                animatorSet.start();*/

            }
        });



    }

    private Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
            Log.e("anim", "anim start");
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            Log.e("anim", "anim end");
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            Log.e("anim", "anim cancel");
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
            Log.e("anim", "anim repeat");

        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            startActivity(new Intent(this,PhotoWallActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);



    }
}

package com.lp.example.facem;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    private IMyService myService;
    private WebView mWebview;

    protected ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myService = IMyService.Stub.asInterface(service);
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
        mWebview = (WebView) findViewById(R.id.web_view);
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.loadUrl("http://www.baidu.com");
//        bindService(new Intent(this, MyService.class),mServiceConnection,BIND_AUTO_CREATE);
        Button button  = (Button) findViewById(R.id.btn_diao);
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("btn","onTouch excute on btn "+event.getAction());
                return false;
            }
        });




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("btn", "onClick excute on btn");
            }
        });

       Button newButton =  new Button(this){
           @Override
           public boolean onTouchEvent(MotionEvent event) {
               int action = event.getAction();
               switch (action){
                   case MotionEvent.ACTION_DOWN:
                       Log.e("tag","action down laizihan");
                       break;

               }
               return super.onTouchEvent(event);
           }
       };

    }


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

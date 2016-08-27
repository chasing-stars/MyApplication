package com.example.edward.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.edward1.MyService;


public class MainActivity extends ActionBarActivity {

    private static final int ssss = 277;


  boolean change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Drawable d = ContextCompat.getDrawable(this,
                R.drawable.btn_szc_per);
        final ImageView iv =(ImageView) findViewById(R.id.imageView);
       button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change =!change;
                Drawable mDrawable = DrawableCompat.wrap(d.mutate());
                if(change){
                    DrawableCompat.setTint(mDrawable, Color.RED);
                }else {
                    DrawableCompat.setTintList(mDrawable, null);
                }
                iv.setImageDrawable(mDrawable);

                Intent intent =new Intent(MainActivity.this,MyService.class);
                bindService(intent,conn, Context.BIND_AUTO_CREATE);
            //    Log.i("fuck", "onClick:button ");
            }
        });

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBound){
                    myService.tearDown();
                   unbindService(conn);
            //        Log.i("fuck", "onClick");
                }
            }
        });

				/*
				 * !!!使用proguard 导致 setTint失效!!!!
				 * api调用先后顺序（if not, when use Proguard Tint will dosen't work）
				 * https://developer.android.com/reference/android/support
				 * /v4/graphics/drawable/DrawableCompat.html
				 */
    }


    Button button;
    boolean mBound;
    MyService myService;
    ServiceConnection conn =new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBound=true;
            MyService.LocalBinder binder = ( MyService.LocalBinder) service;
            myService =binder.getService();
            myService.startUp();
         //   Log.i("fuck", "onServiceConnected");
       //     button.setText("has binded");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound=false;
          //  button.setText("BIND SERVICE");
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

//        View view =null;
//        switch (view.getId()){
//            case '≌':
//                break;
//            case '⌒':
//                break;
//            case '∪':
//                break;
//        }
        
        return true;
    }


    ///////////////////////////////////////////////////////////////////////////
    // 寡人
    ///////////////////////////////////////////////////////////////////////////
    
   /**
    * Description: 
    * Company :福州飞翔音乐电子科技有限公司
    * Owner: Edward <1514505790@qq.com>
    *
    *
    */

    /**
     *
     * @param item
     * @return
     */
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

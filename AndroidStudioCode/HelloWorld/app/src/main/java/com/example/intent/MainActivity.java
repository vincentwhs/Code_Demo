package com.example.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.activitylifecycle.LifeCycleActivity;
import com.example.broadcast.BroadcastDemo1Activity;
import com.example.broadcast.BroadcastLogin2Activity;
import com.example.broadcast.MyBroastcastActivity;
import com.example.ctrl.listview.ListViewActivity;
import com.example.ctrl.recyclerview.ChatUiActivity;
import com.example.ctrl.recyclerview.RecyclerViewActivity;
import com.example.datapersistent.FilePersistenceMainActivity;
import com.example.fragment.NewsActivity;
import com.example.fragment.SimpleFragmentActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn_intent = (Button) findViewById(R.id.btn_intent);
        btn_intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    public void myclick(View v){
        switch (v.getId()){
            case R.id.btn_intent:
                tesIntent();
                break;
            case R.id.btn_test_life_cycle:
                //intent传递数据
                Intent intent  = new Intent(MainActivity.this, LifeCycleActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_test_ctrl_listview:
                ListViewActivity.actionStart(MainActivity.this);
                break;
            case R.id.btn_test_ctrl_recyclerview:
                RecyclerViewActivity.actionStart(MainActivity.this);
                break;
            case R.id.btn_test_ctrl_recyclerview_chat:
                ChatUiActivity.actionStart(MainActivity.this);
                break;
            case R.id.btn_fragment_1:
                SimpleFragmentActivity.actionStart(MainActivity.this);
                break;
            case R.id.btn_fragment_2:
                NewsActivity.actionStart(MainActivity.this);
                break;
            case R.id.btn_broadcast_1:
                BroadcastDemo1Activity.actionStart(MainActivity.this);
                break;
            case R.id.btn_broadcast_2:
                MyBroastcastActivity.actionStart(MainActivity.this);
                break;
            case R.id.btn_broadcast_offline:
                BroadcastLogin2Activity.actionStart(MainActivity.this);
                break;
            case R.id.btn_stream_file_1:
                FilePersistenceMainActivity.actionStart(MainActivity.this);
                break;
        }
    }

    private void tesIntent() {
    /*Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setData(Uri.parse("http://www.baidu.com"));
    startActivity(intent);*/

                /*Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);*/

        //intent传递数据
        Intent intent  = new Intent(MainActivity.this, SecondActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode)
        {
            case  1:
                if (resultCode == RESULT_OK){
                    Toast.makeText(MainActivity.this, data.getStringExtra("test"), Toast.LENGTH_SHORT).show();
                }
                break;
                default:
                    break;
        }
        //super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item_add:
                Toast.makeText(this,"add",Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_remove:
                Toast.makeText(this,"remove",Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }
}

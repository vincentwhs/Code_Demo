package com.example.datapersistent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.intent.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FilePersistenceMainActivity extends AppCompatActivity {

    public static void actionStart(Context content){
        Intent intent = new Intent(content, FilePersistenceMainActivity.class);
        content.startActivity(intent);
    }
    private EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_persistence_main);

        edit = findViewById(R.id.et_filepersist_edit);

        String str = load();
        if (!TextUtils.isEmpty(str)){
            edit.setText(str);
            edit.setSelection(str.length());

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        String inpuText = edit.getText().toString();
        save(inpuText);
    }

    private String load(){
        FileInputStream in=null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = this.openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null){
                content.append(line);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


        return content.toString();
    }

    private void save(String inputText){

        FileOutputStream outputStream = null;
        BufferedWriter writer = null;

        try {
            outputStream = openFileOutput("data", MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(outputStream));

            writer.write(inputText);

        }catch (Exception e){

        }finally {
            if (writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                writer = null;
            }
        }

    }



    public void mypresclick(View view){
        switch (view.getId()){
            case R.id.btn_sharepres_1:
                //以当前文件名保存
                SharedPreferences share = getApplicationContext().getSharedPreferences("contextpref", Context.MODE_PRIVATE);
                saveSharesPref(share);
                break;
            case R.id.btn_sharepres_2:
                //以当前类名保存
                SharedPreferences share1 = this.getPreferences(Context.MODE_PRIVATE);
                saveSharesPref(share1);
                break;
            case R.id.btn_sharepres_3:
                //com.example.helloworld_preferences.xml 当前程序报名+后缀
                SharedPreferences share2 = PreferenceManager.getDefaultSharedPreferences(this);
                saveSharesPref(share2);
                break;
            case R.id.btn_sharepres_4:
                //com.example.helloworld_preferences.xml 当前程序报名+后缀
                SharedPreferences share4 = this.getSharedPreferences("contextpref",MODE_PRIVATE);
                readSharedPrefs(share4);
                break;
        }
    }

    private void readSharedPrefs(SharedPreferences share){
        String str = share.getString("str", "");
        boolean b = share.getBoolean("isOk", false);
        int i = share.getInt("num", 0);
        Log.d("test", str);
        Log.d("test", b+ "");
        Log.d("test", i + "");
    }

    private void saveSharesPref(SharedPreferences share){
        SharedPreferences.Editor editor = share.edit();
        editor.putBoolean("isOk", true);
        editor.putInt("num",20);
        editor.putString("str","aaaaaa");

        editor.apply();
    }




}

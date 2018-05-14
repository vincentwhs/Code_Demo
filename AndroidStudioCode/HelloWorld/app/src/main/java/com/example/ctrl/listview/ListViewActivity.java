package com.example.ctrl.listview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.intent.R;
import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    /*private String[] data = {"Apple","Banana","Orange","Watermelon","Pear","Grape","Pineapple",
            "Strawberry","Cherry","Mango"};*/
    private List<Fruit> fruitList = new ArrayList<Fruit>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        /*ArrayAdapter<String> adapter = new ArrayAdapter(ListViewActivity.this,android.R.layout.simple_list_item_1,
                data);*/
        initFruits();
        FruitAdapter fruitAdapter = new FruitAdapter(ListViewActivity.this,
                R.layout.list_item_fruit, fruitList);
        ListView lv_demo1 = findViewById(R.id.lv_demo1);
        lv_demo1.setAdapter(fruitAdapter);

        lv_demo1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(ListViewActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initFruits() {
        for (int i=0; i<2; i++){
            Fruit apple = new Fruit("Apple", R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit banana = new Fruit("Banana", R.drawable.banana_pic);
            fruitList.add(banana);
            Fruit orange = new Fruit("Orange", R.drawable.orange_pic);
            fruitList.add(orange);
            Fruit watermelon = new Fruit("Watermelon", R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Fruit pear = new Fruit("Pear", R.drawable.pear_pic);
            fruitList.add(pear);
            Fruit grape = new Fruit("Grape", R.drawable.grape_pic);
            fruitList.add(grape);
            Fruit pineapple = new Fruit("Pineapple", R.drawable.pineapple_pic);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit("Strawberry", R.drawable.strawberry_pic);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit("Cherry", R.drawable.cherry_pic);
            fruitList.add(cherry);
            Fruit mango = new Fruit("Mango", R.drawable.mango_pic);
            fruitList.add(mango);
        }
    }


    public static void actionStart(Context content){
        Intent intent_lv = new Intent(content, ListViewActivity.class);
        content.startActivity(intent_lv);
    }
}

package com.cao.nang.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    GridView gridView;
    ArrayList<Item> gridArray = new ArrayList<>();
    CustomView customView;
    Bitmap studentIcon,mapsIcon,newsIcon,facebookIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentIcon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.hotro);
        mapsIcon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.mapss);
        newsIcon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.tintuc);
        facebookIcon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.fb);
        gridArray.add(new Item(studentIcon, "Course"));
        gridArray.add(new Item(mapsIcon, "Maps"));
        gridArray.add(new Item(newsIcon, "News"));
        gridArray.add(new Item(facebookIcon, "Social"));

        // add to adapter
        gridView = (GridView) findViewById(R.id.gridView);
        customView = new CustomView(this,
                R.layout.row_img, gridArray);
        gridView.setAdapter(customView);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i){
            case 0:
                Intent intent = new Intent(MainActivity.this, Student.class);
                startActivity(intent);
                break;
            case 1:
                Intent intent2 = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent2);
                break;
            case 2:
                Intent intent3 = new Intent(MainActivity.this, News.class);
                startActivity(intent3);
                break;
            case 3:
                Intent intent4 = new Intent(MainActivity.this, Social.class);
                startActivity(intent4);
        }
    }
}

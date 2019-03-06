package com.cao.nang.myapplication;


import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListClass extends AppCompatActivity {
    ListView ltvListClass;
    SQLiteDatabase database;

    ArrayList<String> list = null;
    ArrayAdapter<String> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_class);
        ltvListClass = (ListView) findViewById(R.id.ltvListClass);
        array();
        loadListClass();
    }

    public void array() {
        list = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        ltvListClass.setAdapter(adapter);
        ltvListClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListClass.this, list.get(i), Toast.LENGTH_SHORT).show();
                final String data = list.get(i);
                final int pos = i;
                AlertDialog.Builder b = new AlertDialog.Builder(ListClass.this);
                b.setTitle("Xóa");
                b.setMessage("Bạn có muốn xóa không " + data.toString() + " Không ?");
                b.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        database.delete("Lớp", "stt=?",
                                new String[] { data.toString() });

                        Toast.makeText(ListClass.this, "Xóa thành công",
                                Toast.LENGTH_LONG).show();
                        list.remove(pos);
                        adapter.notifyDataSetChanged();
                    }
                });
                b.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                b.show();
            }
        });
    }
    // Load Data
    public void loadListClass() {
        database = openOrCreateDatabase("htht.db", MODE_PRIVATE, null);
        Cursor c = database.query("Lớp", null, null, null, null, null,
                null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            list.add("  " + c.getString(0) + "        -        "
                    + c.getString(1) + "        -        " + c.getString(2));
            c.moveToNext();
        }
        c.close();
        adapter.notifyDataSetChanged();
    }
}

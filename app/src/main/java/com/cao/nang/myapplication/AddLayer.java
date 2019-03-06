package com.cao.nang.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddLayer extends AppCompatActivity {
    EditText txtClassID, txtClassName;
    Button btnClear, btnCreateClass;

    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_layer);
        init();
    }
    public void init() {
        txtClassID = (EditText) findViewById(R.id.txtClassID);
        txtClassName = (EditText) findViewById(R.id.txtClassName);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnCreateClass = (Button) findViewById(R.id.btnCreateClass);
    }
    public void createClass(View v) {
        database = openOrCreateDatabase("htht.db", MODE_PRIVATE, null);

        ContentValues values = new ContentValues();
        values.put("MãLớp", txtClassID.getText().toString());
        values.put("TênLớp", txtClassName.getText().toString());
        String msg = "";
        if (database.insert("Lớp", null, values) != -1) {
            msg = "Thêm lớp thành công";
        } else {
            msg = "Thêm lớp không thành công";
        }
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        finish();

    }

    public void clear(View v) {
        txtClassID.setText("");
        txtClassName.setText("");
        txtClassID.requestFocus();
    }

}

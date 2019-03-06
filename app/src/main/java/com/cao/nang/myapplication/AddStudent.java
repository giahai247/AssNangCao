package com.cao.nang.myapplication;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddStudent extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spnListClass;
    EditText txtStudentID, txtStudentName;
    ListView ltvListStudent;

    SQLiteDatabase database;

    ArrayList<String> list = null;
    ArrayAdapter<String> adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        init();
        loadDataClass();
    }

    public void init() {
        spnListClass = (Spinner) findViewById(R.id.spnListClass);
        txtStudentID = (EditText) findViewById(R.id.txtStudentID);
        txtStudentName = (EditText) findViewById(R.id.txtStudentName);
        ltvListStudent = (ListView) findViewById(R.id.ltvListStudent);
    }

    // Load data class
    public void loadDataClass() {
        // Array
        database = openOrCreateDatabase("htht.db", MODE_PRIVATE, null);
        list = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnListClass.setAdapter(adapter);
        spnListClass.setOnItemSelectedListener(this);
        // Load
        Cursor c = database.query("Lớp", null, null, null, null, null,
                null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            list.add(c.getString(2));
            c.moveToNext();
        }
        c.close();
        adapter.notifyDataSetChanged();
    }

    // Add Student
    public void addStudent(View v) {
        database = openOrCreateDatabase("htht.db", MODE_PRIVATE, null);
        ContentValues values = new ContentValues();
        values.put("MãSinhViên", txtStudentID.getText().toString());
        values.put("TênSinhViên", txtStudentName.getText().toString());
        values.put("TênLớp", String.valueOf(spnListClass.getSelectedItem()));

        // add
        if (database.insert("SinhViên", null, values) != -1) {
            Toast.makeText(this, "Thêm Sinh Viên Thành Công", Toast.LENGTH_LONG)
                    .show();
            loadListStudent();
            txtStudentID.setText("");
            txtStudentName.setText("");
            txtStudentID.requestFocus();
        }
    }

    public void loadListStudent() {
        // Array
        list = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        ltvListStudent.setAdapter(adapter);

        // load
        list.clear();
        Cursor c = database.query("SinhViên", null, null, null, null, null,
                null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            list.add(" " + c.getString(0) + "   -   " + c.getString(1)
                    + "   -   " + c.getString(2) + "   -   "
                    + c.getString(3));
            c.moveToNext();
        }
        c.close();
        adapter.notifyDataSetChanged();
    }

    // Back
    public void back(View v) {
        finish();
    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
}

package com.cao.nang.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class addMonhoc extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spnListClassmon;

    private EditText edtidmon;
    private EditText edtTenmon;
    private Button btnBackmon;
    private Button btnInsertMon;
    private ListView ltvListStudentmon;





    SQLiteDatabase database;

    ArrayList<String> list = null;
    ArrayAdapter<String> adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_monhoc);
        init();
        loadDataClass();
    }

    public void init() {
        spnListClassmon = (Spinner) findViewById(R.id.spnListClassmon);
        edtidmon = (EditText) findViewById(R.id.edtidmon);
        edtTenmon = (EditText) findViewById(R.id.edtTenmon);
        btnBackmon = (Button) findViewById(R.id.btnBackmon);
        btnBackmon = (Button) findViewById(R.id.btnBackmon);
        btnInsertMon = (Button) findViewById(R.id.btnInsertMon);
        ltvListStudentmon = (ListView) findViewById(R.id.ltvListStudentmon);
    }

    // Load data class
    public void loadDataClass() {
        // Array
        database = openOrCreateDatabase("htht.db", MODE_PRIVATE, null);
        list = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnListClassmon.setAdapter(adapter);
        spnListClassmon.setOnItemSelectedListener(this);
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
    public void addMonhoc(View view) {
        database = openOrCreateDatabase("htht.db",MODE_PRIVATE, null);
        ContentValues values = new ContentValues();
        values.put("idMon", edtidmon.getText().toString());
        values.put("tenmon", edtTenmon.getText().toString());
        values.put("lophoc", String.valueOf(spnListClassmon.getSelectedItem()));

        // add
        if (database.insert("Mon", null, values) != -1) {
            Toast.makeText(this, "Thêm môn học thành công", Toast.LENGTH_LONG)
                    .show();
            loadListStudent();

        }
    }

    public void loadListStudent() {
        // Array
        list = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        ltvListStudentmon.setAdapter(adapter);

        // load
        list.clear();
        Cursor c = database.query("Mon", null, null, null, null, null,
                null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            list.add("mã môn: " + c.getString(0) + " -   tên môn: " + c.getString(1)
                    + " -   lớp: " + c.getString(2) );
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

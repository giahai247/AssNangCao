package com.cao.nang.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Student extends AppCompatActivity {
    SQLiteDatabase database;
    private Button btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);


        createDatabase();
    }
    public void createDatabase() {
        database = openOrCreateDatabase("htht.db", MODE_PRIVATE, null);
        createTable();
    }
    public void createTable() {
        String sqlMon="create table if not exists Mon(idMon text primary key,tenmon text,lophoc text)";
        String sqlClass = "create table if not exists Lớp(stt integer primary key, MãLớp text, TênLớp text)";
        String sqlStudent = "create table if not exists SinhViên(stt integer primary key, MãSinhViên text, TênSinhViên text, TênLớp text)";
        database.execSQL(sqlClass);
        database.execSQL(sqlStudent);
        database.execSQL(sqlMon);
        Toast.makeText(this, "Thêm liệu thành công", Toast.LENGTH_LONG);

    }
    public void reasetDatabase(View v) {
        if (deleteDatabase("htht.db")) {
            Toast.makeText(this, "Xóa dữ liệu thành công", Toast.LENGTH_LONG)
                    .show();
            createDatabase();
        } else {
            Toast.makeText(this, "Xóa dữ liệu không thành công", Toast.LENGTH_LONG)
                    .show();
        }
    }
    public void themLop(View v) {
        Intent intent = new Intent(this, AddLayer.class);
        startActivity(intent);
    }
    public void xemDanhSachLop(View v) {
        Intent intent = new Intent(this, ListClass.class);
        startActivity(intent);
    }
    public void quanLySinhVien(View v) {
        Intent intent = new Intent(this, AddStudent.class);
        startActivity(intent);
    }
    public void addmonhoc(View v) {
        Intent intent = new Intent(this, addMonhoc.class);
        startActivity(intent);
    }
    public void xemmon(View v) {
        Intent intent = new Intent(this, listmonhoc.class);
        startActivity(intent);
    }

    public void thoat(View v) {
        finish();
    }
}


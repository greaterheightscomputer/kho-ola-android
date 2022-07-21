package com.greaterheights.khaola;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BtnUserAccessActivity extends AppCompatActivity implements OnClickListener {

    private Button btnInsertUA, btnUpdateUA, btnDeleteUA, btnSelectAllUA;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.btn_user_access_layout);
        getSupportActionBar().hide();
        initButton();
    }

    private void initButton(){
        btnInsertUA = findViewById(R.id.btn_insert_ua);
        btnUpdateUA = findViewById(R.id.btn_update_ua);
        btnDeleteUA = findViewById(R.id.btn_delete_ua);
        btnSelectAllUA = findViewById(R.id.btn_select_all_ua);

        btnInsertUA.setOnClickListener(this);
        btnUpdateUA.setOnClickListener(this);
        btnDeleteUA.setOnClickListener(this);
        btnSelectAllUA.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_insert_ua:
                Intent intentInsertUser = new Intent(this, InsertUserAccessActivity.class);
                startActivity(intentInsertUser);break;
            case R.id.btn_update_ua:
                Intent intentUpdateUser = new Intent(this, GoUpdateUserAccess.class);
                String emailBtnUser = getIntent().getStringExtra("EmailBtn");
                intentUpdateUser.putExtra("emailBtnUser",emailBtnUser);
                startActivity(intentUpdateUser);break;
            case R.id.btn_delete_ua:
                Intent intentDeleteUser = new Intent(this, GoDeleteUserAccess.class);
                String emailBtnDeleteUser = getIntent().getStringExtra("EmailBtn");
                intentDeleteUser.putExtra("emailBtnDeleteUser",emailBtnDeleteUser);
                startActivity(intentDeleteUser);break;
            case R.id.btn_select_all_ua:
                Intent intentSelecteAllUser = new Intent(this, ShowAllUserAccess.class);
                startActivity(intentSelecteAllUser);break;
        }
    }
}

package com.greaterheights.khaola;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BtnDepartmentActivity extends AppCompatActivity implements OnClickListener {

    private Button btnInsertDept, btnUpdateDept, btnDeleteDept, btnSelectAllDept;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.btn_department_layout);
        getSupportActionBar().hide();

        initButton();
    }

    private void initButton(){
        btnInsertDept = findViewById(R.id.btn_insert_dept);
        btnUpdateDept = findViewById(R.id.btn_update_dept);
        btnDeleteDept = findViewById(R.id.btn_delete_dept);
        btnSelectAllDept = findViewById(R.id.btn_select_all_dept);

        btnInsertDept.setOnClickListener(this);
        btnUpdateDept.setOnClickListener(this);
        btnDeleteDept.setOnClickListener(this);
        btnSelectAllDept.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_insert_dept:
                Intent intentInsertDept = new Intent(this, InsertDepartmentActivity.class);
                startActivity(intentInsertDept); break;
            case R.id.btn_update_dept:
                Intent intentUpdateDept = new Intent(this, GoUpdateDepartment.class);
                startActivity(intentUpdateDept); break;
            case R.id.btn_delete_dept:break;
            case R.id.btn_select_all_dept:break;
        }

    }


}

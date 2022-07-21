package com.greaterheights.khaola;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BtnEmployeeActivity extends AppCompatActivity implements OnClickListener{

    private Button btnInsertEmp, btnSelectEmp, btnUpdateEmp, btnDeleteEmp, btnSelectAllEmp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.btn_employee_layout);
        getSupportActionBar().hide();
        initButton();
    }

    private void initButton(){
        btnInsertEmp = findViewById(R.id.btn_insert_emp);
        btnSelectEmp = findViewById(R.id.btn_select_emp);
        btnUpdateEmp = findViewById(R.id.btn_update_emp);
        btnDeleteEmp = findViewById(R.id.btn_delete_emp);
        btnSelectAllEmp = findViewById(R.id.btn_select_all_emp);

        btnInsertEmp.setOnClickListener(this);
        btnSelectEmp.setOnClickListener(this);
        btnUpdateEmp.setOnClickListener(this);
        btnDeleteEmp.setOnClickListener(this);
        btnSelectAllEmp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_insert_emp:
                Intent intentEmployee = new Intent(this, InsertEmployeeActivity.class);
                startActivity(intentEmployee); break;
            case R.id.btn_update_emp:break;
            case R.id.btn_select_emp:break;
            case R.id.btn_delete_emp:break;
            case R.id.btn_select_all_emp:break;
        }
    }
}

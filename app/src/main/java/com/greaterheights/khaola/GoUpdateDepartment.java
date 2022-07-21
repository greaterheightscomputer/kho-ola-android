package com.greaterheights.khaola;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.greaterheights.khaola.database.DatabaseHelper;
import com.greaterheights.khaola.model.User;

public class GoUpdateDepartment extends AppCompatActivity implements View.OnClickListener {

    private EditText updateDept;
    private Button goDeptBtn;

    DatabaseHelper databaseHelper;
    User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_go_department);

        initGetReference();
        initObject();
        initListener();
    }

    private void initObject() {
        databaseHelper = new DatabaseHelper(this);
        user = new User();
    }

    private void initGetReference() {
        updateDept = findViewById(R.id.update_dept_id);
        goDeptBtn = findViewById(R.id.go_dept_btn);

    }

    private void initListener() {
        goDeptBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == (View) goDeptBtn) {
            Intent intent = new Intent(this, UpdateDepartmentRecord.class);
            intent.putExtra("updateDeptID", updateDept.getText().toString().trim());
            startActivity(intent);
        }

    }
}
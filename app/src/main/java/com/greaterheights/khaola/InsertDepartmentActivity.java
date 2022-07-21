package com.greaterheights.khaola;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.greaterheights.khaola.database.DatabaseHelper;
import com.greaterheights.khaola.model.Department;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertDepartmentActivity extends AppCompatActivity implements OnClickListener {

    private EditText deptName, deptDesc;
    private Button createDeptBtn;
    private String creation_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    DatabaseHelper databaseHelper;
    Department dept;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_department);
        getSupportActionBar().hide();

        initObject();
        initGetReference();
        initListener();

    }

    private void initObject(){
        databaseHelper = new DatabaseHelper(this);
        dept = new Department();
    }

    private void initGetReference(){
        deptName = findViewById(R.id.edit_text_dept_name);
        deptDesc = findViewById(R.id.edit_text_desc);
        createDeptBtn = findViewById(R.id.create_dept_btn);
    }

    private void initListener(){
        createDeptBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == (View) createDeptBtn) {
            validateFields();
        }
    }

        private void validateFields() {
            String deptname = deptName.getText().toString();
            String desc = deptDesc.getText().toString();

            if (deptname.isEmpty() || desc.isEmpty()) {

                if (deptname.isEmpty() && !desc.isEmpty()) {
                    Toast.makeText(this, "Department Name Field is Empty", Toast.LENGTH_SHORT).show();
                }
                if (!deptname.isEmpty() && desc.isEmpty()) {
                    Toast.makeText(this, "Department Description Field is Empty", Toast.LENGTH_SHORT).show();
                }
                if (deptname.isEmpty() && desc.isEmpty()) {
                    Toast.makeText(this, "Field(s) are Empty", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                if(!deptname.isEmpty() && !desc.isEmpty()){
                    sendDataToDatabase();
                    emptyField();
                  //  Toast.makeText(this, "Successfull", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Not Successfull", Toast.LENGTH_SHORT).show();
                }
            }
        }

    private void sendDataToDatabase() {

        String deptname = deptName.getText().toString().trim();
        String desc = deptDesc.getText().toString().trim();
        String created_date = creation_date;

        if(!databaseHelper.checkDept(deptname)){
            dept = new Department(deptname, desc, created_date);
            dept.setName(deptname);
            dept.setDescription(desc);
            dept.setDate(created_date);

            databaseHelper.addDept(dept);

            Toast.makeText(this, "Department Created", Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            //startActivity(intent);
        }
        else{
           Toast.makeText(this, "Department not Created", Toast.LENGTH_SHORT).show();
        }
    }

    private void emptyField(){
        deptName.setText("");
        deptDesc.setText("");
    }
 }

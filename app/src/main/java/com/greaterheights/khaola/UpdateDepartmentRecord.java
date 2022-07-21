package com.greaterheights.khaola;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.greaterheights.khaola.database.DatabaseHelper;
import com.greaterheights.khaola.model.Department;
import com.greaterheights.khaola.model.User;

public class UpdateDepartmentRecord extends AppCompatActivity{

    private EditText dept_id, dept_name, dept_desc;
    private Button updateDeptBtn;

    DatabaseHelper databaseHelper;
    Department dept;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_department);
        //
        dept_id = findViewById(R.id.edit_text_dept_id);
        dept_name = findViewById(R.id.edit_text_dept_name);
        dept_desc = findViewById(R.id.edit_text_desc);
        updateDeptBtn = findViewById(R.id.update_dept_btn);

        String departmentID = getIntent().getStringExtra("updateDeptID");

        databaseHelper = new DatabaseHelper(this);
        Cursor cursor = databaseHelper.fetchDataDept(departmentID);

        if(cursor.moveToFirst()){
            do{
                //if you not need the loop you can remove that
                dept_id.setText(cursor.getString(cursor.getColumnIndex(databaseHelper.ColumnIdDept)));
                dept_name.setText(cursor.getString(cursor.getColumnIndex(databaseHelper.ColumnNameDept)));
                dept_desc.setText(cursor.getString(cursor.getColumnIndex(databaseHelper.ColumnDescDept)));
            }
            while(cursor.moveToNext());
        }cursor.close();

        //anonymous inner class
        updateDeptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String deptid = dept_id.getText().toString().trim();
                int deptedID = Integer.parseInt(deptid);
                String deptname = dept_name.getText().toString().trim();
                String deptdesc =dept_desc.getText().toString().trim();

                Log.d("Department", "deptid1 "+ deptid);
                Log.d("Department", "deptname1 "+ deptname);
                Log.d("Department", "deptdesc1 "+ deptdesc);

                if(databaseHelper.checkDept(deptedID)){

                    dept = new Department(deptedID, deptname, deptdesc);
                    dept.setDept_id(deptedID);
                    dept.setName(deptname);
                    dept.setDescription(deptdesc);
                    Log.d("Department", "deptid "+ deptid);
                    Log.d("Department", "deptname "+ deptname);
                    Log.d("Department", "deptdesc "+ deptdesc);

                    databaseHelper.updateDept(dept);
                    emptyField();
                    Toast.makeText(UpdateDepartmentRecord.this, "Department Record Updated", Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(UpdateRecordActivity.this, HomeAdminActivity.class);
                    //startActivity(intent);
                }
                else{
                    Toast.makeText(UpdateDepartmentRecord.this, "User Record not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void emptyField(){
        dept_id.setText("");
        dept_name.setText("");
        dept_desc.setText("");
    }

}

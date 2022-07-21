package com.greaterheights.khaola;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.greaterheights.khaola.database.DatabaseHelper;
import com.greaterheights.khaola.model.User;

public class UpdateUserAccessRecord extends AppCompatActivity {

    private EditText user_id, email, password;
    private CheckBox usercheck, admincheck;
    private Button updateUserBtn;
    private String role;

    DatabaseHelper databaseHelper;
    User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_user_access);

        user_id = findViewById(R.id.edit_text_user_id);
        email = findViewById(R.id.edit_text_email);
        password = findViewById(R.id.edit_text_password);
        usercheck = findViewById(R.id.checkbok_user);
        admincheck = findViewById(R.id.checkbok_admin);
        updateUserBtn = findViewById(R.id.update_user_access_btn);

        String emailUser = getIntent().getStringExtra("emailUserGo");

        databaseHelper = new DatabaseHelper(this);
        Cursor cursor = databaseHelper.fetchDataUser(emailUser);

        if(cursor.moveToFirst()){
            do{
                //if you not need the loop you can remove that
                user_id.setText(cursor.getString(cursor.getColumnIndex(databaseHelper.ColumnIdUser)));
                email.setText(cursor.getString(cursor.getColumnIndex(databaseHelper.ColumnEmailUser)));
                password.setText(cursor.getString(cursor.getColumnIndex(databaseHelper.ColumnPasswordUser)));
            }
            while(cursor.moveToNext());
        }cursor.close();

        //anonymous inner class
        updateUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkBoxes();

                String userId = user_id.getText().toString().trim();
                String emailId = email.getText().toString().trim();
                String passwd =password.getText().toString().trim();
                String roles = role;

                if(databaseHelper.checkUser(emailId)){

                    User user = new User(userId, emailId, passwd, roles);
                    user.setUser_id(Integer.parseInt(userId));
                    user.setEmail(emailId);
                    user.setPassword(passwd);
                    user.setPrivilege(roles);
                    databaseHelper.updateUser(user);

                    Toast.makeText(UpdateUserAccessRecord.this, "User Record Updated", Toast.LENGTH_SHORT).show();

                    //Intent intent = new Intent(UpdateRecordActivity.this, HomeAdminActivity.class);
                    //startActivity(intent);
                }
                else if(!databaseHelper.checkUser(emailId)){
                    Toast.makeText(UpdateUserAccessRecord.this, "User Record doesn't exist", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(UpdateUserAccessRecord.this, "User Record not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void checkBoxes(){
        if(usercheck.isChecked()){
            role = "User";
        }
        else if(admincheck.isChecked()){
            role = "Admin";
        }
    }



}

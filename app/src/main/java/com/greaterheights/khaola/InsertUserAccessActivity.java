package com.greaterheights.khaola;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.greaterheights.khaola.database.DatabaseHelper;
import com.greaterheights.khaola.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertUserAccessActivity extends AppCompatActivity implements OnClickListener {

    private EditText email, password, confirmpwd;
    private CheckBox usercheck, admincheck;
    private Button createUserBtn;
    private String role;

    private String creation_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    DatabaseHelper databaseHelper;
    User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_user_access);
        //getSupportActionBar().hide();

        initObject();
        initGetReference();
        initListener();
    }

    private void initObject() {
        databaseHelper = new DatabaseHelper(this);
        user = new User();
    }

    private void initGetReference() {
        email = findViewById(R.id.edit_text_email);
        password = findViewById(R.id.edit_text_password);
        confirmpwd = findViewById(R.id.edit_text_confirmpwd);
        usercheck = findViewById(R.id.checkbok_user);
        admincheck = findViewById(R.id.checkbok_admin);
        createUserBtn = findViewById(R.id.create_user_access_btn);
    }

    private void initListener() {
        createUserBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == (View) createUserBtn){
            validateFields();
        }
    }

    private void validateFields() {
        String emails = email.getText().toString().trim();
        String passwords = password.getText().toString().trim();
        String confirmpasswords = confirmpwd.getText().toString().trim();

        if (emails.isEmpty() || passwords.isEmpty() || confirmpasswords.isEmpty()) {

            if (emails.isEmpty() && !passwords.isEmpty() && !confirmpasswords.isEmpty()) {
                Toast.makeText(this, " Email Field is Empty", Toast.LENGTH_SHORT).show();
            }
            if (!emails.isEmpty() && passwords.isEmpty() && !confirmpasswords.isEmpty()) {
                Toast.makeText(this, "Password Field is Empty", Toast.LENGTH_SHORT).show();
            }
            if (!emails.isEmpty() && !passwords.isEmpty() && confirmpasswords.isEmpty()) {
                Toast.makeText(this, "Confirmpassword Field is Empty", Toast.LENGTH_SHORT).show();
            }
            if (emails.isEmpty() && passwords.isEmpty() && confirmpasswords.isEmpty()) {
                Toast.makeText(this, "Field(s) are Empty", Toast.LENGTH_SHORT).show();
            }
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(emails).matches()){
            Toast.makeText(this, "Email Field Pattern Incorrect", Toast.LENGTH_SHORT).show();
        }
        else {
             if(passwords.contentEquals(confirmpasswords)){
                sendDataToDatabase();
                 emptyFields();
                //Toast.makeText(this, "Successfull", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Not Successfull", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sendDataToDatabase(){
        checkBoxes();
        String emails = email.getText().toString().trim();
        String passwords = password.getText().toString().trim();
        String roleTypes = role;
        String createdDate = creation_date;

        if(!databaseHelper.checkUser(emails)){
            User user = new User(emails, passwords, roleTypes, createdDate);

            user.setEmail(emails);
            user.setPassword(passwords);
            user.setPrivilege(roleTypes);
            user.setCreation_date(createdDate);

            databaseHelper.addUser(user);

            Toast.makeText(this, "User Access Created", Toast.LENGTH_SHORT).show();
           // Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            //startActivity(intent);
        }
        else{
            Toast.makeText(this, "User Access Fail", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkBoxes(){
        if(usercheck.isChecked()){
            role = "User";
        }
        else if(admincheck.isChecked()){
            role = "Admin";
        }
    }

    private void emptyFields(){
        email.setText("");
        password.setText("");
        confirmpwd.setText("");
    }

}

package com.greaterheights.khaola;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.greaterheights.khaola.database.DatabaseHelper;

public class LoginActivity extends AppCompatActivity implements OnClickListener {
    private EditText email, password;
    private Button loginBtn;
    private TextView createAccount;
    private String role;

    DatabaseHelper databaseHelper;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        
        initField();
        initListener();
        initObject();
    }

    private void initObject() {
        databaseHelper = new DatabaseHelper(this);
    }

    private void initField(){
        email = findViewById(R.id.edit_text_email);
        password = findViewById(R.id.edit_txt_password);

        email.setText("khadijat@gmail.com");
        password.setText("123");

        loginBtn = findViewById(R.id.login_btn);
        createAccount = findViewById(R.id.create_account);        
    }
    
    private void initListener(){
        loginBtn.setOnClickListener(this);
        createAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn: validateField(); break;
            case R.id.create_account:
                 Intent intentua = new Intent(this, InsertUserAccessActivity.class);
                 startActivity(intentua);break;
        }
    }

    private void validateField() {
        String emails = email.getText().toString().trim();
        String passwords = password.getText().toString().trim();

        if (emails.isEmpty() || passwords.isEmpty()) {
            if (emails.isEmpty() && !passwords.isEmpty()) {
                Toast.makeText(this, "Username Empty", Toast.LENGTH_SHORT).show();
            }
            if (!emails.isEmpty() && passwords.isEmpty()) {
                Toast.makeText(this, "Password Empty", Toast.LENGTH_SHORT).show();
            }
            if (emails.isEmpty() && passwords.isEmpty()) {
                Toast.makeText(this, "Field(s) Empty", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            sendDataToDatabase();
        }
    }

    private void sendDataToDatabase() {

        if (databaseHelper.checkUser(email.getText().toString().trim(), password.getText().toString().trim(),
                role="Admin")) {

            Toast.makeText(this, "Successful Login", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivityAdmin.class);
            intent.putExtra("Email",email.getText().toString().trim());
            startActivity(intent);
        }
        else if (databaseHelper.checkUser(email.getText().toString().trim(), password.getText().toString().trim(),
                role="User")) {
            Toast.makeText(this, "Successful Login", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("Email",email.getText().toString().trim());
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Account Not Found Pls. Register", Toast.LENGTH_SHORT).show();
        }
    }
}

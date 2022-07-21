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

public class GoDeleteUserAccess extends AppCompatActivity implements View.OnClickListener {

    private EditText deleteEmailUser;
    private Button goBtn;

    DatabaseHelper databaseHelper;
    User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_go_user_access);

        initGetReference();
        initObject();
        initListener();
    }

    private void initObject() {
        databaseHelper = new DatabaseHelper(this);
        user = new User();
    }

    private void initGetReference() {
        deleteEmailUser = findViewById(R.id.update_email_user);
        goBtn = findViewById(R.id.go_btn);

        String emailDeleteUser = getIntent().getStringExtra("emailBtnDeleteUser");
        deleteEmailUser.setText(emailDeleteUser);
    }

    private void initListener() {
        goBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == (View) goBtn) {
            Intent intent = new Intent(this, DeleteUserAccessRecord.class);
            intent.putExtra("emailDleteUserGo", deleteEmailUser.getText().toString().trim());
            startActivity(intent);
        }

    }
}

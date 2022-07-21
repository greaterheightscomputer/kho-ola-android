package com.greaterheights.khaola;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.greaterheights.khaola.database.DatabaseHelper;
import com.greaterheights.khaola.model.User;

import java.util.List;

public class ShowAllUserAccess extends AppCompatActivity {

    private TextView userIdDB;

    DatabaseHelper databaseHelper;
    StringBuilder sb;
    User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all_user_access);
        getSupportActionBar().hide();

        initFields();

    }

    private void initFields(){
        userIdDB = findViewById(R.id.userIdDB);
        databaseHelper = new DatabaseHelper(this);
        sb = new StringBuilder();

        List<User> listUser = databaseHelper.getAllUserRecord();
        for (User u : listUser) {
            sb.append(u.getUser_id() + "     |  " + u.getEmail() +"   |   "+ u.getPassword() + "   |   " + u.getPrivilege() +"   |   "+ u.getCreation_date()+"\n");
        }

        //display UI
        userIdDB.setText(sb.toString());
    }

}

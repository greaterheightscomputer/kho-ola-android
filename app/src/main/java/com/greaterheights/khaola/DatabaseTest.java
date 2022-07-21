package com.greaterheights.khaola;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.greaterheights.khaola.database.DatabaseHelper;
import com.greaterheights.khaola.model.User;

import java.util.List;

public class DatabaseTest extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);

        //test of database method using UserDatabaseHelper and StringBuilder object
        DatabaseHelper userDB = new DatabaseHelper(this);
        StringBuilder sb = new StringBuilder();

        //insert a user
        User user = new User("Khadijat","123","ade@yahoo.com", "2018-06-03");
       // Long insertId = userDB.addUser(user);
       // if(insertId > 0){
        //    sb.append("Row inserted! Insert Id: " + insertId + "\n");
        }

        //upddate a user
       // user.setUser_access_id((int)insertId);
        //user.setUsername("Orimadegun Ajoke");
        //int updateCount = userDB.updateUser(user);
        //if(updateCount==1){
          //  sb.append("User updated! Update count: " + updateCount + "\n");
        //}

        //delete a user
       // int deleteCount = userDB.deleteUser(insertId);
        //if(deleteCount == 1){
          //  sb.append("User deleted! Delete count: " + deleteCount + "\n\n");
        //}

        // display all users (id + name)
        //List<UserAccess> usersAr = userDB.getAllUserRecord();
        //for (UserAccess u : usersAr) {
          //  sb.append(u.getUser_access_id() + " | " + u.getUsername() +  " | " + u.getPassword() +" | "+ u.getPrivilege()+ " | "+ u.getCreation_date()+"\n");
        //}

        //display UI
        //TextView dml = (TextView) findViewById(R.id.dmlId);
        //dml.setText(sb.toString());
    //}


    }


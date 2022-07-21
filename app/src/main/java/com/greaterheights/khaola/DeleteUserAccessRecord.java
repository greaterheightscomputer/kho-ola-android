package com.greaterheights.khaola;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.greaterheights.khaola.database.DatabaseHelper;
import com.greaterheights.khaola.model.User;

public class DeleteUserAccessRecord extends AppCompatActivity implements OnClickListener {

    private EditText user_id, email, password;
    private CheckBox usercheck, admincheck;
    private Button deleteUserBtn;

    DatabaseHelper databaseHelper;
    User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_user_access);

        user_id = findViewById(R.id.edit_text_user_id);
        email = findViewById(R.id.edit_text_email);
        password = findViewById(R.id.edit_text_password);
        deleteUserBtn = findViewById(R.id.delete_user_access_btn);
        deleteUserBtn.setOnClickListener(this);

        String emailDelUser = getIntent().getStringExtra("emailDleteUserGo");

        databaseHelper = new DatabaseHelper(this);
        Cursor cursor = databaseHelper.fetchDataUser(emailDelUser);

        if (cursor.moveToFirst()) {
            do {
                //if you not need the loop you can remove that
                user_id.setText(cursor.getString(cursor.getColumnIndex(databaseHelper.ColumnIdUser)));
                email.setText(cursor.getString(cursor.getColumnIndex(databaseHelper.ColumnEmailUser)));
                password.setText(cursor.getString(cursor.getColumnIndex(databaseHelper.ColumnPasswordUser)));
            }
            while (cursor.moveToNext());
        }
        cursor.close();

    }


    @Override
    public void onClick(View v) {

        String emails = email.getText().toString().trim();

        if (v == (View) deleteUserBtn) {
            if (databaseHelper.checkUser(emails)) {

                new AlertDialog.Builder(this).setMessage("Are you sure you want to delete this record?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String userId = user_id.getText().toString().trim();
                                String emailId = email.getText().toString().trim();
                                String passwd = password.getText().toString().trim();

                                user = new User(Integer.parseInt(userId), emailId, passwd);
                                databaseHelper.deleteUser(Integer.parseInt(userId));
                                emptyField();

                                Toast.makeText(DeleteUserAccessRecord.this, "User Deleted", Toast.LENGTH_SHORT).show();

                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(DeleteRecordActivity.this, "Delete Fail", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                }).show();
            }
        }
    }

    private void emptyField(){
        user_id.setText("");
        email.setText("");
        password.setText("");
        }
}

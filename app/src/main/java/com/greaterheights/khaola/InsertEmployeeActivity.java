package com.greaterheights.khaola;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.greaterheights.khaola.database.DatabaseHelper;
import com.greaterheights.khaola.model.Employee;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertEmployeeActivity extends AppCompatActivity implements OnClickListener {

    private EditText employeeId, fullName, address, phone, email, maritalStatus, dept, dateofbirth, hireDate;
    private RadioButton radioFemale, radioMale;
    private ImageView pic;
    private Button createEmpBtn;
    private String gender;

    String emp_id = new SimpleDateFormat("yyyy/MMdd/HHmm/ss").format(new Date());
    String creation_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    DatabaseHelper databaseHelper;
    Employee employee;
    private Bitmap bp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_employee);
        getSupportActionBar().hide();
        
        initObjects();
        initGetReference();
        initListener();
    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(this);
        employee = new Employee();
    }

    private void initGetReference() {
        employeeId = findViewById(R.id.edit_txt_employee_id);
        employeeId.setText("KO/"+emp_id);
        fullName = findViewById(R.id.edit_txt_full_name);
        address = findViewById(R.id.edit_txt_address);
        phone = findViewById(R.id.edit_txt_phone);
        email = findViewById(R.id.edit_txt_email);
        maritalStatus = findViewById(R.id.edit_txt_marital);
        dept = findViewById(R.id.edit_txt_dept);
        dateofbirth = findViewById(R.id.edit_txt_dof);
        hireDate= findViewById(R.id.edit_txt_hdate);
        radioFemale = findViewById(R.id.radio_btn_female);
        radioMale = findViewById(R.id.radio_btn_male);
        pic = findViewById(R.id.image_view_id);
        createEmpBtn = findViewById(R.id.create_emp_btn);
    }

    private void initListener() {
        createEmpBtn.setOnClickListener(this);
        pic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.image_view_id:
                if(employeeId.getText().toString().isEmpty() && fullName.getText().toString().isEmpty() && address.getText().toString().isEmpty() &&
                   phone.getText().toString().isEmpty() && email .getText().toString().isEmpty() && maritalStatus.getText().toString().isEmpty() &&
                   dept.getText().toString().isEmpty() && dateofbirth.getText().toString().isEmpty() && hireDate.getText().toString().isEmpty())
                {
                    Toast.makeText(this, "Fields can't be empty", Toast.LENGTH_SHORT).show();
                }else{
                    selectImage();
                } break;

            case R.id.create_emp_btn:validateFields(); break;
        }
    }

    //selectImage() make the imageView btn to just access to your phone picture gallery
    private void selectImage() {
        Intent photoPickerIntent = new Intent (Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 2);
    }
    //this method is one of the method in FragmentActivity class.
    // Dispatch incoming result to the correct fragment.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case 2:
                if(resultCode == RESULT_OK){
                    Uri choosenImage = data.getData();
                    if(choosenImage !=null){
                        bp = decodeUri(choosenImage, 400);
                        pic.setImageBitmap(bp);
                    }
                }
        }
    }
//convert and resize our image to 400dp for faster uploading our images to DB
    private Bitmap decodeUri(Uri selectedImage, int REQUIRED_SIZE) {
        try{
            //decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);
            //the new size we want to scale to
            // final int REQUIRED_SIZE= size;
            //Find the correct scale value. It should be the power of 2
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while(true){
                if(width_tmp/2 < REQUIRED_SIZE){
                    break;
                }
                width_tmp /=2;
                height_tmp /=2;
                scale *=2;
            }
            //decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null,o2);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //convert bitmap to bytes
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private byte[] profileImage(Bitmap b){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 0, bos);
        return bos.toByteArray();
    }

    private void validateFields() {
        String empId = employeeId.getText().toString().trim();
        String fullname = fullName.getText().toString().trim();
        String addreses = address.getText().toString().trim();
        String phones = phone.getText().toString().trim();
        String emails = email.getText().toString().trim();
        String maritalStatu = maritalStatus.getText().toString().trim();
        String deptId = dept.getText().toString().trim();
        String dateofbirths = dateofbirth.getText().toString().trim();
        String hireDates = hireDate.getText().toString().trim();

        if (empId.isEmpty() || fullname.isEmpty() || addreses.isEmpty() || phones.isEmpty() || emails.isEmpty()
                || maritalStatu.isEmpty() || deptId.isEmpty() || dateofbirths.isEmpty() || hireDates.isEmpty()) {

            if (empId.isEmpty() && !fullname.isEmpty() && !addreses.isEmpty() && !phones.isEmpty() && !emails.isEmpty()
                    && !maritalStatu.isEmpty() && !deptId.isEmpty() && !dateofbirths.isEmpty() && !hireDates.isEmpty()) {
                Toast.makeText(this, " EmployeeID Field is Empty", Toast.LENGTH_SHORT).show();
            }
            if (!empId.isEmpty() && fullname.isEmpty() && !addreses.isEmpty() && !phones.isEmpty() && !emails.isEmpty()
                    && !maritalStatu.isEmpty() && !deptId.isEmpty() && !dateofbirths.isEmpty() && !hireDates.isEmpty()) {
                Toast.makeText(this, "Fullname Field is Empty", Toast.LENGTH_SHORT).show();
            }
            if (!empId.isEmpty() && !fullname.isEmpty() && addreses.isEmpty() && !phones.isEmpty() && !emails.isEmpty()
                    && !maritalStatu.isEmpty() && !deptId.isEmpty() && !dateofbirths.isEmpty() && !hireDates.isEmpty()) {
                Toast.makeText(this, "Address Field is Empty", Toast.LENGTH_SHORT).show();
            }
            if (!empId.isEmpty() && !fullname.isEmpty() && !addreses.isEmpty() && phones.isEmpty() && !emails.isEmpty()
                    && !maritalStatu.isEmpty() && !deptId.isEmpty() && !dateofbirths.isEmpty() && !hireDates.isEmpty()) {
                Toast.makeText(this, "Phone Field is Empty", Toast.LENGTH_SHORT).show();
            }
            if (!empId.isEmpty() && !fullname.isEmpty() && !addreses.isEmpty() && !phones.isEmpty() && emails.isEmpty()
                    && !maritalStatu.isEmpty() && !deptId.isEmpty() && !dateofbirths.isEmpty() && !hireDates.isEmpty()) {
                Toast.makeText(this, "Email Field is Empty", Toast.LENGTH_SHORT).show();
            }
            if (!empId.isEmpty() && !fullname.isEmpty() && !addreses.isEmpty() && !phones.isEmpty() && !emails.isEmpty()
                    && maritalStatu.isEmpty() && !deptId.isEmpty() && !dateofbirths.isEmpty() && !hireDates.isEmpty()) {
                Toast.makeText(this, "Marital Status Field is Empty", Toast.LENGTH_SHORT).show();
            }
            if (!empId.isEmpty() && !fullname.isEmpty() && !addreses.isEmpty() && !phones.isEmpty() && !emails.isEmpty()
                    && !maritalStatu.isEmpty() && deptId.isEmpty() && !dateofbirths.isEmpty() && !hireDates.isEmpty()) {
                Toast.makeText(this, "DepartmentID Field is Empty", Toast.LENGTH_SHORT).show();
            }
            if (!empId.isEmpty() && !fullname.isEmpty() && !addreses.isEmpty() && !phones.isEmpty() && !emails.isEmpty()
                    && !maritalStatu.isEmpty() && !deptId.isEmpty() && dateofbirths.isEmpty() && !hireDates.isEmpty()) {
                Toast.makeText(this, "Date of Birth Field is Empty", Toast.LENGTH_SHORT).show();
            }
            if (!empId.isEmpty() && !fullname.isEmpty() && !addreses.isEmpty() && !phones.isEmpty() && !emails.isEmpty()
                    && !maritalStatu.isEmpty() && !deptId.isEmpty() && !dateofbirths.isEmpty() && hireDates.isEmpty()) {
                Toast.makeText(this, "Hire Date Field is Empty", Toast.LENGTH_SHORT).show();
            }
            if (empId.isEmpty() && fullname.isEmpty() && addreses.isEmpty() && phones.isEmpty() && emails.isEmpty()
                    && maritalStatu.isEmpty() && deptId.isEmpty() && dateofbirths.isEmpty() && hireDates.isEmpty()) {
                Toast.makeText(this, "Fields are Empty", Toast.LENGTH_SHORT).show();
            }
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(emails).matches()){
            Toast.makeText(this, "Email Field Pattern Incorrect", Toast.LENGTH_SHORT).show();
        }

        else {
                sendDataToDatabase();
                //emptyFields();
                startActivity(new Intent(getApplicationContext(), BtnEmployeeActivity.class));
            }
    }

    private void sendDataToDatabase() {

        selectRadio();
        String empId = employeeId.getText().toString().trim();
        String fullname = fullName.getText().toString().trim();
        String addreses = address.getText().toString().trim();
        String phones = phone.getText().toString().trim();
        String emails = email.getText().toString().trim();
        String maritalStatu = maritalStatus.getText().toString().trim();
        int deptId = Integer.parseInt(dept.getText().toString().trim());
        String dateofbirths = dateofbirth.getText().toString().trim();
        String hireDates = hireDate.getText().toString().trim();
        String genders = gender;
        byte[] photo = profileImage(bp);
        String created_date = creation_date;

        if (!databaseHelper.checkEmployee(empId)) {
            Employee employee = new Employee(empId, fullname, addreses, phones, emails, genders, maritalStatu, hireDates, dateofbirths, deptId, photo, created_date);

            databaseHelper.addEmployee(employee);
            Toast.makeText(this, "Employee Created", Toast.LENGTH_SHORT).show();
            // Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            //startActivity(intent);
        } else {
            Toast.makeText(this, "Employee not Created", Toast.LENGTH_SHORT).show();
        }
    }

    private void selectRadio(){
        if(radioFemale.isSelected()){
            gender = "Female";
        }
        else if(radioMale.isSelected()){
            gender = "Male";
        }
    }

    private void emptyFields(){
        employeeId.setText("KO/"+emp_id);
        fullName.setText("");
        address.setText("");
        phone.setText("");
        email.setText("");
        maritalStatus.setText("");
        dept.setText("");
        dateofbirth.setText("");
        hireDate.setText("");
        pic.setImageResource(R.drawable.ic_person_add_black_24dp);
    }

}

package com.greaterheights.khaola.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.greaterheights.khaola.model.Department;
import com.greaterheights.khaola.model.Employee;
import com.greaterheights.khaola.model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DatabaseVersion = 1;
    //Database Name
    private static final String DatabaseName = "khoalo.db";

    //Department Table Name
    private static final String TableNameDept = "department";
    public static final String ColumnIdDept = "dept_id";
    public static final String ColumnNameDept = "name";
    public static final String ColumnDescDept = "description";
    public static final String ColumnDateDept = "date";

    //Creating Table using sql query
    private String CreateTableDept =
            "create table " + TableNameDept +
                    "(" + ColumnIdDept + " integer primary key autoincrement,"
                    + ColumnNameDept + " text,"
                    + ColumnDescDept + " text,"
                    + ColumnDateDept + " text" + ")";

    //Droping a table
    private String DropTableDept = "drop table if exists " + TableNameDept;

    //User Table Name
    private static final String TableNameUser = "user";
    public static final String ColumnIdUser = "user_id";
    public static final String ColumnEmailUser = "email";
    public static final String ColumnPasswordUser = "password";
    public static final String ColumnPrivilegeUser = "privilege";
    public static final String ColumnCreatedDateUser = "creation_date";

    //Creating Table using sql query
    private String CreateTableUser =
            "create table " + TableNameUser +
                    "(" + ColumnIdUser + " integer primary key autoincrement,"
                    + ColumnEmailUser + " text,"
                    + ColumnPasswordUser + " text,"
                    + ColumnPrivilegeUser + " text,"
                    + ColumnCreatedDateUser + " text" + ")";

    //Droping a table
    private String DropTableUser = "drop table if exists " + TableNameUser;

    //Employee Table Name
    private static final String TableNameEmp = "employee";
    public static final String ColumnIdEmp = "emp_id";
    public static final String ColumnFullNameEmp = "fullname";
    public static final String ColumnAddressEmp = "address";
    public static final String ColumnPhoneEmp = "phone";
    public static final String ColumnEmailEmp = "email";
    public static final String ColumnGenderEmp = "gender";
    public static final String ColumnMaritalEmp = "marital_status";
    public static final String ColumnHireDateEmp = "hire_date";
    public static final String ColumnDateOfBirthEmp = "date_of_birth";
    public static final String ColumnDepartmentIDEmp = "dept_id";
    public static final String ColumnPictureEmp = "picture";
    public static final String ColumnCreationDateEmp = "creation_date";

    //Creating Table using sql query
    private String CreateTableEmp =
            "create table " + TableNameEmp +
                    "(" + ColumnIdEmp + " text primary key,"
                    + ColumnFullNameEmp + " text,"
                    + ColumnAddressEmp + " text,"
                    + ColumnPhoneEmp + " text,"
                    + ColumnEmailEmp + " text,"
                    + ColumnGenderEmp + " text,"
                    + ColumnMaritalEmp + " text,"
                    + ColumnHireDateEmp + " text,"
                    + ColumnDateOfBirthEmp + " text,"
                    + ColumnDepartmentIDEmp + " integer,"
                    + ColumnPictureEmp + " blob,"
                    + ColumnCreationDateEmp + " text" + ")";

    //Droping a table
    private String DropTableEmp = "drop table if exists " + TableNameEmp;

    //Default Constructor
    public DatabaseHelper(Context context) {
        super(context, DatabaseName, null, DatabaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //using object of SQLiteDatabase to create table
        db.execSQL(CreateTableDept);
        db.execSQL(CreateTableUser);
        db.execSQL(CreateTableEmp);
    }
    //This method will be use to upgrade database version from 1 to any other version when new app is done

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop table if exist
        db.execSQL(DropTableDept);
        db.execSQL(DropTableUser);
        db.execSQL(DropTableEmp);
        //recreate new table
        onCreate(db);
    }
    //How to add users into the table using User class object as a parameter
    //==================================================================
    //Department Methods Start Here
    //==================================================================
    public void addDept(Department dept) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ColumnNameDept, dept.getName());
        values.put(ColumnDescDept, dept.getDescription());
        values.put(ColumnDateDept, dept.getDate());

        //null is inserted where there is no cell without value
        sqLiteDatabase.insert(TableNameDept, null, values);
        sqLiteDatabase.close();
    }

    public boolean checkDept(String deptname) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        //array to store column id
        String[] column = {ColumnIdDept};
        //selection criteria or where clause
        String selection = ColumnNameDept + "= ?";
        //selection argument is an array to username
        String[] selectionArgs = {deptname};
        //querying table with specified condtions
        Cursor cursor = sqLiteDatabase.query(TableNameDept, column, selection, selectionArgs, null, null, null);
        //return the number of row in the cursor object.
        int cursorCount = cursor.getCount();
        cursor.close();
        sqLiteDatabase.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean checkDept(int deptId) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        //array to store column id
        String[] column = {ColumnIdDept};
        //selection criteria or where clause
        String selection = ColumnIdDept + "= ?";
        //selection argument is an array to username
        String[] selectionArgs = {String.valueOf(deptId)};
        //querying table with specified condtions
        Cursor cursor = sqLiteDatabase.query(TableNameDept, column, selection, selectionArgs, null, null, null);
        //return the number of row in the cursor object.
        int cursorCount = cursor.getCount();
        cursor.close();
        sqLiteDatabase.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public void updateDept(Department dept) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //ContentValues class object is use to store data into the table
        ContentValues values = new ContentValues();
        values.put(ColumnIdDept, dept.getDept_id());
        values.put(ColumnNameDept, dept.getName());
        values.put(ColumnDescDept, dept.getDescription());

        String where = ColumnIdDept + "= ?";
        String[] whereArgs = { String.valueOf(dept.getDept_id())};

        //null is inserted where there is no cell without value
        sqLiteDatabase.update(TableNameDept, values, where, whereArgs);
        sqLiteDatabase.close();
    }

    //delete single user
    public void deleteDept(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TableNameDept, ColumnIdDept + " =?", new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }


    public Cursor fetchDataDept(String id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String[] selectionsArgs = {id};

        String query = "select * from "+ TableNameDept + " where " + ColumnIdDept + " = ?";

        Cursor cursor = sqLiteDatabase.rawQuery(query, selectionsArgs);

        return  cursor;
    }
    //==================================================================
    //Department Methods Ends Here
    //==================================================================

    //==================================================================
    //UserAccess Methods Start Here
    //==================================================================

    public void addUser(User user) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //ContentValues class object is use to store data into the table
        ContentValues values = new ContentValues();
        values.put(ColumnEmailUser, user.getEmail());
        values.put(ColumnPasswordUser, user.getPassword());
        values.put(ColumnPrivilegeUser, user.getPrivilege());
        values.put(ColumnCreatedDateUser, user.getCreation_date());

        //null is inserted where there is no cell without value
        sqLiteDatabase.insert(TableNameUser, null, values);
        sqLiteDatabase.close();
    }

    //updating single contact
    public void updateUser(User user) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //ContentValues class object is use to store data into the table
        ContentValues values = new ContentValues();
        values.put(ColumnEmailUser, user.getEmail());
        values.put(ColumnPasswordUser, user.getPassword());
        values.put(ColumnPrivilegeUser, user.getPrivilege());
        values.put(ColumnCreatedDateUser, user.getCreation_date());

        String where = ColumnIdUser + "= ?";
        String[] whereArgs = { String.valueOf(user.getUser_id())};

        //null is inserted where there is no cell without value
        sqLiteDatabase.update(TableNameUser, values, where, whereArgs);
        sqLiteDatabase.close();
    }

    //delete single user
    public void deleteUser(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TableNameUser, ColumnIdUser + " =?", new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }

    public boolean checkUser(String email) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        //array to store column id
        String[] column = {ColumnIdUser};
        //selection criteria or where clause
        String selection = ColumnEmailUser + "= ?";
        //selection argument is an array to username
        String[] selectionArgs = {email};
        //querying table with specified condtions
        Cursor cursor = sqLiteDatabase.query(TableNameUser, column, selection, selectionArgs, null, null, null);
        //return the number of row in the cursor object.
        int cursorCount = cursor.getCount();
        cursor.close();
        sqLiteDatabase.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean checkUser(int user_id, String email) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        //array to store column id
        String[] column = {ColumnIdUser};
        //selection criteria or where clause
        String selection = ColumnIdUser + "= ?" + " AND " + ColumnEmailUser + "= ?";
        //selection argument is an array to username
        String[] selectionArgs = {String.valueOf(user_id), email};
        //querying table with specified condtions
        Cursor cursor = sqLiteDatabase.query(TableNameUser, column, selection, selectionArgs, null, null, null);
        //return the number of row in the cursor object.
        int cursorCount = cursor.getCount();
        cursor.close();
        sqLiteDatabase.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    //this method is use to check if the usename and password loging unto an app exist or not
    public boolean checkUser(String username, String password, String userRole){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        //array to fetch column
        String[] column = {ColumnIdUser};
        //selection criteria or where clause
        String selection = ColumnEmailUser + "= ?" + " AND " + ColumnPasswordUser + "= ?"+ " AND " + ColumnPrivilegeUser + "= ?";
        //selection argument is an array that contain specific username and password
        String[] selectionArgs = {username, password, userRole};
        //query table with conditions
        Cursor cursor = sqLiteDatabase.query(TableNameUser, column, selection, selectionArgs, null, null, null);
        //return the numbers of row in the cursosr object
        int cursorCount = cursor.getCount();
        cursor.close();
        sqLiteDatabase.close();
        if(cursorCount > 0){
            return true;
        }
        return false;
    }

    //this method is use to query or fetch data from table
    public Cursor fetchDataUser(String email){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String[] selectionsArgs = {email};

        String query = "select * from "+ TableNameUser + " where " + ColumnEmailUser + " = ?";

        Cursor cursor = sqLiteDatabase.rawQuery(query, selectionsArgs);

        return  cursor;
    }

    //get all users records
    public List<User> getAllUserRecord() {
        String[] column = {ColumnIdUser, ColumnEmailUser, ColumnPasswordUser, ColumnPrivilegeUser,ColumnCreatedDateUser};
        String sortOrder = ColumnIdUser + " asc";
        List<User> userList = new ArrayList<User>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TableNameUser, column, null, null, null,null, sortOrder);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setUser_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ColumnIdUser))));
                user.setEmail(cursor.getString(cursor.getColumnIndex(ColumnEmailUser)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(ColumnPasswordUser)));
                user.setPrivilege(cursor.getString(cursor.getColumnIndex(ColumnPrivilegeUser)));
                user.setCreation_date(cursor.getString(cursor.getColumnIndex(ColumnCreatedDateUser)));

                //Adding user record to list object one by one by using do while loop
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        //return list object userList
        return userList;
    }
    //==================================================================
    //UserAccess Methods Ends Here
    //==================================================================

    //==================================================================
    //Employee Methods Start Here
    //==================================================================
    public void addEmployee(Employee emp) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //ContentValues class object is use to store data into the table
        ContentValues values = new ContentValues();
        values.put(ColumnIdEmp, emp.getEmp_id());
        values.put(ColumnFullNameEmp, emp.getFullname());
        values.put(ColumnAddressEmp, emp.getAddress());
        values.put(ColumnPhoneEmp, emp.getPhone());
        values.put(ColumnEmailEmp, emp.getEmail());
        values.put(ColumnGenderEmp, emp.getGender());
        values.put(ColumnMaritalEmp, emp.getMarital_status());
        values.put(ColumnHireDateEmp, emp.getHire_date());
        values.put(ColumnDateOfBirthEmp, emp.getDate_of_birth());
        values.put(ColumnDepartmentIDEmp, emp.getDept_id());
        values.put(ColumnPictureEmp, emp.getPicture());
        values.put(ColumnCreationDateEmp, emp.getCreation_date());

        //null is inserted where there is no cell without value
        sqLiteDatabase.insert(TableNameEmp, null, values);
        sqLiteDatabase.close();
    }

    public boolean checkEmployee(String employeeId) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        //array to store column id
        String[] column = {ColumnIdEmp};
        //selection criteria or where clause
        String selection = ColumnIdEmp + "= ?";
        //selection argument is an array to username
        String[] selectionArgs = {employeeId};
        //querying table with specified condtions
        Cursor cursor = sqLiteDatabase.query(TableNameEmp, column, selection, selectionArgs, null, null, null);
        //return the number of row in the cursor object.
        int cursorCount = cursor.getCount();
        cursor.close();
        sqLiteDatabase.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    //==================================================================
    //Employee Methods Ends Here
    //==================================================================


}
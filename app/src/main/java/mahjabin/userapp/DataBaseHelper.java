package mahjabin.userapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String PARENT_USER_TABLE = "PARENT_USER_TABLE";
    private static final String CHILD_USER_TABLE = "CHILD_USER_TABLE";
    private static final String COLUMN_CHILD_FIRSTNAME = "CHILD_FIRSTNAME";
    private static final String COLUMN_CHILD_LASTNAME = "CHILD_LASTNAME";
    private static final String COLUMN_FIRSTNAME = "FIRSTNAME";
    private static final String COLUMN_LASTNAME = "LASTNAME";
    private static final String COLUMN_STREET = "STREET";
    private static final String COLUMN_CITY = "CITY";
    private static final String COLUMN_STATE = "STATE";
    private static final String COLUMN_ZIP = "ZIP";

    DataBaseHelper(Context context) {
        super(context, "User_List_New.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String createParentTableStatement = "CREATE TABLE " + PARENT_USER_TABLE + " ( " + COLUMN_FIRSTNAME + " TEXT PRIMARY KEY, " + COLUMN_LASTNAME + " TEXT, " + COLUMN_STREET + " TEXT, " + COLUMN_CITY + " TEXT, " + COLUMN_STATE + " TEXT, " + COLUMN_ZIP + " TEXT )";
        //String createChildTableStatement = "CREATE TABLE " + CHILD_USER_TABLE + " ( " + COLUMN_CHILD_FIRSTNAME + " TEXT PRIMARY KEY, " + COLUMN_CHILD_LASTNAME + " TEXT, " + COLUMN_FIRSTNAME + " TEXT " + COLUMN_LASTNAME + " TEXT )";

        String createChildTableStatement = "CREATE TABLE " + CHILD_USER_TABLE + " ( " + COLUMN_CHILD_FIRSTNAME + " TEXT PRIMARY KEY, " + COLUMN_CHILD_LASTNAME + " TEXT, " + COLUMN_FIRSTNAME + " TEXT, " + COLUMN_LASTNAME + " TEXT )";
        db.execSQL(createChildTableStatement);
        String createParentTableStatement = "CREATE TABLE " + PARENT_USER_TABLE + " ( " + COLUMN_FIRSTNAME + " TEXT PRIMARY KEY, " + COLUMN_LASTNAME + " TEXT, " + COLUMN_STREET + " TEXT, " + COLUMN_CITY + " TEXT, " + COLUMN_STATE + " TEXT, " + COLUMN_ZIP + " TEXT )";
        db.execSQL(createParentTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    boolean addNewUser(Parent parentUser) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FIRSTNAME, parentUser.getmFirstName());
        cv.put(COLUMN_LASTNAME, parentUser.getmLastName());
        cv.put(COLUMN_STREET, parentUser.getmStreet());
        cv.put(COLUMN_CITY, parentUser.getmCity());
        cv.put(COLUMN_STATE, parentUser.getmState());
        cv.put(COLUMN_ZIP, parentUser.getmZip());

        long insert = db.insert(PARENT_USER_TABLE, null, cv);

        return insert != -1;
    }

    boolean addNewChildUser(Child childUser) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        String parentFirstName = childUser.getParentFirstName();

        cv.put(COLUMN_CHILD_FIRSTNAME, childUser.getChildFirstName());
        cv.put(COLUMN_CHILD_LASTNAME, childUser.getChildLastName());
        cv.put(COLUMN_FIRSTNAME, childUser.getParentFirstName());
        cv.put(COLUMN_LASTNAME, childUser.getParentLastName());
        long insert;

        String queryString = "SELECT* FROM " + PARENT_USER_TABLE + " WHERE " + COLUMN_FIRSTNAME + " = ?";

        Cursor cursor = db.rawQuery(queryString, new String[]{parentFirstName});
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        } else {
            insert = db.insert(CHILD_USER_TABLE, null, cv);
            cursor.close();
            return insert != -1;
        }

    }

    void deleteUser(Parent parentUser) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + PARENT_USER_TABLE + " WHERE " + COLUMN_FIRSTNAME + " = ?";

        Cursor cursor = db.rawQuery(queryString, new String[]{parentUser.getmFirstName()});

        if (cursor.moveToFirst()) {
            System.out.println("success");
            cursor.close();
            db.close();
        } else {
            System.out.println("failed");
            cursor.close();
            db.close();
        }
    }

    void deleteChildUser(Child childUser) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + CHILD_USER_TABLE + " WHERE " + COLUMN_CHILD_FIRSTNAME + " = ?";

        Cursor cursor = db.rawQuery(queryString, new String[]{childUser.getChildFirstName()});

        if (cursor.moveToFirst()) {
            System.out.println("success");
            cursor.close();
            db.close();
        } else {
            System.out.println("failed");
            cursor.close();
            db.close();
        }
    }

    boolean updateParentUser(String updateFirstName, String updateLastName, String updateStreet, String updateCity, String updateState, String updateZip) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_LASTNAME, updateLastName);
        contentValues.put(COLUMN_STREET, updateStreet);
        contentValues.put(COLUMN_CITY, updateCity);
        contentValues.put(COLUMN_STATE, updateState);
        contentValues.put(COLUMN_ZIP, updateZip);

        db.update(PARENT_USER_TABLE, contentValues, COLUMN_FIRSTNAME + " = ? ",
                new String[]{String.valueOf(updateFirstName)});

        return true;
    }

    boolean updateChildUser(String updateChildFirstName, String updateChildLastName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_LASTNAME, updateChildLastName);

        db.update(PARENT_USER_TABLE, contentValues, COLUMN_FIRSTNAME + " = ? ",
                new String[]{String.valueOf(updateChildFirstName)});

        return true;
    }


    List<Parent> getAllUser() {
        List<Parent> returnParentList = new ArrayList<>();
        String queryString = "SELECT* FROM " + PARENT_USER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                String parentFirstName = cursor.getString(0);
                String parentLastName = cursor.getString(1);
                String parentStreet = cursor.getString(2);
                String parentCity = cursor.getString(3);
                String parentState = cursor.getString(4);
                String parentZip = cursor.getString(5);

                Parent parentUser = new Parent();

                parentUser.setmFirstName(parentFirstName);
                parentUser.setmLastName(parentLastName);
                parentUser.setmStreet(parentStreet);
                parentUser.setmCity(parentCity);
                parentUser.setmState(parentState);
                parentUser.setmZip(parentZip);

                returnParentList.add(parentUser);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnParentList;
    }

    List<Child> getAllChildUser() {
        List<Child> returnChildList = new ArrayList<>();
        String queryString = "SELECT* FROM " + CHILD_USER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                String childFirstName = cursor.getString(0);
                String childLastName = cursor.getString(1);
                String parentFirstName = cursor.getString(2);
                String parentLastName = cursor.getString(3);

                Child childUser = new Child();

                childUser.setChildFirstName(childFirstName);
                childUser.setChildLastName(childLastName);
                childUser.setParentFirstName(parentFirstName);
                childUser.setParentLastName(parentLastName);

                returnChildList.add(childUser);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnChildList;
    }
}

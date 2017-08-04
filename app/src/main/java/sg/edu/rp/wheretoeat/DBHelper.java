package sg.edu.rp.wheretoeat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "tasks.db";

    private static final String TABLE_TASK = "task";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_MENU = "menu";
    private static final String COLUMN_LOCATION = "location";

    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_TASK +  "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_DESCRIPTION + " TEXT,"
            + COLUMN_MENU + " TEXT,"
            + COLUMN_LOCATION + " TEXT)";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
        Log.i("info" ,"created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        onCreate(db);
    }

    public void insertTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DESCRIPTION, task.description);
        values.put(COLUMN_MENU, task.menu == null ? "[]" : task.menu.toString());
        values.put(COLUMN_LOCATION, task.location == null ? "{}" : task.location.toString());

        db.insert(TABLE_TASK, null, values);
        db.close();
    }

    public ArrayList<String> getTaskContent() {
        ArrayList<String> tasks = new ArrayList<String>();
        String selectQuery = "SELECT " + COLUMN_DESCRIPTION + " FROM " + TABLE_TASK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                tasks.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return tasks;
    }

    public ArrayList<Task> getTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        String selectQuery = "SELECT * FROM " + TABLE_TASK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String description = cursor.getString(1);
                String menu = cursor.getString(2);
                String location = cursor.getString(3);
                Task obj = new Task(id, description, Location.jsonToLocation(location),Menu.jsonToMenu(menu));
                tasks.add(obj);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return tasks;
    }

    public String getMenuById(int id){
        String selectQuery = "SELECT " + COLUMN_MENU + " FROM " + TABLE_TASK + " WHERE _id = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            return cursor.getString(0);
        }

        cursor.close();
        db.close();

        return null;
    }

    public void clearTasks(){
        SQLiteDatabase db = null;

        try {
            db = getWritableDatabase();
            db.delete(TABLE_TASK, null, null);
        } catch (final SQLiteException e) {
            e.printStackTrace();
        } finally {
            if(db != null){
                db.close();
            }
        }
    }

    public void recreateTaskTable(){
        final SQLiteDatabase db = getWritableDatabase();
        try{
            //delete table
            db.execSQL("DROP TABLE " + TABLE_TASK + ";");
            //recreate table
            db.execSQL(SQL_CREATE_TABLE);
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(db != null){
                db.close();
            }
        }
    }

}

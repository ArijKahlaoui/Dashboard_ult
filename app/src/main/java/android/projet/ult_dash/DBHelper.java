package android.projet.ult_dash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table student(matricule INTEGER primary key,name TEXT,contact TEXT,dob TEXT,specialite TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists student");
    }

    public boolean insertstudent(String matricule,String name,String contact,String dob,String specialite){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("matricule",matricule);
        cv.put("name",name);
        cv.put("contact",contact);
        cv.put("dob",dob);
        cv.put("specialite",specialite);
        Long result=DB.insert("student",null, cv);
        if (result==-1){
            return false ;
        }
        else{
            return true;
        }
    }

    public boolean updatestudent(String matricule,String name,String contact,String dob,String specialite){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("contact",contact);
        cv.put("dob",dob);
        cv.put("specialite",specialite);
        Cursor cursor = DB.rawQuery("select * from student where matricule = ?",new String[]{matricule});
        if (cursor.getCount()>0) {
            long result = DB.update("student", cv, "matricule=?", new String[]{matricule});
            if (result == -1) {
                return false;
            }
            else {
                return true;
            }
        }
        else{
            return false;
        }
    }

    public boolean deletestudent(String matricule){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from student where matricule = ?",new String[]{matricule});
        if (cursor.getCount()>0) {
            long result = DB.delete("student","matricule=?", new String[]{matricule});
            if (result == -1) {
                return false;
            }
            else {
                return true;
            }
        }
        else{
            return false;
        }
    }

    public Cursor getstudents(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from student",null);
        return cursor;
    }
}

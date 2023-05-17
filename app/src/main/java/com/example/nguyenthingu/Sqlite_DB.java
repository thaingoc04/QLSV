package com.example.nguyenthingu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Sqlite_DB extends SQLiteOpenHelper {
    // tên bảng
    public  static final String TableName = "ThiSinh";
    // Tên các cột trong bảng
    public static final String SBD = "SBD";
    public static final String HoTen = "HoTen";
    public static final String Toan = "Toan";
    public static final String Ly = "Ly";
    public static final String Hoa = "Hoa";
    public Sqlite_DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name,factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Tạo câu SQL để tạo bảng
        String sqlCreate = "Create table if not exists " + TableName + "("
                +SBD +" Text PRIMARY KEY, "
                +HoTen +" Text,"
                +Toan +" Float,"
                +Ly +" Float,"
                +Hoa +" Float)";
        // Chạy câu truy vấn sql để tạo bảng
        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Xóa bảng tableContact đã có
        sqLiteDatabase.execSQL("Drop table if exists "+ TableName);
        // Tạo lại
        onCreate(sqLiteDatabase);
    }
    public void addContact(ThiSinh item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(SBD, item.getSBD());
        value.put(HoTen, item.getHoTen());
        value.put(Toan, item.getToan());
        value.put(Ly, item.getLy());
        value.put(Hoa, item.getHoa());
        db.insert(TableName, null, value);
        db.close();
    }
    public void updateContact(String id, ThiSinh item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(SBD, item.getSBD());
        value.put(HoTen, item.getHoTen());
        value.put(Toan, item.getToan());
        value.put(Ly, item.getLy());
        value.put(Hoa, item.getHoa());
        db.update(TableName, value,SBD +"=?",new String[]{String.valueOf(id)});
        db.close();
    }
    public void deleteContact(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Delete From "+TableName+" Where SBD = "+id;
        db.execSQL(sql);
        db.close();
    }
    public ArrayList<ThiSinh> getAllContact(){
        ArrayList<ThiSinh> list = new ArrayList<>();
        // câu truy vấn
        String sql = "Select * from "+TableName;
        //Lấy đối tượng csdl sqlite
        SQLiteDatabase db = this.getReadableDatabase();
        //Chạy câu truy vấn trả về dạng Cursor
        Cursor cursor = db.rawQuery(sql, null);
        // Tạo ArrayList<Contact> để trả về
        if(cursor != null){
            while (cursor.moveToNext()){
                ThiSinh item = new ThiSinh(cursor.getString(0),cursor.getString(1),
                        cursor.getFloat(2), cursor.getFloat(3), cursor.getFloat(4));
                list.add(item);
            }
        }
        return list;
    }
}


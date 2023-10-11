package Dbhepler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbhepper extends SQLiteOpenHelper {
    public  MyDbhepper(Context context){
        super(context,"chuot",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tb_chuot="CREATE TABLE tb_chuot ( id       INTEGER PRIMARY KEY AUTOINCREMENT,   tenchuot  TEXT, soluong       INTEGER, chatlieu TEXT,mausac TEXT);";
        sqLiteDatabase.execSQL(tb_chuot);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

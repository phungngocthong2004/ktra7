package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import DTO.Chuot_DTO;
import Dbhepler.MyDbhepper;

public class Chuot_Dao {
    MyDbhepper dbhepper;
    SQLiteDatabase db;
    public Chuot_Dao(Context context){
        dbhepper=new MyDbhepper(context);
        db=dbhepper.getWritableDatabase();
    }
    public  long add(Chuot_DTO chuot_dto){
        ContentValues values=new ContentValues();
        values.put("tenchuot",chuot_dto.getTen());
        values.put("soluong",chuot_dto.getSoluong());
        values.put("chatlieu",chuot_dto.getChatlieu());
        values.put("mausac",chuot_dto.getMausac());
        return  db.insert("tb_chuot",null,values);
    }
    public  int update(Chuot_DTO chuot_dto){
        ContentValues values=new ContentValues();
        values.put("tenchuot",chuot_dto.getTen());
        values.put("soluong",chuot_dto.getSoluong());
        values.put("chatlieu",chuot_dto.getChatlieu());
        values.put("mausac",chuot_dto.getMausac());
        String []dk=new String[]{String.valueOf(chuot_dto.getId())};
        return  db.update("tb_chuot",values,"id=?",dk);
    }
    public  int xoa(Chuot_DTO chuot_dto){
        String []dk=new String[]{String.valueOf(chuot_dto.getId())};
        return  db.delete("tb_chuot","id=?",dk);
    }
    public ArrayList<Chuot_DTO>getAll(){
        ArrayList<Chuot_DTO>list=new ArrayList<>();
        Cursor c=db.rawQuery("select * from tb_chuot",null);
        if (c!=null&&c.getCount()>0){
            c.moveToFirst();
            do {
                list.add(new Chuot_DTO(c.getInt(0),c.getString(1),c.getInt(2),c.getString(3),c.getString(4)));
            }while (c.moveToNext());
        }
        return list;
    }
}

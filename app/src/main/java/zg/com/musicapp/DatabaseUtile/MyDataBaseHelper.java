package zg.com.musicapp.DatabaseUtile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lzw on 2018/5/6.
 */

public class MyDataBaseHelper extends SQLiteOpenHelper {

    String tableName = "create table if not exists user(" +
                            "id int," +
                            "username varchar(200)," +
                            "password varchar(20))";

    public MyDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tableName);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

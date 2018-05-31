package zg.com.musicapp.DatabaseUtile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by lzw on 2018/5/6.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "zg";

    String createTable = "create table Test(id int,content varchar(200),age int)";

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: 创建数据库");
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: 更新数据库");
    }
}

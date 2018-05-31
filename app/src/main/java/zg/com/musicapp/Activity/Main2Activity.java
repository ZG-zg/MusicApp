package zg.com.musicapp.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import zg.com.musicapp.DatabaseUtile.MyDataBaseHelper;
import zg.com.musicapp.R;


public class Main2Activity extends AppCompatActivity {

    private EditText editname;
    private EditText  editPwd;
    private EditText  editPwdagain;
    private Button bt_zhuce,bt_login;
    private String name;
    private String password ;
    private String pwdagin ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initView();

    }

    private void initView() {

        bt_login =  findViewById(R.id.bt_login);
        bt_zhuce =  findViewById(R.id.bt_zhuce);

        editname =  findViewById(R.id.edit_user);
        editPwd =  findViewById(R.id.edit_pwd);
        editPwdagain =  findViewById(R.id.edit_pwd_again);
       /* //获取数据:获取SharedPreference对象
        SharedPreferences sp = this.getSharedPreferences("USER_INFO",MODE_PRIVATE);
        String username = sp.getString("userName","");//得到缓存中的数据，根据key
        String userpwd = sp.getString("userPwd","");
        //设置页面文本显示
        showName.setText(username);
        showPwd.setText(userpwd);*/



        bt_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zhuce();
            }
        });
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        /*
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateData();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });*/
    }

    public void zhuce(){
        name = editname.getText().toString();
        password = editPwd.getText().toString();
        pwdagin = editPwdagain.getText().toString();
        if(judge()){
            insertData(name,password);
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            Log.d("zgzhuce", "注册成功: ");
        }
    }

    public boolean judge(){
        name = editname.getText().toString();
        password = editPwd.getText().toString();
        pwdagin = editPwdagain.getText().toString();
        boolean flag = queryData(name);
        if(flag){
            Toast.makeText(this, "用户名已存在", Toast.LENGTH_SHORT).show();
            Log.d("zgjudge", "用户名已存在: ");
            return false;
        }
        if(!password.equals(pwdagin)){
            Toast.makeText(this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
            Log.d("zgjudge", "两次输入密码不一致: ");
            return false;
        }
        return true;
    }

    private void insertData(String name,String pwd){
        ContentValues cv = new ContentValues();
        cv.put("id",1);
        cv.put("username",name);
        cv.put("password",pwd);
        //实例
        MyDataBaseHelper dbh = new MyDataBaseHelper(this,"user",null,3);
        SQLiteDatabase db = dbh.getWritableDatabase();
        db.insert("user",null,cv);//数据库名
        Log.d("zginsertData", "insertData: "+name+pwd);
        db.close();
    }

    private boolean queryData( String name){
        boolean flag  = false;
        String username;
        String selectQuery = "select id,username,password from user where username=?";
        Log.d("zgquerdata", "queryData: "+name);
        MyDataBaseHelper dbh = new MyDataBaseHelper(this,"user",null,3);
        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,new String[]{String.valueOf(name)});
        if(cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                username = cursor.getString(cursor.getColumnIndex("username"));
                String password = cursor.getString(cursor.getColumnIndex("password"));
                Log.d("zgcursor", "queryData: "  + "-" + username + "-" + password);
            }while (cursor.moveToNext());
        }
        Log.d("zgcursor", "queryData: ");
        if(cursor.getCount()>0)
            flag = true;
        cursor.close();
        db.close();
    return flag;
    }
    /*
    private void updateData(){
        ContentValues cv = new ContentValues();
        cv.put("content","lizhiwei");
        MyDataBaseHelper dbh = new MyDataBaseHelper(this,"test",null,1);
        SQLiteDatabase db = dbh.getWritableDatabase();

        db.update("Test1",cv,"id=?",new String[]{"1"});
        db.close();
    }

    private void deleteData(){
        MyDataBaseHelper dbh = new MyDataBaseHelper(this,"test",null,1);
        SQLiteDatabase db = dbh.getReadableDatabase();

        db.delete("Test1","id=?",new String[]{"1"});
        db.close();;
    }
*/
}

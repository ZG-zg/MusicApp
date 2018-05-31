package zg.com.musicapp.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import zg.com.musicapp.Adaptar.SaveUserInfo;
import zg.com.musicapp.DatabaseUtile.MyDataBaseHelper;
import zg.com.musicapp.Fragment.UserFragment;
import zg.com.musicapp.R;

public class MainActivity extends AppCompatActivity {

    EditText edit_user;
    EditText edit_pwd;
    Button bt,bt_zhuce;
    private String muser;
    private String mpwd;
    SaveUserInfo s = new SaveUserInfo();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();//初始化控件的方法
    }

    /**
     * 初始化控件
     */
    private void initview() {
        edit_user = (EditText) findViewById(R.id.edit_user);
        edit_pwd = (EditText) findViewById(R.id.edit_pwd);
        data();
        bt = (Button) findViewById(R.id.bt);
        bt_zhuce = (Button) findViewById(R.id.bt_zhuce);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jump();
            }
        });
        bt_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }
    private void data() {
        String name = s.getStringSharedperences(this,"userName","");
        String pwd = s.getStringSharedperences(this,"userPwd","");
        /*
        SharedPreferences sp = this.getSharedPreferences("user_info",MODE_PRIVATE);
        String name = sp.getString("userName","");
        String pwd = sp.getString("userPwd","");
        */
        edit_user.setText(name);
        edit_pwd.append(pwd);
    }
    private void saveUserInfo() {

        s.setStringSharedperences(this,"userName",muser);
        s.setStringSharedperences(this,"userPwd",mpwd);
        /*
        SharedPreferences sp = this.getSharedPreferences("user_info",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();//借助editor对象对数据进行操作
        editor.putString("userName",muser);
        editor.putString("userPwd",mpwd);
        editor.commit();//一定要提交操作
        */

    }
    private void jump() {
        if(judge()){
            saveUserInfo();
            Bundle bundle = new Bundle();
            bundle.putSerializable("username", muser);
            //Intent intent = new Intent(this, UserFragment.class);
            Intent intent = new Intent(this, MainFragmentActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
    }



    private boolean judge() {
        muser = edit_user.getText().toString();
        mpwd = edit_pwd.getText().toString();
        String selectQuery = "select id,username,password from user where username=?";
        if(muser.equals("")){
            Toast.makeText(this, "用户名为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(mpwd.equals("")){
            Toast.makeText(this, "密码为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(queryData(selectQuery,muser)){
            Toast.makeText(this, "用户名有误或用户不存在", Toast.LENGTH_SHORT).show();
            return false;
        }
        selectQuery = "select id,username,password from user where username='"+muser+"' and password=?";
        Log.d("zgquerdata", "queryData: "+muser);
        if(queryData(selectQuery,mpwd)){
            Toast.makeText(this, "密码有误", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private boolean queryData( String selectQuery,String value){
        boolean flag  = true;
        //String selectQuery = "select id,username,password from user where username=?";
        MyDataBaseHelper dbh = new MyDataBaseHelper(this,"user",null,3);
        SQLiteDatabase db = dbh.getReadableDatabase();
        Log.d("zgquerdata", "queryData: "+value);
        Cursor cursor = db.rawQuery(selectQuery,new String[]{String.valueOf(value)});
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String password   = cursor.getString(cursor.getColumnIndex("password"));
            Log.d("zg", "queryData: "+id+"-"+username+"-"+password);
        }
        if(cursor.getCount()>0)
            flag = false;
        cursor.close();
        db.close();
        return flag;
    }
}

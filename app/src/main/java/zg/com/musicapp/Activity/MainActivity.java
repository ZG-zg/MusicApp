package zg.com.musicapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import zg.com.musicapp.Adaptar.SaveUserInfo;
import zg.com.musicapp.R;

public class MainActivity extends AppCompatActivity {

    EditText edit_user;
    EditText edit_pwd;
    Button bt;
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
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jump();
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
            Intent intent = new Intent(this, MainFragmentActivity.class);
            startActivity(intent);
            finish();
        }
    }



    private boolean judge() {
        muser = edit_user.getText().toString();
        mpwd = edit_pwd.getText().toString();
        if(muser.equals("")){
            Toast.makeText(this, "用户名为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(mpwd.equals("")){
            Toast.makeText(this, "密码为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(muser.length()<11||!muser.equals("18084057634")){
            Toast.makeText(this, "用户名有误", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!mpwd.equals("123456")){
            Toast.makeText(this, "密码有误", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}

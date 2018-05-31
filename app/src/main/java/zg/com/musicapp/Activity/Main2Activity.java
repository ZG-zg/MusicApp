package zg.com.musicapp.Activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import zg.com.musicapp.R;

public class Main2Activity extends AppCompatActivity {

    TextView show_user;
    TextView show_pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initview();
    }

    private void initview() {
        show_user = (TextView) findViewById(R.id.show_user);
        show_pwd = (TextView) findViewById(R.id.show_pwd);
        data();
    }

    private void data() {
        SharedPreferences sp = this.getSharedPreferences("user_info",MODE_PRIVATE);
        String name = sp.getString("userName","");
        String pwd = sp.getString("userPwd","");
        show_user.setText(name);
        show_pwd.append(pwd);

    }

}

package zg.com.musicapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import zg.com.musicapp.Activity.MainActivity;
import zg.com.musicapp.R;


public class UserFragment extends Fragment {
    private Button mbt_login;
    private Button mbt_exit;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        mbt_login = getActivity().findViewById(R.id.bt_login);
        mbt_exit = getActivity().findViewById(R.id.bt_exit);
        //监听登录事件
        mbt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();
            }
        });
        //监听退出事件
        mbt_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

}

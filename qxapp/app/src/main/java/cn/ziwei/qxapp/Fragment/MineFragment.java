package cn.ziwei.qxapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.ziwei.qxapp.Bean.User;
import cn.ziwei.qxapp.LoginActivity;
import cn.ziwei.qxapp.R;

/**
 * @author ziwei
 * @version 1.0
 * @createTime 2022.07.16  20:46:00
 * @Description  我的
 */
public class MineFragment extends Fragment {

    private TextView username, nickname;
    private Button loginout_btn;    // 退出登录按钮


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 初始化
        initView();

        // 加载当前用户信息
        getUserInfo();

        // 退出登录，点击事件
        loginout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 退出当前用户
                BmobUser.logOut();
                // 跳转到登录界面
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();

            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    /**
     * 加载当前用户信息，获取username和nickname
     */
    private void getUserInfo() {
        BmobUser bu = BmobUser.getCurrentUser(BmobUser.class);
        String id = bu.getObjectId();
        BmobQuery<User> queryUser = new BmobQuery<>();
        queryUser.getObject(id, new QueryListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null){
                    username.setText(user.getUsername());
                    nickname.setText(user.getNickname());
                }else {
                    Toast.makeText(getActivity(), "加载失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        username = getActivity().findViewById(R.id.username);
        nickname = getActivity().findViewById(R.id.nickname);
        loginout_btn = getActivity().findViewById(R.id.loginout);
    }


}
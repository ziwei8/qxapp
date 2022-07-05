package cn.ziwei.qxapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.ziwei.qxapp.Bean.User;

/**
 * @author ziwei
 * @version 1.0
 * @createTime 2022.07.05
 * @Description 注册
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText username, password, nickname;   // 登录年龄、密码、昵称
    private Button register_btn;                     // 注册按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        nickname = findViewById(R.id.nickname);
        register_btn = findViewById(R.id.register);

        // 注册按钮,设置点击事件
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setUsername(username.getText().toString().trim());
                user.setPassword(username.getText().toString().trim());
                user.setNickname(nickname.getText().toString().trim());

                // 判断用户名是否为空
                if (username.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, "用户名为空", Toast.LENGTH_SHORT).show();
                }
                // 判断密码是否为空
                if (password.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, "用户密码为空", Toast.LENGTH_SHORT).show();
                }

                user.signUp(new SaveListener<User>() {
                    @Override
                    public void done(User user, BmobException e) {
                        if (e == null){
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            finish();
                            // 跳转到登录界面
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }else {
                            Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
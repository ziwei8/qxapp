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
 * @Description 登录
 */
public class LoginActivity extends AppCompatActivity {

    private EditText username, password;        // 登录年龄、密码
    private Button login_btn, register_btn;     // 登录按钮、注册按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.login);
        register_btn = findViewById(R.id.register);

        // 登录按钮,设置点击事件
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 登录
                User user = new User();
                // 设置登录的账号
                user.setUsername(username.getText().toString().trim());
                // 设置登录的密码
                user.setPassword(password.getText().toString().trim());
                user.login(new SaveListener<User>() {
                    @Override
                    public void done(User bmobUser, BmobException e) {
                        if (e == null) {
                            // 登录成功，跳转到主界面
                            Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "用户名/密码错误，登录失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        // 注册按钮,设置点击事件
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}
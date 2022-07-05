package cn.ziwei.qxapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.ziwei.qxapp.Bean.User;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    private Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.login);

        // 登录按钮设置点击事件
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 登录方法
                User user = new User();
                user.setUsername(username.getText().toString().trim());
                user.setPassword(password.getText().toString().trim());
                user.login(new SaveListener<User>() {
                    @Override
                    public void done(User bmobUser, BmobException e) {
                        if (e == null) {
                            Toast.makeText(LoginActivity.this, "登录失败！", Toast.LENGTH_SHORT).show();
                        } else {
                            // 登录成功，跳转到主界面
                            Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }
                    }
                });
            }
        });
    }
}
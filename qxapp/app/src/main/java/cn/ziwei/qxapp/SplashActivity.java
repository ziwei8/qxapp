package cn.ziwei.qxapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.ziwei.qxapp.Bean.User;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // 延时操作，延时2秒，跳转到MainActivity
        Timer timer = new Timer();
        timer.schedule(timetask, 5000);

        // 初始化Bmob
        // appkey: 是Bmob网站上，应用qxapp，里面的Application ID
        Bmob.initialize(this, "9b6413e33eda93a2041ab3c34f64934e");
    }

    TimerTask timetask = new TimerTask() {
        @Override
        public void run() {
            // startActivity(new Intent(SplashActivity.this, MainActivity.class));
            // 如果已登录，跳转到主界面；没登陆，调转到登录界面
            BmobUser bmobUser = BmobUser.getCurrentUser(User.class);
            if (bmobUser!=null){
                // 已登录
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }else {
                // 未登录
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
        }
    };
}
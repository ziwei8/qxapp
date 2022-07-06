package cn.ziwei.qxapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.ziwei.qxapp.Bean.User;

public class MainActivity extends AppCompatActivity {

    private TextView username, nickname;  // 用户名，昵称

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        nickname = findViewById(R.id.nickname);

        // 进入主界面后，在Bmob云数据库中查询用户信息
        // 参考官方文档：http://doc.bmob.cn/data/android/develop_doc/#4
        BmobUser user = BmobUser.getCurrentUser(User.class);
        // 获取当前用户id
        String id = user.getObjectId();
        BmobQuery<User> query = new BmobQuery<>();
        // 通过id 查询用户信息
        query.getObject(id, new QueryListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null){
                    // 没有异常
                    username.setText(user.getUsername());
                    nickname.setText(user.getNickname());
                }else {
                    Toast.makeText(MainActivity.this, "查询失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
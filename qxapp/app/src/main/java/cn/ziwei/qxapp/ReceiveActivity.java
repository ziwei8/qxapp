package cn.ziwei.qxapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.ziwei.qxapp.Bean.Post;

/**
 * @author ziwei
 * @version 1.0
 * @createTime 2022.07.11  17:14:00
 * @Description 一个条目的具体页面
 */
public class ReceiveActivity extends AppCompatActivity {

    private TextView username, content, time;  // 条目的作者、内容、时间
    private ImageView backImg;                 // 返回按钮的图片


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        initView();
        initData();

        // 监听返回
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initData() {

        Intent in = getIntent();
        // 获取带进来的id
        String id = in.getStringExtra("id");
        Post post = new Post();
        // 根据id查询对应条目
        BmobQuery<Post> query = new BmobQuery<>();
        query.getObject(id, new QueryListener<Post>() {
            @Override
            public void done(Post post, BmobException e) {
                if (e == null){
                    username.setText(post.getUserName());
                    content.setText(post.getContent());
                    time.setText(post.getCreatedAt());
                }else {
                    Toast.makeText(ReceiveActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initView() {
        username = findViewById(R.id.username);
        content = findViewById(R.id.content);
        time = findViewById(R.id.time);
        backImg = findViewById(R.id.back);
    }
}
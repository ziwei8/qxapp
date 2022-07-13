package cn.ziwei.qxapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.ziwei.qxapp.Bean.Post;
import cn.ziwei.qxapp.LoginActivity;
import cn.ziwei.qxapp.R;
import cn.ziwei.qxapp.ReceiveActivity;

/**
 * @author ziwei
 * @version 1.0
 * @createTime 2022.07.11  17:14:00
 * @Description 首页适配器
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Post> posts;

    private final int N_TYPE = 0;  // 普通样式
    private final int F_TYPE = 1;  // foot type

    private int Max_num = 15;  // 最大显示数量

    private Boolean isfootview = true;  // 是否有footview

    public HomeAdapter(Context context, List<Post> posts){
        this.context = context;
        this.posts = posts;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 普通
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ord_item, parent, false);
        // 底部
        View footview = LayoutInflater.from(parent.getContext()).inflate(R.layout.foot_item, parent, false);

        if (viewType == F_TYPE){
            return new RecyclerViewHolder(footview, F_TYPE);
        }else {
            return new RecyclerViewHolder(view, N_TYPE);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) holder;
        if (isfootview && (getItemViewType(position)) == F_TYPE){
            // 底部加载提示
           recyclerViewHolder.loading.setText("加载中...");
            // 创建一个新线程，用于消息传递
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // 加载后，多显示8条数据
                    Max_num += 8;
                    notifyDataSetChanged();
                }
            }, 2000);
        }else {
            // ord_item的内容
            Post post = posts.get(position);
            recyclerViewHolder.username.setText(post.getUserName());
            recyclerViewHolder.nickname.setText(post.getNickName());
            recyclerViewHolder.content.setText(post.getContent());
            recyclerViewHolder.time.setText(post.getCreatedAt());

            // 监听点击
            // 点击一个条目后，跳转到具体界面
            recyclerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = recyclerViewHolder.getAbsoluteAdapterPosition();
                    if (BmobUser.getCurrentUser(BmobUser.class) != null){
                        // 用户已登录，点击一个条目后，跳转到 ReceiveActivity
                        Intent in = new Intent(context, ReceiveActivity.class);
                        // 携带id跳转
                        in.putExtra("id", posts.get(position).getObjectId());
                        context.startActivity(in);
                    }else {
                        // 用户未登录，提示请登录后，跳转到登录界面
                        Toast.makeText(context, "请登录", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, LoginActivity.class));
                    }
                }
            });
        }
    }

    /**
     * 获取条目的 种类
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        // 位于最大显示数目，则转换到F_TYPE
        if (position == Max_num-1){
            return F_TYPE;
        }else {
            return N_TYPE;
        }
    }

    /**
     * 获取当页显示的总数目
     * @return
     */
    @Override
    public int getItemCount() {
        // 如果数据库中查询的数据总数 小于 显示最大值 直接返回
        if (posts.size() < Max_num){
            return posts.size();
        }
        // 否则 返回最大显示数目
        return Max_num;
    }



    private class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView username, nickname, content, time; // ord_item的TextView
        public TextView loading;  // foot_item的TextView

        /**
         * 内部类RecyclerViewHolder的构造函数
         */
        public RecyclerViewHolder(View itemview, int viewtype) {
            super(itemview);

            if (viewtype == N_TYPE){
                // 如果是 N_TYPE， 获取条目的nickname，content，time
                username = itemview.findViewById(R.id.username);
                nickname = itemview.findViewById(R.id.nickname);
                content = itemview. findViewById(R.id.content);
                time = itemview.findViewById(R.id.time);
            }else if (viewtype == F_TYPE){
                // 如果是 F_TYPE， 获取 加载中...
                loading = itemview.findViewById(R.id.loading);
            }
        }
    }
}

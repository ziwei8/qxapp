package cn.ziwei.qxapp.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.ziwei.qxapp.Adapter.HomeAdapter;
import cn.ziwei.qxapp.Bean.Post;
import cn.ziwei.qxapp.Bean.User;
import cn.ziwei.qxapp.R;

/**
 * @author ziwei
 * @version 1.0
 * @createTime 2022.07.11  15:51:00
 * @Description  首页
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;    // 列表
    private SwipeRefreshLayout srlayout;  // 下拉刷新
    private TextView homePage;            // 首页顶部的TextView首页
    private TextView loginUser, welcome;  // 当前登录的用户

    List<Post> posts;  // 存查询数据库的数据

    private HomeAdapter homeAdapter;  // 适配器

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//    public void onCreate(Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        super.onActivityCreated(savedInstanceState);
//        super.onCreate(savedInstanceState);
        // 逻辑处理
        // 初始化视图
        initView();

        // 初始化Bmob，用于获取云数据库数据
        // appkey: 是Bmob网站上，应用qxapp，里面的Application ID
        Bmob.initialize(getActivity(), "1d9612a274c26107376d8980fc858126");
        // 初始刷新
        reflash();

        //在onCreate方法设置setColorSchemeResources()
        srlayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_red_light);
        // 监听刷新
        srlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 刷新
                reflash();
            }
        });

        // user 当前登录的用户 加载：   xxx 欢迎您
        BmobUser bmobUser = BmobUser.getCurrentUser(User.class);  // 获取当前用户
        String userId = bmobUser.getObjectId();
        BmobQuery<User> queryUser = new BmobQuery<>();
        queryUser.getObject(userId, new QueryListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null){
                    loginUser.setText(user.getUsername());
                }else {
                    loginUser.setText(" ");
                    welcome.setText(" ");
                }
            }
        });
    }

    /**
     * 刷新方法
     */
    private void reflash() {
        // 从数据库查询信息
        BmobQuery<Post> query = new BmobQuery<>();
        query.order("-createdAt");  // 按照创建时间，由近往远排
        query.setLimit(1000);
        query.findObjects(new FindListener<Post>() {
            @Override
            public void done(List<Post> list, BmobException e) {
                srlayout.setRefreshing(false);  // 停止刷新
                if (e == null){
                    posts = list;
                    homeAdapter = new HomeAdapter(getActivity(), posts);
                    // 组件垂直往下
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(homeAdapter);
                }else {
                    // 停止刷新
                    srlayout.setRefreshing(false);
                    Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 初始化视图
     */
    private void initView() {
        recyclerView = getActivity().findViewById(R.id.recyclerview);
        srlayout = getActivity().findViewById(R.id.swipe);
        homePage = getActivity().findViewById(R.id.homepage);
        loginUser = getActivity().findViewById(R.id.loginuser);
        welcome = getActivity().findViewById(R.id.welcome);
    }




}
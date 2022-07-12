package cn.ziwei.qxapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

import cn.ziwei.qxapp.Adapter.SectionsPagerAdapter;
import cn.ziwei.qxapp.Fragment.FragmentChat;
import cn.ziwei.qxapp.Fragment.FragmentMine;
import cn.ziwei.qxapp.Fragment.HomeFragment;

/**
 * @author ziwei
 * @version 1.0
 * @createTime 2022.07.03  16:08:00
 * @Description  app主页
 */
public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener, ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private BottomNavigationBar bottomNavigationBar;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewpager);
        bottomNavigationBar = findViewById(R.id.bottomNavigationBar);

        // 初始化
        initView();
        
    }

    /**
     * 初始化函数
     */
    private void initView() {
        // 初始化ViewPager
        initViewPager();
        // 初始化BottomNavigationBar
        initBottomNavigationBar();
    }

    /**
     * 初始化 ViewPager
     */
    private void initViewPager() {
        // 配置 ViewPager
        viewPager.setOffscreenPageLimit(3);   // 设置最大缓存个数
        fragments = new ArrayList<Fragment>();
        fragments.add(new HomeFragment());
        fragments.add(new FragmentChat());
        fragments.add(new FragmentMine());

        viewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager(), fragments));
        // 设置页面变化时的监听器
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(0);
    }

    /**
     * 初始化BottomNavigationBar
     */
    @SuppressLint("ResourceType")
    private void initBottomNavigationBar() {
        // 设置监听器
        bottomNavigationBar.setTabSelectedListener(this);
        bottomNavigationBar.clearAll();
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);   // 自适应大小
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);   // 背景风格
        bottomNavigationBar.setBarBackgroundColor(R.color.white)     // 背景颜色
                .setActiveColor(R.color.colorBase1)      // 未点击颜色
                .setInActiveColor(R.color.black);        // 点击颜色

        // 设置底部导航栏的三个item
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.homepage_fill, "首页"))  // 点击时图标的样子
//                .setInActiveColorResource(R.drawable.homepage))       // 没有点击时图标的样子
                .addItem(new BottomNavigationItem(R.drawable.mobilephone_fill, "论坛"))
//                .setInActiveColorResource(R.drawable.mobilephone))
                .addItem(new BottomNavigationItem(R.drawable.mine_fill, "我的"))
//                .setInActiveColorResource(R.drawable.mine))
                .setFirstSelectedPosition(0)       // 默认第一个显示面板是 我的
                .initialise();
    }

    @Override
    public void onTabSelected(int position) {

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}


//        // 进入主界面后，在Bmob云数据库中查询用户信息
//        // 参考官方文档：http://doc.bmob.cn/data/android/develop_doc/#4
//        BmobUser user = BmobUser.getCurrentUser(User.class);
//        // 获取当前用户id
//        String id = user.getObjectId();
//        BmobQuery<User> query = new BmobQuery<>();
//        // 通过id 查询用户信息
//        query.getObject(id, new QueryListener<User>() {
//            @Override
//            public void done(User user, BmobException e) {
//                if (e == null){
//                    // 没有异常
//                    username.setText(user.getUsername());
//                    nickname.setText(user.getNickname());
//                }else {
//                    Toast.makeText(MainActivity.this, "查询失败",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
package cn.ziwei.qxapp.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.ListFragment;

import java.util.List;

/**
 * @author ziwei
 * @version 1.0
 * @createTime 2022.07.10  16:00:00
 * @Description Adapter是用于连接后端数据和前端显示的适配器接口,是数据data和UI（View）之间一个重要的纽带
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public SectionsPagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}

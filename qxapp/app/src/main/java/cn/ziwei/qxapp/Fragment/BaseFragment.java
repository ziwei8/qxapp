package cn.ziwei.qxapp.Fragment;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author ziwei
 * @version 1.0
 * @createTime 2022.07.10  16:08:00
 * @Description  几个fragment的基类
 */
public abstract class BaseFragment extends Fragment {

    public Context context;
    public Resources resources;
    public LayoutInflater layoutInflater;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        this.resources = context.getResources();
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private View RootView;  // 缓存的frag

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 如果缓存为空，就创建
        if (RootView == null){
            RootView = inflater.inflate(getLayoutID(), container, false);
        }

        ViewGroup parent = (ViewGroup) RootView.getParent();
        if (parent != null){
            parent.removeView(RootView);
        }

        return RootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        bindEvent();
        initData();
    }

    @Nullable
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    // 泛型VV：View类型，id是控件的id
    protected <VV extends View> VV findView(View view, @IdRes int id){
        return view.findViewById(id);
    }

    protected <VV extends View> VV findView(@IdRes int id){
        return RootView.findViewById(id);
    }

    protected abstract void initView(View view);

    protected abstract void bindEvent();

    protected abstract void initData();

    protected abstract int getLayoutID();
}

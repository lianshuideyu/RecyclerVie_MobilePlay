package com.atguigu.recyclerview_mobileplay;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.atguigu.recyclerview_mobileplay.basefragment.BaseFragment;
import com.atguigu.recyclerview_mobileplay.pager.LocalAudioPager;
import com.atguigu.recyclerview_mobileplay.pager.LocalVideoPager;
import com.atguigu.recyclerview_mobileplay.pager.NetAudioPager;
import com.atguigu.recyclerview_mobileplay.pager.NetVideoPager;
import com.atguigu.recyclerview_mobileplay.pager.RecyclerViewPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup rg_bottom;

    private int position;
    private ArrayList<BaseFragment> fragments;

    private BaseFragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg_bottom = (RadioGroup) findViewById(R.id.rg_bottom);

        initData();

        rg_bottom.setOnCheckedChangeListener(this);
        rg_bottom.check(R.id.rb_recyclerview);//初进页面默认勾选的项目
    }

    private void initData() {
        fragments = new ArrayList<>();

        fragments.add(new LocalVideoPager());
        fragments.add(new LocalAudioPager());
        fragments.add(new NetAudioPager());
        fragments.add(new NetVideoPager());
        fragments.add(new RecyclerViewPager());

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {
        switch (id) {
            case R.id.rb_local_video:
                position = 0;
                break;
            case R.id.rb_local_audio:
                position = 1;
                break;
            case R.id.rb_net_audio:
                position = 2;
                break;
            case R.id.rb_net_video:
                position = 3;
                break;
            case R.id.rb_recyclerview:
                position = 4;
                break;
        }

        BaseFragment currentFragment = fragments.get(position);
        addFragment(currentFragment);

    }

    private void addFragment(BaseFragment currentFragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (tempFragment != currentFragment) {
            if(!currentFragment.isAdded()) {
                //当没有添加过先隐藏之前的然后添加当前的
                if(tempFragment != null) {
                    ft.hide(tempFragment);
                }

                ft.add(R.id.fl_pageFragment,currentFragment);
            }else {
                //如果添加过就先隐藏之前的缓存然后显示现在的
                if(tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.show(currentFragment);
            }
            tempFragment = currentFragment;

            ft.commit();//提交事务
        }


    }
}

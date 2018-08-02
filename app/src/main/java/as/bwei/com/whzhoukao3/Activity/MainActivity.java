package as.bwei.com.whzhoukao3.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import as.bwei.com.whzhoukao3.Fragment.Fragment0;
import as.bwei.com.whzhoukao3.Fragment.Fragment1;
import as.bwei.com.whzhoukao3.Fragment.Fragment2;
import as.bwei.com.whzhoukao3.Fragment.Fragment3;
import as.bwei.com.whzhoukao3.R;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab;
    private ViewPager vp;
    private DrawerLayout dl;
    private ArrayList<String> tabs;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
        dl = (DrawerLayout) findViewById(R.id.Dl);
        final ImageView iv = (ImageView) findViewById(R.id.iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dl.isDrawerOpen(Gravity.LEFT)){
                    dl.closeDrawer(Gravity.LEFT);
                }else {
                    dl.openDrawer(Gravity.LEFT);
                }
            }
        });

        tabs = new ArrayList<>();
        tabs.add("tab0");
        tabs.add("tab1");
        tabs.add("tab2");
        tabs.add("tab3");
        //设置TabLayout标签 添加tab选项卡
        tab.addTab(tab.newTab().setText(tabs.get(0)));
        tab.addTab(tab.newTab().setText(tabs.get(1)));
        tab.addTab(tab.newTab().setText(tabs.get(2)));
        tab.addTab(tab.newTab().setText(tabs.get(3)));


        //创建Fragment
        fragments = new ArrayList<>();
        fragments.add(new Fragment0());
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);
        //将TabLayout与ViewPager关联起来
        tab.setupWithViewPager(vp);
    }

    private class MyFragmentAdapter extends FragmentPagerAdapter{
        public MyFragmentAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position);
        }
    }
}

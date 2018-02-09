package com.mavoic.sitegis;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.mavoic.core.activitys.BaseActivity;
import com.mavoic.sitegis.home.HomeFragment;
import com.mavoic.sitegis.map.MapFragment;

public class MainActivity extends BaseActivity {

    private TextView mTextMessage;

    private HomeFragment homeFragment;

    private MapFragment mapFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    getSupportFragmentManager().beginTransaction().hide(mapFragment).show(homeFragment).commit();
                    return true;
                case R.id.navigation_dashboard:
                    if(mapFragment==null){
                        mapFragment = MapFragment.newInstance("AA");
                        getSupportFragmentManager().beginTransaction().add(R.id.container,mapFragment,"f2").commit();
                    }else{
                        getSupportFragmentManager().beginTransaction().hide(homeFragment).show(mapFragment).commit();
                    }

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        View view = findViewById(R.id.toolbarLayout);
        mImmersionBar.titleBar(R.id.toolbar,view).init();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_menu_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("电院新闻");
        toolbar.setNavigationIcon(R.mipmap.ic_menu);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



        if(homeFragment==null){
            homeFragment = HomeFragment.newInstance("hello world");
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container,homeFragment , "f1")        //.addToBackStack("fname")
                    .commit();
        }
    }

}

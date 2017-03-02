package com.jartcorp.hnoss;

import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.jartcorp.hnoss.activity.AccountFragment;
import com.jartcorp.hnoss.activity.AuctingFragment;
import com.jartcorp.hnoss.activity.FreeFragment;
import com.jartcorp.hnoss.activity.GalleryFragment;
import com.jartcorp.hnoss.activity.HomeFragment;
import com.jartcorp.hnoss.activity.LoginFragment;
import com.jartcorp.hnoss.activity.SettingFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private DrawerLayout drawLayout;
    private View drawViewShow;

    /**
     * Used to store the last screen title. For use in {@link #//restoreActionBar()}.
     */
    private CharSequence mTitle;

    //Title
    private ImageView imgMenuName;
    private ImageView imgMenuTouch;
    private RelativeLayout leftDraw;
    private ListView list;
    private List<android.support.v4.app.Fragment> fragments;
    private String[] menusStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();




        drawLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        drawLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                drawViewShow = drawerView;

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                drawViewShow = drawerView;
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        imgMenuTouch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (drawLayout.isDrawerOpen(leftDraw)) {
                    drawLayout.closeDrawer(leftDraw);
                } else {
                    drawLayout.openDrawer(leftDraw);
                }
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int position,
                                    long id) {
                selectItem(position);
            }
        });
        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    private void init() {

        imgMenuTouch = (ImageView)findViewById(R.id.imgMenuTouch);
        imgMenuName = (ImageView)findViewById(R.id.imgMenuName);
        leftDraw = (RelativeLayout)findViewById(R.id.rlDrawerLeft);
        list = (ListView)findViewById(R.id.left_drawer);

        drawViewShow = leftDraw;
        menusStr = new String[] {
                "Home", "Free","Setting"
        };
        /*menusStr = new String[] {
                "Home", "Free", "Gallery", "Account", "Aucting", "Setting","Login"
        };*/
        fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance("Home"));
        fragments.add(FreeFragment.newInstance("Free"));
        //fragments.add(GalleryFragment.newInstance("Gallery"));
        //fragments.add(AccountFragment.newInstance("Account"));
        //fragments.add(AuctingFragment.newInstance("Aucting"));
        fragments.add(SettingFragment.newInstance("Setting"));
        //fragments.add(LoginFragment.newInstance("Login"));

        setAdapter();
    }

    private void setAdapter() {
        list.setAdapter(new ArrayAdapter<String>(this, R.layout.fragment_navigation_drawer, menusStr));
    }

    private void selectItem(int position) {
        android.support.v4.app.Fragment fragment = fragments.get(position);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.flContainer, fragment).commit();

//        title.setText(menusStr[position]);
        list.setItemChecked(position, true);
//        drawLayout.closeDrawer(leftDraw);
        if (drawLayout.isDrawerOpen(leftDraw)) {
            drawLayout.closeDrawer(leftDraw);
        }
    }


}
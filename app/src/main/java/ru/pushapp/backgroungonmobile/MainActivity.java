package ru.pushapp.backgroungonmobile;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import ru.pushapp.backgroungonmobile.adapters.ViewPagerAdapter;
import ru.pushapp.backgroungonmobile.fragments.BestImageFragment;
import ru.pushapp.backgroungonmobile.fragments.CategoryFragment;
import ru.pushapp.backgroungonmobile.fragments.LastImageFragment;

public class MainActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {

    Toolbar toolbar;
    ActionBar actionbar;

    DrawerLayout mDrawerLayout;
    NavigationView navigationView;

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        viewPager = findViewById(R.id.view_pager);
        setupViewPager(viewPager);
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        navigationView.getMenu().getItem(0).setChecked(true);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CategoryFragment(), "Категории");
        adapter.addFragment(new LastImageFragment(), "Последнее");
        adapter.addFragment(new BestImageFragment(), "Лучшее");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mDrawerLayout.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Toast.makeText(this,menuItem.getTitle(),Toast.LENGTH_LONG).show();

        menuItem.setChecked(true);
        mDrawerLayout.closeDrawers();

//        switch (menuItem.getItemId()){
//            case R.id.dashboard_item_menu:
//
//        }
        return true;
    }

}

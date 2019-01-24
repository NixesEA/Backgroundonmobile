package ru.pushapp.backgroungonmobile;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import ru.pushapp.backgroungonmobile.fragments.AllWallpapersFragment;

public class MainActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {

    Toolbar toolbar;
    ActionBar actionbar;

    DrawerLayout mDrawerLayout;
    NavigationView navigationView;

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

        navigationView.setItemBackgroundResource(R.drawable.activated_background);
        navigationView.getMenu().getItem(0).setChecked(true);
        openAllWallpaper();
    }

    public void openAllWallpaper(){
        Fragment currentFragment = getSupportFragmentManager().findFragmentByTag("AllWallpaper");
        if (currentFragment != null){
            return;
        }

        Fragment fragment = new AllWallpapersFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment,"AllWallpaper");
        transaction.show(fragment);
        transaction.commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mDrawerLayout.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItem.setChecked(true);

        switch (menuItem.getItemId()) {
            case R.id.dashboard_item_menu:
                openAllWallpaper();
                break;
            case R.id.favorites_item_menu:
                break;
            default:
                Toast.makeText(this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
        }

        mDrawerLayout.closeDrawers();
        return true;
    }

}
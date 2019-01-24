package ru.pushapp.backgroundonmobile.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.pushapp.backgroundonmobile.R;
import ru.pushapp.backgroundonmobile.adapters.ViewPagerAdapter;

public class AllWallpapersFragment extends Fragment {

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_walpaper_layout, container, false);

        viewPager = view.findViewById(R.id.view_pager);
        setupViewPager(viewPager);
        tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new CategoryFragment(), "Категории");
        adapter.addFragment(new LastImageFragment(), "Последнее");
        adapter.addFragment(new BestImageFragment(), "Лучшее");
        viewPager.setAdapter(adapter);
    }

}

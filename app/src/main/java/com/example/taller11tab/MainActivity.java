package com.example.taller11tab;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupViewPager();
        setupTabLayout();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void setupViewPager() {
        viewPager = findViewById(R.id.viewpager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.HORIZONTAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider));
        viewPager.addItemDecoration(dividerItemDecoration);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle()));
        appBarLayout = findViewById(R.id.appBar);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position != ViewPagerAdapter.Tab.PELICULAS.position) {
                    appBarLayout.setExpanded(true);
                }
            }
        });
    }

    private void setupTabLayout() {
        tabLayout = findViewById(R.id.tabs);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    tab.setText(ViewPagerAdapter.Tab.byPosition(position).title);
                    tab.setIcon(ViewPagerAdapter.Tab.byPosition(position).icon);
                })
                .attach();

        Objects.requireNonNull(tabLayout.getTabAt(ViewPagerAdapter.Tab.FAVORITOS.position))
                .getOrCreateBadge()
                .setVisible(true);
        Objects.requireNonNull(tabLayout.getTabAt(ViewPagerAdapter.Tab.PELICULAS.position))
                .getOrCreateBadge()
                .setNumber(20);
        applyBadgeColor(ViewPagerAdapter.Tab.FAVORITOS.position);
        applyBadgeColor(ViewPagerAdapter.Tab.PELICULAS.position);
    }

    private void applyBadgeColor(int pos) {
        Objects.requireNonNull(tabLayout.getTabAt(pos))
                .getOrCreateBadge()
                .setBackgroundColor(getResources().getColor(R.color.black));
    }

}
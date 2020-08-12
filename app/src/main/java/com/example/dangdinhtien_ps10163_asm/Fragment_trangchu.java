package com.example.dangdinhtien_ps10163_asm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.dangdinhtien_ps10163_asm.Adapter.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class Fragment_trangchu extends Fragment {
    ViewPager viewPager2;
    TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout_trangchu, container, false);

    }
        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
        viewPager2 = view.findViewById(R.id.viewpager);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        sectionsPagerAdapter.addFragment(new Fragment_Baihat());
        sectionsPagerAdapter.addFragment(new Album());
        viewPager2.setAdapter(sectionsPagerAdapter);
        //tab layout
        tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager2);
        tabLayout.getTabAt(0).setText("Bài hát");
        tabLayout.getTabAt(1).setText("Album");
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    }
}
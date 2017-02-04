package com.stcml.live;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class HomeFragment extends Fragment {

    ImageButton btnRefresh;
    WebView homefeature;
    WebSettings settings;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("News"));
        tabLayout.addTab(tabLayout.newTab().setText("Notices"));

        final ViewPager viewPager = (ViewPager) v.findViewById(R.id.pager);

        final PagerAdapter adapter = new PagerAdapter(getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        btnRefresh = (ImageButton) v.findViewById(R.id.refreshbtn);
        homefeature = (WebView) v.findViewById(R.id.homefeature);

        homefeature.setBackgroundColor(0);
        settings = homefeature.getSettings();
        settings.setTextZoom(90);

        loadDatatoWV();

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Refreshing Data",Toast.LENGTH_SHORT).show();
                loadDatatoWV();
            }
        });

        // Inflate the layout for this fragment
        return v;
    }

    public void loadDatatoWV(){

        ConnectivityManager cm = (ConnectivityManager)getContext() .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()&& cm.getActiveNetworkInfo().isAvailable()&& cm.getActiveNetworkInfo().isConnected()) {
            homefeature.loadUrl(Config.HOMEFEATURE_URL);
        }

    }

    public class PagerAdapter extends FragmentStatePagerAdapter {

        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    NewsFragment news = new NewsFragment();
                    return news;
                case 1:
                    AnnouncementsFragment announcements = new AnnouncementsFragment();
                    return announcements;
                default:
                    return null;
            }
        }

        public int getCount() {
            return mNumOfTabs;
        }
    }

}

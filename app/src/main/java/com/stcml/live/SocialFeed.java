package com.stcml.live;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SocialFeed extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_social_feed, container, false);

        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.socialtabs);
        tabLayout.addTab(tabLayout.newTab().setText("Facebook Feeds"));
        tabLayout.addTab(tabLayout.newTab().setText("Twitter Feeds"));

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager mViewPager = (ViewPager) v.findViewById(R.id.socialcontainer);
        final SectionsPagerAdapter adapter = new SectionsPagerAdapter(getFragmentManager(), tabLayout.getTabCount());
        mViewPager.setAdapter(adapter);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return v;
    }

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        int mNumOfTabs;

        public SectionsPagerAdapter(FragmentManager fm, int mNumOfTabs) {
            super(fm);
            this.mNumOfTabs = mNumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    FacebookFragment facebookFragment = new FacebookFragment();
                    return facebookFragment;
                case 1:
                    TwitterFragment twitterFragment = new TwitterFragment();
                    return twitterFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }
}

package com.stcml.live;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance("Greetings!", "Welcome to S.Thomas' College Live mobile app.", R.drawable.crest, Color.parseColor("#3F51B5")));
        addSlide(AppIntroFragment.newInstance("College News", "Stay tuned to latest College News!", R.drawable.newsintro,  Color.parseColor("#3F51B5")));
        addSlide(AppIntroFragment.newInstance("Notices", "Get the latest College Notices", R.drawable.intronotices,  Color.parseColor("#3F51B5")));
        addSlide(AppIntroFragment.newInstance("School Calendar", "Get to know about the upcoming School Events.", R.drawable.introcal,  Color.parseColor("#3F51B5")));
        addSlide(AppIntroFragment.newInstance("All Features", "All what you need to have as a Thomian.", R.drawable.intromenu,  Color.parseColor("#3F51B5")));

        setBarColor(Color.parseColor("#303d87"));
        setSeparatorColor(Color.parseColor("#2196F3"));
        showSkipButton(true);
        setProgressButtonEnabled(true);
        setVibrate(false);
        setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }
}

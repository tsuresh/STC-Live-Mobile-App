package com.stcml.live;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import com.stcml.live.youtube.GetVideos;

public class CalendarFragment extends Fragment {

    boolean isNetwork;

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_calendar, container, false);

        isNetwork = isNetworkAvailable(getContext());

        WebView wv = (WebView) v.findViewById(R.id.calendarview);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setDomStorageEnabled(true);

        if(isNetwork){
            wv.loadUrl("file:///android_asset/calendar.html");
        } else {
            Toast.makeText(getContext(),"This feature requires a working internet connection",Toast.LENGTH_SHORT).show();
        }

        return v;
    }

    public static boolean isNetworkAvailable(Context c) {
        ConnectivityManager cm = (ConnectivityManager)c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()&& cm.getActiveNetworkInfo().isAvailable()&& cm.getActiveNetworkInfo().isConnected()) {
            return true;
        } else { return false; }
    }

}

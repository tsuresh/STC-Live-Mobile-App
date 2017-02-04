package com.stcml.live;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.stcml.live.rss.ReadRSS;
import com.stcml.live.youtube.GetVideos;

public class VideoFragment extends Fragment {

    RecyclerView recyclerView;
    GetVideos getVideos;
    ReadRSS readRSS;
    boolean isNetwork;

    public VideoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_video, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_video_view);

        isNetwork = isNetworkAvailable(getContext());

        if(isNetwork){
            getVideos = new GetVideos(getContext(), recyclerView);
            getVideos.execute();
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
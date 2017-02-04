package com.stcml.live;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stcml.live.rss.ReadRSS;

public class AnnouncementsFragment extends Fragment {

    RecyclerView recyclerView;
    ReadRSS readRSS;

    public AnnouncementsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_announcements, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_announcement_view);
        readRSS = new ReadRSS(container.getContext(), recyclerView, Config.ANNOUNCEMENTS_URL);
        readRSS.execute();

        return v;
    }



}
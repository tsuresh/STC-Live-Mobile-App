package com.stcml.live;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stcml.live.rss.ReadRSS;

public class NewsFragment extends Fragment {

    RecyclerView recyclerView;
    ReadRSS readRSS;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_news, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_news_view);
        readRSS = new ReadRSS(getContext(), recyclerView, Config.NEWS_URL);
        readRSS.execute();

        return v;
    }

}
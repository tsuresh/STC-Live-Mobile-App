package com.stcml.live;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stcml.live.rss.ReadRSS;

public class ClubsFragment extends Fragment {

    RecyclerView recyclerView;
    ReadRSS readRSS;

    public ClubsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_clubs, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_clubs_view);
        readRSS = new ReadRSS(getContext(), recyclerView, Config.CLUBS_URL);
        readRSS.execute();

        return v;
    }

}

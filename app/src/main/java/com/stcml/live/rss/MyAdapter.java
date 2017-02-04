package com.stcml.live.rss;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;
import com.stcml.live.R;
import com.stcml.live.RssDetail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<FeedItem>feedItems;
    Context context;

    int rowlayout;
    boolean isCluborSport = false;

    public MyAdapter(Context context, ArrayList<FeedItem>feedItems, RecyclerView rv){
        this.feedItems = feedItems;
        this.context = context;
        this.isCluborSport = isClubOrSport(rv);

        if(isCluborSport){
            this.rowlayout = R.layout.cns_bar_row;
        } else {
            this.rowlayout = R.layout.news_bar_row;
        }
    }

    public boolean isClubOrSport(RecyclerView rv){
        if(rv.getId() == R.id.recycler_clubs_view || rv.getId() == R.id.recycler_sports_view){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(rowlayout,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        YoYo.with(Techniques.FadeIn).playOn(holder.cardview);

        final FeedItem current = feedItems.get(position);

        if(isCluborSport){

            holder.Title.setText(current.getTitle());
            holder.Description.setText(current.getTitle() + " of S. Thomas' College Mount Lavinia.");
            holder.cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, RssDetail.class);
                    intent.putExtra("description",current.getDescription());
                    intent.putExtra("title",current.getTitle());
                    intent.putExtra("date","");
                    intent.putExtra("image","none");
                    context.startActivity(intent);
                }
            });

        } else {

            final String formattedDate = (String) DateUtils.getRelativeTimeSpanString( getDateInMillis(current.getPubDate()), System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS );
            holder.Title.setText(current.getTitle());
            holder.Date.setText(formattedDate);
            if(current.getThumbURL() == "none"){
                Picasso.with(context).load(R.drawable.default_thumb).into(holder.ThumbNail);
            } else {
                Picasso.with(context).load(current.getThumbURL()).into(holder.ThumbNail);
            }

            holder.cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, RssDetail.class);
                    intent.putExtra("description",current.getDescription());
                    intent.putExtra("title",current.getTitle());
                    intent.putExtra("date",formattedDate);
                    intent.putExtra("image",current.getThumbURL());
                    context.startActivity(intent);
                }
            });
        }

    }

    public static long getDateInMillis(String srcDate) {
        SimpleDateFormat desiredFormat = new SimpleDateFormat("E, d MMMM yyyy hh:mm:ss z");

        long dateInMillis = 0;
        try {
            Date date = desiredFormat.parse(srcDate);
            dateInMillis = date.getTime();
            return dateInMillis;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int getItemCount() {
        if(feedItems == null){
            return 0;
        } else {
            return feedItems.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Title, Description, Date;
        ImageView ThumbNail;
        CardView cardview;

        public MyViewHolder(View itemView) {
            super(itemView);

            if(isCluborSport){
                Title = (TextView) itemView.findViewById(R.id.club_title_text);
                Description = (TextView) itemView.findViewById(R.id.club_intro_text);
                cardview = (CardView) itemView.findViewById(R.id.club_cardview);
            } else {
                Title = (TextView) itemView.findViewById(R.id.title_text);
                Date = (TextView) itemView.findViewById(R.id.date_text);
                ThumbNail = (ImageView) itemView.findViewById(R.id.thumb_img);
                cardview = (CardView) itemView.findViewById(R.id.cardview);
            }

        }
    }

}

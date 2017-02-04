package com.stcml.live.youtube;

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
import com.stcml.live.VideoDetail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {

    ArrayList<VideoItem>videoItems;
    Context context;

    public VideoAdapter(Context context, ArrayList<VideoItem>feedItems, RecyclerView rv){
        this.videoItems = feedItems;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.news_bar_row,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        YoYo.with(Techniques.FadeIn).playOn(holder.cardview);

        final VideoItem current = videoItems.get(position);
        final String formattedDate = (String) DateUtils.getRelativeTimeSpanString( getDateInMillis(current.getPubDate()), System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS );

        holder.Date.setText(formattedDate);
        holder.Title.setText(current.getTitle());
        Picasso.with(context).load(current.getThumbURL()).into(holder.ThumbNail);

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoDetail.class);
                intent.putExtra("description",current.getVID());
                intent.putExtra("title",current.getTitle());
                intent.putExtra("date",formattedDate);
                intent.putExtra("image",current.getThumbURL());
                context.startActivity(intent);
            }
        });

    }

    public static long getDateInMillis(String srcDate) {
        SimpleDateFormat desiredFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
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
        return videoItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Title, Date;
        ImageView ThumbNail;
        CardView cardview;

        public MyViewHolder(View itemView) {
            super(itemView);

            Title = (TextView) itemView.findViewById(R.id.title_text);
            Date = (TextView) itemView.findViewById(R.id.date_text);

            ThumbNail = (ImageView) itemView.findViewById(R.id.thumb_img);
            cardview = (CardView) itemView.findViewById(R.id.cardview);

        }
    }

}

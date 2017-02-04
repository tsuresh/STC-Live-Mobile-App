package com.stcml.live.youtube;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.stcml.live.Config;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class GetVideos extends AsyncTask<Void, Void, Void> {

    Context context;
    ProgressDialog pd;
    ArrayList<VideoItem>videoItems;
    RecyclerView recyclerView;
    URL url;

    public GetVideos(Context context, RecyclerView recyclerView){

        this.recyclerView = recyclerView;
        this.context = context;
        pd = new ProgressDialog(context);
        pd.setMessage("Loading Videos...");

    }

    @Override
    protected Void doInBackground(Void... params) {

        ProcessXML(GetData());
        return null;
    }

    private void ProcessXML(Document data) {
        if(data != null){

            videoItems = new ArrayList<>();
            Element root = data.getDocumentElement();
            NodeList items = root.getChildNodes();

            for(int i=0; i<items.getLength(); i++){
                Node currentChild = items.item(i);

                if(currentChild.getNodeName().equalsIgnoreCase("entry")){

                    VideoItem videoItem = new VideoItem();
                    NodeList itemChilds = currentChild.getChildNodes();

                    for(int j=0; j<itemChilds.getLength(); j++){
                        Node current = itemChilds.item(j);

                        if(current.getNodeName().equalsIgnoreCase("title")){
                            videoItem.setTitle(current.getTextContent());
                        } else if(current.getNodeName().equalsIgnoreCase("published")){
                            videoItem.setPubDate(current.getTextContent());
                        } else if(current.getNodeName().equalsIgnoreCase("yt:videoId")){
                            videoItem.setVID(current.getTextContent());
                        } else if(current.getNodeName().equalsIgnoreCase("media:group")){
                            Element mediacontentelement = (Element) current;
                            String thumbnail = mediacontentelement.getElementsByTagName("media:thumbnail").item(0).getAttributes().getNamedItem("url").getNodeValue();
                            videoItem.setThumbURL(thumbnail);
                        }
                    }
                    videoItems.add(videoItem);
                }
            }

        }
    }

    public Document GetData(){
        try {
            url = new URL(Config.VIDEO_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            Document xmlDoc = builder.parse(inputStream);
            return xmlDoc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPreExecute() {
        pd.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        pd.dismiss();
        super.onPostExecute(aVoid);
        VideoAdapter videoAdapter = new VideoAdapter(context, videoItems, recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(videoAdapter);
    }
}

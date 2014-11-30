package edu.osu.uvento.controller.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.osu.uvento.model.Event;
import edu.osu.uvento.model.Subscription;
import edu.osu.uvento.services.GetImageService;
import edu.osu.uvento.uvento.R;

/**
 * Created by chiragpa on 10/19/14.
 */
public class CustomGridAdapter extends BaseAdapter {

    private List<Item> items = new ArrayList<Item>();
    private LayoutInflater inflater;
    private Context context;

    public CustomGridAdapter(Context context , ArrayList<Subscription> subscriptionList , ArrayList<Event> myEventList) {
        this.context = context;
        inflater = LayoutInflater.from(context);

        if(myEventList != null && myEventList.size() > 0 ){
            System.out.println("My Events Present");
            items.add(new Item("My Events",R.drawable.load_animation,myEventList.get(0).getImage_url()));
        }

        if(subscriptionList != null) {
            int size = subscriptionList.size();
            for (int i = 0; i < size ; i++){
                items.add(new Item(subscriptionList.get(i).getName(),R.drawable.load_animation,subscriptionList.get(i).getImage_url()));
            }
        }
//        items.add(new Item("My Events", R.drawable.load_animation,intialUrl+ "/Uvento/Subscription/my_events.jpg"));
//        items.add(new Item("Computer Science & Engineering", R.drawable.load_animation,intialUrl +"/Uvento/Subscription/cse.png"));
//        items.add(new Item("Ohio Onion Activity Board", R.drawable.load_animation,intialUrl+"/Uvento/Subscription/ouab.jpeg"));
//        items.add(new Item("Indian Student Association", R.drawable.load_animation,intialUrl+"/Uvento/Subscription/isa.jpg"));
//        items.add(new Item("RPAC", R.drawable.load_animation,intialUrl +"/Uvento/Subscription/rpac.jpg"));
//        items.add(new Item("Multicultural Center", R.drawable.load_animation,intialUrl+"/Uvento/Subscription/multicultural.jpg"));
//        items.add(new Item("School of Art", R.drawable.load_animation,intialUrl+"/Uvento/Subscription/art.jpg"));
//        items.add(new Item("School of Music", R.drawable.load_animation,intialUrl+"/Uvento/Subscription/music.jpg"));
//        items.add(new Item("Sports", R.drawable.load_animation,intialUrl+"/Uvento/Subscription/play_sports.png"));
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return items.get(i).drawableId;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        TextView name;

        if (v == null) {
            v = inflater.inflate(R.layout.grid_view_item, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        }

        picture = (ImageView) v.getTag(R.id.picture);
        name = (TextView) v.getTag(R.id.text);

        Item item = (Item) getItem(i);


        GetImageService imageService = new GetImageService();
        imageService.getImage(context,picture,item.getUrl());
        picture.setImageResource(item.drawableId);


        name.setText(item.name);

        return v;
    }

    private class Item
    {
        final String name;
        final int drawableId;
        final String url;

        Item(String name, int drawableId , String url)
        {
            this.name = name;
            this.drawableId = drawableId;
            this.url = url;
        }

        public String getUrl(){
            return url;
        }
    }
}

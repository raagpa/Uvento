package edu.osu.uvento.contoller;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.osu.uvento.model.Event;
import edu.osu.uvento.uvento.R;

public class CustomListAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;


    public CustomListAdapter(Activity a, ArrayList<Event> d) {
        activity = a;
        data = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //imageLoader = new ImageLoader();
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position){
        return data.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.event_list_row, null);

        TextView title = (TextView) vi.findViewById(R.id.eventTitle); // title
        TextView artist = (TextView) vi.findViewById(R.id.group); // artist name
        TextView date = (TextView) vi.findViewById(R.id.date); // duration
        ImageView thumb_image = (ImageView) vi.findViewById(R.id.list_image); // thumb image


        Event event = (Event)getItem(position);
        // Setting all values in listview
        title.setText(event.getEventName());
        artist.setText("Computer Science & Engineering");
        date.setText("20 Oct");
        thumb_image.setImageResource(R.drawable.cse);
        return vi;
    }

}

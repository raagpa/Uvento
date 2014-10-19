package edu.osu.uvento.contoller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.osu.uvento.uvento.R;

/**
 * Created by chiragpa on 10/19/14.
 */
public class CustomGridAdapter extends BaseAdapter {

    private List<Item> items = new ArrayList<Item>();
    private LayoutInflater inflater;

    public CustomGridAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        items.add(new Item("My Events", R.drawable.my_events));
        items.add(new Item("Computer Science & Engineering", R.drawable.cse));
        items.add(new Item("Ohio Onion Activity Board", R.drawable.ouab));
        items.add(new Item("Indian Student Association", R.drawable.isa));
        items.add(new Item("RPAC", R.drawable.rpac));
        items.add(new Item("Multicultural Center", R.drawable.multicultural));
        items.add(new Item("School of Art", R.drawable.art));
        items.add(new Item("School of Music", R.drawable.music));
        items.add(new Item("Sports", R.drawable.play_sports));
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

        picture.setImageResource(item.drawableId);
        name.setText(item.name);

        return v;
    }

    private class Item
    {
        final String name;
        final int drawableId;

        Item(String name, int drawableId)
        {
            this.name = name;
            this.drawableId = drawableId;
        }
    }
}

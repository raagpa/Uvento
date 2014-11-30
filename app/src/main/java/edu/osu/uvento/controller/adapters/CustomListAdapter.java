package edu.osu.uvento.controller.adapters;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.osu.uvento.model.Event;
import edu.osu.uvento.services.GetImageService;
import edu.osu.uvento.uvento.R;

public class CustomListAdapter extends BaseAdapter implements Filterable {

    private Activity activity;
    private ArrayList<Event> originalData;
    private ArrayList<Event> filteredData;
    private static LayoutInflater inflater = null;

    private EventListFilter eventListFilter = new EventListFilter();


    public CustomListAdapter(Activity a, ArrayList<Event> d) {
        activity = a;
        originalData = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //imageLoader = new ImageLoader();
    }

    public int getCount() {
        return originalData.size();
    }

    public Object getItem(int position){
        return originalData.get(position);
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
        TextView group = (TextView) vi.findViewById(R.id.group); // artist name
        TextView date = (TextView) vi.findViewById(R.id.date); // duration
        ImageView thumb_image = (ImageView) vi.findViewById(R.id.list_image); // thumb image


        Event event = (Event)getItem(position);
        // Setting all values in listview
        if(event!=null) {
            title.setText(event.getTitle());
            group.setText(event.getName());
            date.setText(event.getDate());

            GetImageService imageService = new GetImageService();
            if (event.getImage_url() != null && !"".equals(event.getImage_url())) {
                imageService.getImage(activity, thumb_image, event.getImage_url());
            }
        }

        //thumb_image.setImageResource(R.drawable.load_animation);
        return vi;
    }

    @Override
    public Filter getFilter() {

        return eventListFilter;
    }

    private class EventListFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            System.out.println("performFiltering " +  constraint);

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<Event> list = originalData;

            int count = list.size();
            final ArrayList<Event> nlist = new ArrayList<Event>(count);

            String filterableString ;

            for (int i = 0; i < count; i++) {

                filterableString = list.get(i).getDate();
                if (filterableString.toLowerCase().equals(filterString)) {
                    nlist.add(list.get(i));
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraints, FilterResults filterResults) {
            originalData = (ArrayList<Event>) filterResults.values;
            System.out.println("publishResults " +  originalData.size());
            notifyDataSetChanged();
        }
    }
}

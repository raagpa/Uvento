package edu.osu.uvento.controller.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import edu.osu.uvento.model.Category;
import edu.osu.uvento.model.EventType;
import edu.osu.uvento.model.Subscription;
import edu.osu.uvento.uvento.R;

/**
 * Created by chiragpa on 11/9/14.
 */
public class ExpandableSubsribeToListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;

    private List<Subscription> subscriptionList;
    private HashMap<String,List> categoryMap;
    public ExpandableSubsribeToListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData ,
                                 List <Subscription> subscriptionList , HashMap<String,List> categoryMap) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this.subscriptionList = subscriptionList;
        this.categoryMap = categoryMap;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.subscribe_to_list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childText);
        String header = _listDataHeader.get(groupPosition);
        Subscription subscription;
        boolean subscribed = false;
        if(groupPosition == 2){
            List <EventType> list  = categoryMap.get(header);
            EventType eventType =  list.get(childPosition);
            System.out.println("***********" + eventType.getType());
            if( Subscription.isSubscribed(getSubscriptionList(), eventType.getId(), "EVENT_TYPE")){
                System.out.println("***********Subscribed *******" + eventType.getType());
                subscribed = true;
            }

        }else {
            List <Category> list  = categoryMap.get(header);
            Category category =  list.get(childPosition);
            if( Subscription.isSubscribed(getSubscriptionList(), category.getId(),"CATEGORY_TYPE")){
                subscribed = true;
            }

        }

        ImageView subscriptionImage = (ImageView) convertView.findViewById(R.id.subscribe);
        if(subscribed) {
            subscriptionImage.setImageResource(R.drawable.sub_selected);
            convertView.setTag("SUBSCRIBED");
        }else {
            subscriptionImage.setImageResource(R.drawable.sub_not_selected);
            convertView.setTag("NOT_SUBSCRIBED");
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.subscribe_to_list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public List<Subscription> getSubscriptionList() {
        return subscriptionList;
    }

    public void removeSubscription(Subscription subscription) {

        if(subscriptionList != null){
            int size  = subscriptionList.size();
            for(int i=0 ; i< size ;i++){
                if(subscriptionList.get(i).getId() == subscription.getId() &&
                        subscription.getType().equals(subscriptionList.get(i).getType())){
                    subscriptionList.remove(i);
                    return;
                }
            }
        }
    }
}

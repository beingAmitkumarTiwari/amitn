package com.getfreerecharge.instantnews.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.getfreerecharge.instantnews.R;
import com.getfreerecharge.instantnews.pojos.Source;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by amit on 2/21/2017.
 */

public class SourcesAdapter extends BaseAdapter {

    Context context;
    ArrayList<Source> rowitem;

    public SourcesAdapter(Context context, ArrayList<Source> rowitem) {
        this.context = context;
        this.rowitem = rowitem;
    }

    @Override
    public int getCount() {
        return rowitem.size();
    }

    @Override
    public Object getItem(int position) {
        return rowitem.size();
    }

    @Override
    public long getItemId(int position) {
        return rowitem.indexOf(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
        {
//            convertView=layoutInflater.inflate(R.layout.sources_set_layout, null);
//            SourcesViewHolder sourcesViewHolder = new SourcesViewHolder(convertView);
//
////            sourcesViewHolder.number= (TextView) convertView.findViewById(R.id.number);
//            sourcesViewHolder.name= (TextView) convertView.findViewById(R.id.name);
////            sourcesViewHolder.cat= (TextView) convertView.findViewById(R.id.cat);
//            sourcesViewHolder.description= (TextView) convertView.findViewById(R.id.description);
////            sourcesViewHolder.urls= (TextView) convertView.findViewById(R.id.urls);
////            sourcesViewHolder.country= (TextView) convertView.findViewById(R.id.country);
//            sourcesViewHolder.logo= (ImageView) convertView.findViewById(R.id.logo);

//            convertView.setTag(sourcesViewHolder);
        }

        Source source = rowitem.get(position);
        SourcesViewHolder holder = (SourcesViewHolder) convertView.getTag();
//        holder.number.setText(source.getId().toString());
        holder.name.setText(source.getName());
        holder.description.setText(source.getDescription());
     //   holder.urls.setText(source.getUrl());
      //  holder.cat.setText(source.getCategory());
//        holder.country.setText(source.getCountry());
        Picasso.with(context).load(String.valueOf(source.getUrlsToLogos().getSmall()))
                .placeholder(R.drawable.ic_load)
                .error(R.drawable.ic_load)
                .into(holder.logo);

        return convertView;
    }

    private class SourcesViewHolder
    {
        ImageView logo;
        TextView country, cat, urls, description, name, number;

        public SourcesViewHolder(View items)
        {
//            logo = (ImageView) items.findViewById(R.id.logo);
////            country= (TextView) items.findViewById(R.id.country);
////            cat= (TextView) items.findViewById(R.id.cat);
////            urls= (TextView) items.findViewById(R.id.urls);
//            description= (TextView) items.findViewById(R.id.description);
//            name= (TextView) items.findViewById(R.id.name);
//            number= (TextView) items.findViewById(R.id.number);
        }
    }
}

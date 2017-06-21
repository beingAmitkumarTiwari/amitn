package com.getfreerecharge.instantnews.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.getfreerecharge.instantnews.R;
import com.getfreerecharge.instantnews.pojos.OfferList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by Amit on 27-08-2016.
 */
public class MoreAppsAdapter extends BaseAdapter {

    Context context;
    ArrayList<OfferList> rowitem;
    String fontPath = "fonts/TimeRoman.ttf";
    String fontPathOne = "fonts/NeutonCursive-Regular.ttf";

    public MoreAppsAdapter(Context context, ArrayList<OfferList> rowitem) {
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
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.moreapps_set_layout, null);
            MoreAppsViewHolder moreAppsViewHolder = new MoreAppsViewHolder(convertView);
            moreAppsViewHolder.appname = (TextView) convertView.findViewById(R.id.appname);
            moreAppsViewHolder.imgurl = (ImageView) convertView.findViewById(R.id.imgurl);
            convertView.setTag(moreAppsViewHolder);
        }
        OfferList offerList = rowitem.get(position);
        MoreAppsViewHolder holder = (MoreAppsViewHolder) convertView.getTag();
        holder.appname.setText(offerList.getAppName());
        Picasso.with(context)
                .load(offerList.getImgageUrl())
                .placeholder(R.drawable.ic_load)
                .error(R.drawable.ic_load)
                .into(holder.imgurl);
        return convertView;
    }


    private class MoreAppsViewHolder {
        ImageView imgurl;
        TextView appname;

        public MoreAppsViewHolder(View items) {
            appname = (TextView) items.findViewById(R.id.appname);
            Typeface tf = Typeface.createFromAsset(context.getAssets(), fontPath);
            imgurl = (ImageView) items.findViewById(R.id.imgurl);
        }
    }
}

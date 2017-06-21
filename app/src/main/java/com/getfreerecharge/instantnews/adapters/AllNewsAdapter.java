package com.getfreerecharge.instantnews.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.getfreerecharge.instantnews.R;
import com.getfreerecharge.instantnews.pojos.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by amit on 2/25/2017.
 */

public class AllNewsAdapter extends BaseAdapter {


    public static final int adview=1;
    public static final int menuitem=0;

    Context context;
    ArrayList<Article> rowitem;
    String fontPath = "fonts/TimeRoman.ttf";
    String fontPathOne = "fonts/NeutonCursive-Regular.ttf";


    public AllNewsAdapter(Context context, ArrayList<Article> rowitem) {
        this.context = context;
        this.rowitem = rowitem;

    }

    /**
     * Returns a list of sample listings with native ads embedded. In practice, the listings
     * would typically be fetched from a web service instead of hard-coded.
     */



    @Override
    public int getCount()
    {
        return rowitem.size();
    }

    @Override
    public Object getItem(int position)
    {
        return rowitem.size();
    }

    @Override
    public long getItemId(int position)
    {
        return rowitem.indexOf(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView==null)
        {

            convertView=layoutInflater.inflate(R.layout.all_news_set_layout, null);
            AllNewsViewHolder allNewsViewHolder= new AllNewsViewHolder(convertView);
            allNewsViewHolder.pics= (ImageView) convertView.findViewById(R.id.pics);
            allNewsViewHolder.title= (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(allNewsViewHolder);

        }

        Article article = rowitem.get(position);
        AllNewsViewHolder holder= (AllNewsViewHolder) convertView.getTag();
        holder.title.setText(article.getTitle());
        Picasso.with(context).load(article.getUrlToImage())
                .placeholder(R.drawable.ic_load)
                .error(R.drawable.ic_load)
                .into(holder.pics);


        return convertView;
    }

    private class AllNewsViewHolder
    {
        ImageView pics;
        TextView title;
        public AllNewsViewHolder(View items)
        {
            pics = (ImageView) items.findViewById(R.id.pics);
            title= (TextView) items.findViewById(R.id.title);
           Typeface tf = Typeface.createFromAsset(context.getAssets(), fontPath);
            title.setTypeface(tf);
        }
    }

    public class NativeExpressViewHolder
    {
        public NativeExpressViewHolder(View itemss)
        {

        }
    }

}

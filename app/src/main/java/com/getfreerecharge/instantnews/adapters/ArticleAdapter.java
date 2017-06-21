package com.getfreerecharge.instantnews.adapters;

import android.app.Application;
import android.content.Context;
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
 * Created by amit on 2/22/2017.
 */

public class ArticleAdapter extends BaseAdapter{


    Context context;
    ArrayList<Article> rowitem;

    public ArticleAdapter(Context context, ArrayList<Article> rowitem) {
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

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Application.LAYOUT_INFLATER_SERVICE);
        if (convertView==null)
        {
//            convertView=layoutInflater.inflate(R.layout.artical_set_layout, null);
//            ArticleViewHolder articleViewHolder = new ArticleViewHolder(convertView);
//            articleViewHolder.title= (TextView) convertView.findViewById(R.id.title);
//           // articleViewHolder.description= (TextView) convertView.findViewById(R.id.description);
//            articleViewHolder.pics= (ImageView) convertView.findViewById(R.id.pics);
//            convertView.setTag(articleViewHolder);
        }

        Article article = rowitem.get(position);
        ArticleViewHolder holder= (ArticleViewHolder) convertView.getTag();
        holder.title.setText(article.getTitle());
        //holder.description.setText(article.getDescription());
        Picasso.with(context).load(article.getUrlToImage())
                .placeholder(R.drawable.ic_load)
                .error(R.drawable.ic_load)
                .into(holder.pics);

        return convertView;
    }

    private class ArticleViewHolder
    {
        ImageView pics;
        TextView title,description;
        public ArticleViewHolder(View items)
        {
//            pics= (ImageView) items.findViewById(R.id.pics);
//            title= (TextView) items.findViewById(R.id.title);
//            //description= (TextView) items.findViewById(R.id.description);
        }
    }
}

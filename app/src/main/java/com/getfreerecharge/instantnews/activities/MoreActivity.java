package com.getfreerecharge.instantnews.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.getfreerecharge.instantnews.R;
import com.getfreerecharge.instantnews.adapters.MoreAppsAdapter;
import com.getfreerecharge.instantnews.pojos.AdsOfferPojo;
import com.getfreerecharge.instantnews.pojos.OfferList;
import com.getfreerecharge.instantnews.utilitiess.RestInterface;
import com.getfreerecharge.instantnews.utilitiess.ServiceGenerator;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoreActivity extends AppCompatActivity {

    Call<AdsOfferPojo> adsOfferPojoCall;
    ListView listview_ads;
    ArrayList<OfferList> offerListArrayList;
    MoreAppsAdapter moreAppsAdapter;
    RestInterface restInterface;
    String fontPath = "fonts/TimeRoman.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_fragment_layout);
        offerListArrayList = new ArrayList<>();
        restInterface= ServiceGenerator.createServiceOne(RestInterface.class);
        listview_ads = (ListView) findViewById(R.id.listview_ads);

        adlist();
        moreAppsAdapter = new MoreAppsAdapter(getApplicationContext(), offerListArrayList);
        listview_ads.setOnItemClickListener(mOnItemClickListener);
    }

    AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent n = new Intent(Intent.ACTION_VIEW, Uri.parse(offerListArrayList.get(i).getLinkUrl()));
            startActivity(n);
        }
    };

    private void adlist() {

        JsonObject jsonObject = new JsonObject();
        adsOfferPojoCall=restInterface.getMoreApps();
        adsOfferPojoCall.enqueue(new Callback<AdsOfferPojo>() {
            @Override
            public void onResponse(Call<AdsOfferPojo> call, Response<AdsOfferPojo> response) {
                if (response.isSuccessful())
                {
                    if (response.body().getOfferList().size()>0)
                    {
                        AdsOfferPojo adsOfferPojo= response.body();
                        if (adsOfferPojo.getResponseCode().equals("1"))
                        {
                            offerListArrayList.clear();
                            List<OfferList> offerLists = adsOfferPojo.getOfferList();
                            for (int i =0 ; i < offerLists.size(); i++)
                            {
                                OfferList offerList = new OfferList();
                                offerList.setResponseCode(offerLists.get(i).getResponseCode());
                                offerList.setAppName(offerLists.get(i).getAppName());
                                offerList.setAppDescription(offerLists.get(i).getAppDescription());
                                offerList.setButtonDescription(offerLists.get(i).getButtonDescription());
                                offerList.setComainingApps(offerLists.get(i).getComainingApps());
                                offerList.setImgageUrl(offerLists.get(i).getImgageUrl());
                                offerList.setLinkUrl(offerLists.get(i).getLinkUrl());
                                offerList.setOffersStatus(offerLists.get(i).getOffersStatus());
                                offerList.setPayoutsofApp(offerLists.get(i).getPayoutsofApp());
                                offerList.setSurvayDates(offerLists.get(i).getSurvayDates());
                                offerList.setTypeofCompaining(offerLists.get(i).getTypeofCompaining());

                                offerListArrayList.add(offerList);
                            }
                        }
                    }
                    moreAppsAdapter = new MoreAppsAdapter(getApplicationContext(), offerListArrayList);
                    listview_ads.setAdapter(moreAppsAdapter);
                }
            }

            @Override
            public void onFailure(Call<AdsOfferPojo> call, Throwable t) {

            }
        });

    }
}

package com.getfreerecharge.instantnews.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.clockbyte.admobadapter.AdmobAdapterWrapper;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.JsonObject;
import com.getfreerecharge.instantnews.R;
import com.getfreerecharge.instantnews.activities.WebActivity;
import com.getfreerecharge.instantnews.adapters.AllNewsAdapter;
import com.getfreerecharge.instantnews.pojos.Article;
import com.getfreerecharge.instantnews.pojos.NewsPojo;
import com.getfreerecharge.instantnews.utilitiess.RestInterface;
import com.getfreerecharge.instantnews.utilitiess.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by amit on 2/23/2017.
 */

public class Science extends Fragment {

    RestInterface restInterface;
    ListView sourceList;
    ArrayList<Article> articleArrayList;
    Call<NewsPojo> newsPojoCall;
    AllNewsAdapter allNewsAdapter;
    AdmobAdapterWrapper admobAdapterWrapper;

    public Science() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.all_news_fragment, container, false);

        restInterface= ServiceGenerator.createService(RestInterface.class);
        sourceList= (ListView) view.findViewById(R.id.sources_list);
        articleArrayList=new ArrayList<>();
        allNewsAdapter=new AllNewsAdapter(getActivity(),articleArrayList);

        MobileAds.initialize(getActivity(), getString(R.string.test_admob_unit));

        //test devices' ids
        String[] testDevicesIds = new String[]{getString(R.string.testDeviceID), AdRequest.DEVICE_ID_EMULATOR};
        admobAdapterWrapper = new AdmobAdapterWrapper(getActivity(), testDevicesIds);

//        adapterWrapper=new AdmobAdapterWrapper(getActivity(), String.valueOf(R.string.test_admob_unit));

        admobAdapterWrapper.setAdapter(allNewsAdapter);
        admobAdapterWrapper.setLimitOfAds(7);
        admobAdapterWrapper.setNoOfDataBetweenAds(3);
        admobAdapterWrapper.setFirstAdIndex(2);

        scienceList();

        sourceList.setOnItemClickListener(onItemClickListener);
        return view;
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String url = articleArrayList.get(position).getUrl();
            Intent intent = new Intent(getActivity(), WebActivity.class);
            intent.putExtra("EXTRA_MESSAGE", url);
            startActivity(intent);

//            Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
            System.out.println("Amit" + position);
        }
    };

    private void scienceList() {
        JsonObject jsonObject=new JsonObject();
        newsPojoCall=restInterface.getNews("new-scientist", "84a7decf3110498ea372bd16dd0601e8");
        newsPojoCall.enqueue(new Callback<NewsPojo>() {
            @Override
            public void onResponse(Call<NewsPojo> call, Response<NewsPojo> response) {
                if (response.isSuccessful())
                {
                    if (response.body().getArticles().size()>0)
                    {
                        NewsPojo newsPojo=response.body();
                        if (newsPojo.getStatus().equals("ok"))
                        {
                            articleArrayList.clear();
                            List<Article> articles=newsPojo.getArticles();
                            for (int i=0; i<articles.size(); i++)
                            {
                                Article sciencess=new Article();
                                sciencess.setAuthor(articles.get(i).getAuthor());
                                sciencess.setTitle(articles.get(i).getTitle());
                                sciencess.setDescription(articles.get(i).getDescription());
                                sciencess.setUrl(articles.get(i).getUrl());
                                sciencess.setUrlToImage(articles.get(i).getUrlToImage());
                                sciencess.setPublishedAt(articles.get(i).getPublishedAt());

                                articleArrayList.add(sciencess);
                            }
                        }
                    }
                    allNewsAdapter=new AllNewsAdapter(getActivity(),articleArrayList);
                    sourceList.setAdapter(admobAdapterWrapper);
                }
            }

            @Override
            public void onFailure(Call<NewsPojo> call, Throwable t) {

            }
        });
    }

}

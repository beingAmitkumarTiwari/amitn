package com.getfreerecharge.instantnews.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
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


public class AllNews extends Fragment {

    TextView textView;
    ListView sourceList;
    Call<NewsPojo> sourcesCall;
    ArrayList<Article> articleArrayList;
    AllNewsAdapter allNewsAdapter;
    RestInterface restinterface;
    AdmobAdapterWrapper adapterWrapper;

    String fontPath = "fonts/TimeRoman.ttf";


    public AllNews() {
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

        restinterface = ServiceGenerator.createService(RestInterface.class);
        articleArrayList = new ArrayList<>();
        allNewsAdapter=new AllNewsAdapter(getActivity(),articleArrayList);
        sourceList= (ListView) view.findViewById(R.id.sources_list);

        MobileAds.initialize(getActivity(), getString(R.string.test_admob_unit));

        //test devices' ids
        String[] testDevicesIds = new String[]{getString(R.string.testDeviceID), AdRequest.DEVICE_ID_EMULATOR};
        adapterWrapper = new AdmobAdapterWrapper(getActivity(), testDevicesIds);

//        adapterWrapper=new AdmobAdapterWrapper(getActivity(), String.valueOf(R.string.test_admob_unit));

        adapterWrapper.setAdapter(allNewsAdapter);
        adapterWrapper.setLimitOfAds(7);
        adapterWrapper.setNoOfDataBetweenAds(3);
        adapterWrapper.setFirstAdIndex(2);

        AllNewsList();

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



    private void AllNewsList()
    {
        JsonObject jsonObject = new JsonObject();
        sourcesCall=restinterface.getNews("the-hindu", "84a7decf3110498ea372bd16dd0601e8");
        sourcesCall.enqueue(new Callback<NewsPojo>() {
            @Override
            public void onResponse(Call<NewsPojo> call, Response<NewsPojo> response) {
                if (response.isSuccessful())
                {
                    if (response.body().getArticles().size()>0)
                    {
                        NewsPojo newsPojo = response.body();
                        if (newsPojo.getStatus().equals("ok"))
                        {
                            articleArrayList.clear();
                            List<Article> sources1= newsPojo.getArticles();
                            for (int i=0; i<sources1.size(); i++ )
                            {
                                Article allnewss= new Article();
                                allnewss.setAuthor(sources1.get(i).getAuthor());
                                allnewss.setTitle(sources1.get(i).getTitle());

                                allnewss.setDescription(sources1.get(i).getDescription());
                                allnewss.setUrl(sources1.get(i).getUrl());
                                allnewss.setUrlToImage(sources1.get(i).getUrlToImage());
                                allnewss.setPublishedAt(sources1.get(i).getPublishedAt());

                                articleArrayList.add(allnewss);

                            }
                        }
                    }

                    allNewsAdapter = new AllNewsAdapter(getActivity(), articleArrayList);
                    sourceList.setAdapter(adapterWrapper);
                }

            }

            @Override
            public void onFailure(Call<NewsPojo> call, Throwable t) {

            }


        });
    }

}

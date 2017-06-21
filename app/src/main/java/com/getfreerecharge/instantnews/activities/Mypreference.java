package com.getfreerecharge.instantnews.activities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Kamal on 05-02-2015.
 */
public class Mypreference {

    SharedPreferences sp;
    SharedPreferences.Editor spEdit;

    private final String DATABASE = "myDatabase";
    private final String ADMOB_FULLPAGE_ID = "admobFullpageId";


    public Mypreference(Context context) {
        sp = context.getSharedPreferences(DATABASE, Context.MODE_PRIVATE);

    }


    public String getAdmobFullpageId() {
        return sp.getString(ADMOB_FULLPAGE_ID, "ca-app-pub-1001302816606190/4469494460");
    }

    public void setAdmobFullpageId(String admobFullpageId) {
        spEdit = sp.edit();
        spEdit.putString(ADMOB_FULLPAGE_ID,admobFullpageId);
        spEdit.commit();
    }


}

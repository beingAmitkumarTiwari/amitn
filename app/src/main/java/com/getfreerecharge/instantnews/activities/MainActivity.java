package com.getfreerecharge.instantnews.activities;


import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.getfreerecharge.instantnews.R;
import com.getfreerecharge.instantnews.adapters.ViewPagerAdapter;
import com.getfreerecharge.instantnews.fragments.Geographic;
import com.getfreerecharge.instantnews.fragments.NewsOfTheWeek;
import com.getfreerecharge.instantnews.fragments.SportTalk;
import com.getfreerecharge.instantnews.fragments.Science;
import com.getfreerecharge.instantnews.fragments.Sport;
import com.getfreerecharge.instantnews.fragments.Entertainment;
import com.getfreerecharge.instantnews.fragments.MetroNews;
import com.getfreerecharge.instantnews.fragments.AllNews;
import com.getfreerecharge.instantnews.fragments.Celebs;
import com.getfreerecharge.instantnews.fragments.Gaminggossip;
import com.getfreerecharge.instantnews.fragments.Technology;
import com.getfreerecharge.instantnews.fragments.TopGrossing;
import com.getfreerecharge.instantnews.fragments.LatestNews;
import com.getfreerecharge.instantnews.utilitiess.NotifyMe;
import com.getfreerecharge.instantnews.utilitiess.NotifyonSeven;
import com.getfreerecharge.instantnews.utilitiess.NotifyonThree;
import com.getfreerecharge.instantnews.utilitiess.NotifyonTwelve;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    AdRequest adRequest;
    Mypreference myprefs;
    InterstitialAd interstitialAd;
    private Handler handler1 = new Handler();

    private TabLayout tabLayout;
    private ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    private PendingIntent pendingIntent;

    private String googles="https://news.google.com/";
    private String bbcnews="http://www.bbc.com/news";
    private String mtvnews="http://www.mtv.com/news/";
    private String toi="http://timesofindia.indiatimes.com/";
    private String thehindu="http://www.thehindu.com/";
    private String cnbc="http://www.cnbc.com/world/?region=world";
    private String espn="http://www.espn.in/";
    private String espncrick="http://www.espncricinfo.com/";
    private String cnn="http://edition.cnn.com/";
    private String bbcsport="http://www.bbc.com/sport";
    private String skysport="http://www.skysports.com/";
    private String theeconomist="http://www.economist.com/";
    private String techreader="http://www.in.techradar.com/";
    private String t3n="http://t3n.de/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myprefs = new Mypreference(MainActivity.this);

        /* Retrieve a PendingIntent that will perform a broadcast */
        Intent alarmIntent = new Intent(MainActivity.this, NotifyMe.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);

              /* Retrieve a PendingIntent that will perform a broadcast */
        Intent alarmIntentone = new Intent(MainActivity.this, NotifyonThree.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntentone, 0);

              /* Retrieve a PendingIntent that will perform a broadcast */
        Intent alarmIntenttwo = new Intent(MainActivity.this, NotifyonSeven.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntenttwo, 0);

              /* Retrieve a PendingIntent that will perform a broadcast */
        Intent alarmIntentthree = new Intent(MainActivity.this, NotifyonTwelve.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntentthree, 0);

//        handleNotification();
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

//        gamingNewsNotify();
       // startService(new Intent(getApplicationContext(), NotificationManage.class));
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        startAt10();
        startAt03();
        startAt07();
        startAt12();
        //start();
        connectionCheck();
        adMobFullPageAd();
    }

    private void setupViewPager(ViewPager viewPager) {
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragments(new AllNews(), "All News");
        viewPagerAdapter.addFragments(new LatestNews(), "Latest News");
        viewPagerAdapter.addFragments(new TopGrossing(), "Top Grossing");
        viewPagerAdapter.addFragments(new Entertainment(), "Entertainment");
        viewPagerAdapter.addFragments(new Sport(), "Sport");
        viewPagerAdapter.addFragments(new Gaminggossip(), "Gaming Gossip");
        viewPagerAdapter.addFragments(new Celebs(), "Celebs");
        viewPagerAdapter.addFragments(new SportTalk(), "Cricket");
        viewPagerAdapter.addFragments(new MetroNews(), "Metro News");
        viewPagerAdapter.addFragments(new Technology(), "Technology");
        viewPagerAdapter.addFragments(new Science(), "Science");
        viewPagerAdapter.addFragments(new Geographic(), "Geographic News");
        viewPagerAdapter.addFragments(new NewsOfTheWeek(), "News Of The Week");

        viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(), MoreActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

      if (id == R.id.nav_bbc_news) {

            String url = bbcnews;
            Intent intent = new Intent(getApplicationContext(), WebActivity.class);
            intent.putExtra("EXTRA_MESSAGE", url);
            startActivity(intent);
            //Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();

        }else if (id == R.id.nav_mtv_news) {

            String url = mtvnews;
            Intent intent = new Intent(getApplicationContext(), WebActivity.class);
            intent.putExtra("EXTRA_MESSAGE", url);
            startActivity(intent);
            //Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();

        }else if (id == R.id.nav_times_of_india) {

            String url = toi;
            Intent intent = new Intent(getApplicationContext(), WebActivity.class);
            intent.putExtra("EXTRA_MESSAGE", url);
            startActivity(intent);
            //Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_the_hindu) {

            String url = thehindu;
            Intent intent = new Intent(getApplicationContext(), WebActivity.class);
            intent.putExtra("EXTRA_MESSAGE", url);
            startActivity(intent);
           // Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_cnbc_news) {

            String url = cnbc;
            Intent intent = new Intent(getApplicationContext(), WebActivity.class);
            intent.putExtra("EXTRA_MESSAGE", url);
            startActivity(intent);
            //Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_espn_news) {

            String url = espn;
            Intent intent = new Intent(getApplicationContext(), WebActivity.class);
            intent.putExtra("EXTRA_MESSAGE", url);
            startActivity(intent);
            //Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_espn_cricket) {

            String url = espncrick;
            Intent intent = new Intent(getApplicationContext(), WebActivity.class);
            intent.putExtra("EXTRA_MESSAGE", url);
            startActivity(intent);
            //Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_cnn_news) {

            String url = cnn;
            Intent intent = new Intent(getApplicationContext(), WebActivity.class);
            intent.putExtra("EXTRA_MESSAGE", url);
            startActivity(intent);
           // Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_bbc_sport) {

            String url = bbcsport;
            Intent intent = new Intent(getApplicationContext(), WebActivity.class);
            intent.putExtra("EXTRA_MESSAGE", url);
            startActivity(intent);
            //Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_sky_sport) {

            String url = skysport;
            Intent intent = new Intent(getApplicationContext(), WebActivity.class);
            intent.putExtra("EXTRA_MESSAGE", url);
            startActivity(intent);
           // Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_the_economist) {

            String url = theeconomist;
            Intent intent = new Intent(getApplicationContext(), WebActivity.class);
            intent.putExtra("EXTRA_MESSAGE", url);
            startActivity(intent);
            //Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_tech_resder) {

            String url = techreader;
            Intent intent = new Intent(getApplicationContext(), WebActivity.class);
            intent.putExtra("EXTRA_MESSAGE", url);
            startActivity(intent);
            //Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();

        }else if (id == R.id.nav_t3n) {

            String url = t3n;
            Intent intent = new Intent(getApplicationContext(), WebActivity.class);
            intent.putExtra("EXTRA_MESSAGE", url);
            startActivity(intent);
            //Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void gamingNewsNotify() {
        int notifyID = 1;
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.gaming)
//                .setColor(getResources().getColor(R.color.notifyone))
                        .setContentTitle("Instant News")
                        .setContentText("See the latest news about gaming industry!");
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notifyID, mBuilder.build());

    }


    public void start() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 8000;

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }

    public void cancel() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("WrongConstant")
    public void startAt10() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 720;

        /* Set the alarm to start at 10:30 AM */
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 10);

        /* Repeating on every 20 minutes interval */
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * 720, pendingIntent);

       // gamingNewsNotify();
    }

    @SuppressLint("WrongConstant")
    public void startAt12() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 720;

        /* Set the alarm to start at 10:30 AM */
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 10);

        /* Repeating on every 20 minutes interval */
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * 720, pendingIntent);

        // gamingNewsNotify();
    }

    @SuppressLint("WrongConstant")
    public void startAt03() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 720;

        /* Set the alarm to start at 10:30 AM */
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 15);
        calendar.set(Calendar.MINUTE, 10);

        /* Repeating on every 20 minutes interval */
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * 720, pendingIntent);

        // gamingNewsNotify();
    }

    @SuppressLint("WrongConstant")
    public void startAt07() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 720;

        /* Set the alarm to start at 10:30 AM */
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 10);

        /* Repeating on every 20 minutes interval */
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * 720, pendingIntent);

        // gamingNewsNotify();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////


    private void connectionCheck() {
        ConnectionCheck connectionCheck = new ConnectionCheck(getApplicationContext());
        interstitialAd = new InterstitialAd(getApplicationContext());
        if (connectionCheck.isConnectionAvailable()) {
            interstitialAd.setAdUnitId(myprefs.getAdmobFullpageId());
            adRequest = new AdRequest.Builder().build();
            interstitialAd.loadAd(adRequest);
            addAdmobAdListner();
            //adMobBannerAd();
        }
    }

    private void addAdmobAdListner() {
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                }
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {

            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();

            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });
    }


    Runnable mShowFullPageAdTask = new Runnable() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (interstitialAd.isLoaded())
                        interstitialAd.show();
                }
            });
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        handler1.postDelayed(mShowFullPageAdTask, 45 * 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler1.removeCallbacks(mShowFullPageAdTask);

    }

    private void adMobFullPageAd() {
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId((myprefs.getAdmobFullpageId()));
        requestNewInterstitial();
        interstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });
    }

    private void requestNewInterstitial() {
        adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

}

package teetech.com.mylex;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatuteDisplayActivity extends AppCompatActivity
{


    ViewPager viewPager;
    PartListAdapter partListAdapter;
    List<Object> partList;
    Context context;
    String statuteTitle,statuteId,gloAnno;
    Bundle bundle;
    private TabLayout tabLayout;
    net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statute_display);
        //getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        collapsingToolbarLayout = (net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);




        statuteTitle = getIntent().getStringExtra("title");
        statuteId = getIntent().getStringExtra("statuteId");
        gloAnno = getIntent().getStringExtra("glo-anno");

        bundle = new Bundle();
        bundle.putString("title",statuteTitle);
        bundle.putString("statuteId",statuteId);
        bundle.putString("gloAnno",gloAnno);



        //toolbar.setTitle(statuteTitle);
        setSupportActionBar(toolbar);

        collapsingToolbarLayout.setTitle(statuteTitle);

        viewPager = (ViewPager) findViewById(R.id.statute_view_pager);
        viewPager.setAdapter(new SectionStatutePagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(0);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Statute Parts");
        tabLayout.setupWithViewPager(viewPager);

        Map<String, String> params = new HashMap<String, String>();
        params.put("controller", "merchant");
        params.put("action", "partList");
        params.put("statuteId",statuteId);

        new Connection(this, params, new OnDataReady()
        {
            @Override
            public void dataReady(JSONObject jsonObject)
            {
                Log.e("checkMe Error",jsonObject.toString());
            }

            @Override
            public void onConnectionError(String error)
            {
                Log.e("checkMe Error",error);
            }
        });


        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, statuteTitle, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }


    class SectionStatutePagerAdapter extends FragmentPagerAdapter
    {


        public SectionStatutePagerAdapter(FragmentManager fm)
        {
            super(fm);

        }

        @Override
        public Fragment getItem(int position)
        {
            Fragment fragment=null;
            switch (position)
            {
                case 1:
                    fragment = GloAnnoHolder.newInstance(position + 1,gloAnno);
                    fragment.setArguments(bundle);
                    break;

                case 0:

                    fragment = StatuteHolder.newInstance(position + 1);
                    fragment.setArguments(bundle);


            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }


}

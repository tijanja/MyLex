package teetech.com.mylex;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class SectionTextActivity extends AppCompatActivity {

    private TextView sectionTextView;
    private ViewPager viewPager;
    private SectionTextPagerAdapter sectionTextPagerAdapter;
    private ArrayList<String> sectionTextArray;
    private HashMap<Integer, Section> sectionHashMap;
    private Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_text);

        /*toolbar =(Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);*/


        viewPager = (ViewPager) findViewById(R.id.section_text_pagers);
        viewPager.setClipToPadding(false);
        viewPager.setPadding(23,0,23,0);
        String sectText = getIntent().getStringExtra("sectionText");
        int currentPosition = getIntent().getIntExtra("position",0);
        sectionTextArray = getIntent().getStringArrayListExtra("SectionTextList");
        sectionHashMap = (HashMap<Integer, Section>) getIntent().getSerializableExtra("mymap");


        sectionTextPagerAdapter = new SectionTextPagerAdapter(getSupportFragmentManager(),sectionHashMap);

        viewPager.setAdapter(sectionTextPagerAdapter);
        viewPager.setCurrentItem(currentPosition);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {
                //viewPager.setCurrentItem(4);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

       // sectionTextView = (TextView) findViewById(R.id.section_text);


        //sectionTextView.setText(Html.fromHtml(sectText));
    }

    class SectionTextPagerAdapter extends FragmentPagerAdapter
    {
        private ArrayList<String> itemList;
        private HashMap<Integer, Section> sectionTextHash;
        public SectionTextPagerAdapter(FragmentManager fm,HashMap<Integer, Section> sectHash)
        {
            super(fm);
            this.itemList = itemList;
            sectionTextHash = sectHash;
        }

        @Override
        public Fragment getItem(int position)
        {
            Fragment fragment= SectionTextFragment.newInstance(position+1,sectionTextHash.get(position));
            return fragment;
        }

        @Override
        public int getCount() {
            return sectionTextHash.size();
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            Section section = (Section)sectionTextHash.get(position);
            return section.getSectionTitle();

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId())
        {
            case R.id.action_settings:
                Toast.makeText(this,"it works;",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

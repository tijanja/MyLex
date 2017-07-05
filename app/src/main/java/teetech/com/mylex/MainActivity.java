package teetech.com.mylex;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager mViewPager;
    private RecyclerView recyclerView;
    private List<Object> statuteList,advertList;
    private List<Object> objectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.tool_bar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        statuteList = new ArrayList<>();

        advertList = new ArrayList<>();

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        StatuteListAdapter statuteListAdapter = new StatuteListAdapter(statuteList,this,advertList,this);
        recyclerView.setAdapter(statuteListAdapter);

        statuteList.add(new Category(this,"image"));
        statuteList.add(new Category(this,"Constitution"));
        statuteList.add(new Category(this,"State"));
        statuteList.add(new Category(this,"Procurement"));
        statuteList.add(new Category(this,"Child",statuteListAdapter));
        statuteList.add(new Category(this,"Business"));
        statuteList.add(new Category(this,"Criminal Law"));




        advertList.add("empty");
        advertList.add(new MyImage(getResources().getDrawable(R.drawable.nass)));

    }


}

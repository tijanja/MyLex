package teetech.com.mylex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubpartSectionActivity extends AppCompatActivity implements OnDataReady
{

    private Toolbar subpart_toolbar;
    private RecyclerView subPartSectionList;
    private List<Object> subPartList;
    private  SubPartSectionAdapter subPartSectionAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subpart_section);
        subpart_toolbar = (Toolbar) findViewById(R.id.subpart_toolbar);
        progressBar = (ProgressBar) findViewById(R.id.advert_progress);
        progressBar.setVisibility(View.VISIBLE);
        setSupportActionBar(subpart_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String partid = getIntent().getStringExtra("partId");
        String statuteId = getIntent().getStringExtra("statuteId");

        subPartList = new ArrayList<Object>();
        subPartSectionAdapter = new SubPartSectionAdapter(subPartList);
        subPartSectionList = (RecyclerView) findViewById(R.id.subpart_section_list);
        subPartSectionList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        subPartSectionList.setAdapter(subPartSectionAdapter);
        subPartSectionList.addOnItemTouchListener(new OnStatuteClickListener(this,subPartSectionList , new OnClickStatute()
        {
            @Override
            public void onClick(View view, int position)
            {
                Subpart subpart = (Subpart) subPartList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("sectionList",subpart);
                Intent i = new Intent(SubpartSectionActivity.this,SectionListActivity.class);
                i.putExtra("sectionFrom","subpart");
                i.putExtra("statuteId",subpart.getStatuteId());
                i.putExtra("section_list",bundle);
                SubpartSectionActivity.this.startActivity(i);

            }

            @Override
            public void onLongClick(View view, int position)
            {

            }
        }));

        Map<String, String> params = new HashMap<String, String>();
        params.put("controller", "merchant");
        params.put("action", "subpartList");
        params.put("statuteid",statuteId);
        params.put("partid",partid);

        new Connection(this,params,this,progressBar);

    }

    @Override
    public void dataReady(JSONObject jsonObject)
    {

        try
        {
            JSONObject jCheck = jsonObject.getJSONObject("data");

                if(jCheck.getBoolean("action"))
                {
                    JSONArray jsonArray = jCheck.optJSONArray("response");
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject subJSON = jsonArray.getJSONObject(i);
                        Subpart subpart = new Subpart();
                        subpart.setStatuteId(subJSON.getString("statuteid"));
                        subpart.setSubpartTitle(subJSON.getString("subpart_title"));
                        subpart.setPartId(subJSON.getString("partid"));
                        subpart.setSubpartId(subJSON.getString("subpartid"));

                        subPartList.add(subpart);

                    }

                    subPartSectionAdapter.notifyDataSetChanged();
                    //Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
                }



        }
        catch (JSONException e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onConnectionError(String error) {

    }
}

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

public class SectionListActivity extends AppCompatActivity implements OnDataReady
{

    private RecyclerView recyclerView;
    private List<Object> itemList;
    private ArrayList<String> sectionTextList;
    private SectionlistAdapter sectionlistAdapter;
    private HashMap<Integer, Section> sectionTextHash;
    private ProgressBar progressBar;
    String statuteId;
    String partId,subpartId;
    private Subpart subpart;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_list);
        //getActionBar().getTitle();
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        progressBar = (ProgressBar) findViewById(R.id.advert_progress);
        sectionTextHash = new HashMap<Integer, Section>();
        sectionTextList = new ArrayList<String>();

        String sectionFrom = getIntent().getStringExtra("sectionFrom");
        statuteId = getIntent().getStringExtra("statuteId");

        Map<String, String> params = new HashMap<String, String>();
        params.put("controller", "merchant");
        params.put("action", "sectionList");
        params.put("sectionFrom",sectionFrom);
        params.put("statuteid",statuteId);

        switch (sectionFrom)
        {
            case "part":
                 partId = getIntent().getStringExtra("partId");
                params.put("partid",partId);

                break;

            case "subpart":

                Bundle bundle = getIntent().getBundleExtra("section_list");
                subpart = (Subpart) bundle.getSerializable("sectionList");

                params.put("partid",subpart.getPartId());
                params.put("subpartid",subpart.getSubpartId());

               // Toast.makeText(this,"partid:"+partId+" subpartId"+subpart,Toast.LENGTH_LONG).show();

                break;
        }


        itemList = new ArrayList<Object>();
        sectionlistAdapter = new SectionlistAdapter(itemList);
        recyclerView = (RecyclerView) findViewById(R.id.section_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(sectionlistAdapter);
        recyclerView.addOnItemTouchListener(new OnStatuteClickListener(this, recyclerView, new OnClickStatute() {
            @Override
            public void onClick(View view, int position)
            {
                Section section = (Section) itemList.get(position);
                Intent i = new Intent(SectionListActivity.this,SectionTextActivity.class);
                i.putExtra("sectionText",section.getSectionText());
                i.putStringArrayListExtra("SectionTextList",sectionTextList);
                i.putExtra("mymap",sectionTextHash);
                i.putExtra("position",position);
                SectionListActivity.this.startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));



        new Connection(this,params,this,progressBar);

    }

    @Override
    public void dataReady(JSONObject jsonObject)
    {
        try
        {
            JSONArray jsonArray = jsonObject.getJSONArray("data");

            for(int i=0;i<jsonArray.length();i++)
            {
                Section section = new Section();
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                section.setSectionTitle(jsonObj.getString("section_title"));
                section.setSectionId(jsonObj.getString("section_title"));
                section.setSectionText(jsonObj.getString("section_text"));
                section.setSectionAnno(jsonObj.getString("section_anno"));
                section.setCaseAnno(jsonObj.getString("case_anno"));
                itemList.add(section);
                sectionTextList.add(section.getSectionText());
                sectionTextHash.put(i,section);
            }

            sectionlistAdapter.notifyDataSetChanged();

            //Toast.makeText(this,jsonObject1.toString(),Toast.LENGTH_LONG).show();
        }
        catch (JSONException e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onConnectionError(String error) {

    }
}

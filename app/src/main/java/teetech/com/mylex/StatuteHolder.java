package teetech.com.mylex;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aKI on 10/02/2017.
 */

public class StatuteHolder extends Fragment implements OnDataReady
{
    String statuteTitle,statuteId;
    RecyclerView recyclerView;
    PartListAdapter partListAdapter;
    List<Object> partList;
    Context context;
    ProgressBar progressBar;
    private static final String ARG_SECTION_NUMBER = "section_number";

    public StatuteHolder()
    {

    }

    public static StatuteHolder newInstance(int sectionNumber)
    {
        StatuteHolder fragment = new StatuteHolder();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        statuteId = getArguments().getString("statuteId");
        View rootView = inflater.inflate(R.layout.content_statute_display, container, false);
        progressBar = (ProgressBar) rootView.findViewById(R.id.advert_progress);

        partList = new ArrayList<Object>();
        partListAdapter = new PartListAdapter(partList);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_for_part);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL, false);
        //RecyclerView.LayoutManager stagger =new StaggeredGridLayoutManager(2,1);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(partListAdapter);
        recyclerView.addOnItemTouchListener(new OnStatuteClickListener(getActivity(), recyclerView, new OnClickStatute()
        {
            @Override
            public void onClick(View view, int position)
            {

                try
                {
                    Part part = (Part)partList.get(position);

                    if(part.getNextSet().equals("subpart"))
                    {
                        Intent i = new Intent(getContext(),SubpartSectionActivity.class);
                        i.putExtra("partId",part.getPartId());
                        i.putExtra("statuteId",part.getStatuteId());


                            getActivity().startActivity(i);


                        //Toast.makeText(getContext(),part.getNextSet(),Toast.LENGTH_LONG).show();

                    }
                    else
                    {
                        Intent i = new Intent(getContext(),SectionListActivity.class);
                        i.putExtra("partId",part.getPartId());
                        i.putExtra("statuteId",part.getStatuteId());
                        i.putExtra("sectionFrom","part");
                        //Toast.makeText(getContext(),"Section",Toast.LENGTH_LONG).show();
                        getActivity().startActivity(i);
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onLongClick(View view, int position)
            {

            }
        }));

        Map<String, String> params = new HashMap<String, String>();
        params.put("controller", "merchant");
        //params.put("action", "test");
        params.put("action", "partList");
        params.put("statuteId",statuteId);

        progressBar.setVisibility(View.VISIBLE);

        new Connection(getActivity(),params,this);

        return rootView;
    }

    @TargetApi(21)
    public ActivityOptions ddd(View view)
    {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(StatuteHolder.this.getActivity(),view,"partTitle");
        return options;
    }

   @Override
    public void dataReady(JSONObject jsonObject)
    {
        Log.e("checkMe Error",jsonObject.toString());
        try
        {
            JSONObject jObj = jsonObject.getJSONObject("data");

            if(jObj.getBoolean("action"))
            {
                JSONArray jsonArray = jObj.optJSONArray("response");

                for (int i=0;i<jsonArray.length();i++)
                {
                    JSONObject partObject = jsonArray.getJSONObject(i);
                    Part part = new Part(statuteId);
                    part.setPartId(partObject.getString("partid"));
                    part.setPartName(partObject.getString("part_title"));
                    part.setNextSet(partObject.getString("nextSet"));
                    partList.add(part);
                    //Log.e("000",+"  --------");

                    partListAdapter.notifyDataSetChanged();
                }



            }
            else
            {
                Log.e("000","new error");
            }
        }
        catch (JSONException e)
        {
            Log.e("tunji",jsonObject.toString());
        }
    }

    @Override
    public void onConnectionError(String error) {

    }

}

package teetech.com.mylex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
 * Created by aKI on 02/02/2017.
 */

public class State extends RecyclerView.Adapter implements OnDataReady
{
        List<Object> itemList;
        Context context;
        State state;

        public State(Context c)
        {
            context = c;
            itemList = new ArrayList<Object>();
            state = this;
            Map<String, String> params = new HashMap<String, String>();
            params.put("controller", "merchant");
            params.put("action", "statuteList");
            params.put("search_query", "women");

            new Connection(context,params,this);
        }

    @Override
    public void dataReady(JSONObject jsonObject)
    {
        try
        {

            JSONArray jsonArray = jsonObject.getJSONArray("data");

            if (jsonObject.getBoolean("success"))
            {
                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsObj = jsonArray.getJSONObject(i);
                    Statute statute = new Statute(jsObj.getString("id"));
                    statute.setTitle(jsObj.getString("title"));
                    itemList.add(statute);
                    state.notifyDataSetChanged();
                }
            }
            else
            {
                Toast.makeText(context,"Error",Toast.LENGTH_LONG).show();
            }
        }
        catch (JSONException e)
        {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionError(String error) {

    }

    public static class StatuteHolder extends RecyclerView.ViewHolder
{
    ImageView imageView;
    TextView statute_title;
    LinearLayout status_parent_box;

    public StatuteHolder(View itemView)
    {
        super(itemView);

        statute_title = (TextView) itemView.findViewById(R.id.statute_title);
        status_parent_box = (LinearLayout) itemView.findViewById(R.id.status_parent_box);
    }

}

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view2 = inflater.inflate(R.layout.image_horizontal_layout,parent,false);

        return new StatuteRowAdapter.StatuteHolder(view2);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {

        StatuteRowAdapter.StatuteHolder imgHolder = (StatuteRowAdapter.StatuteHolder) holder;
        Statute myImage = (Statute) itemList.get(position);

        if(position == itemList.size()-1)
        {
            imgHolder.status_parent_box.setPadding(0,0,20,0);
        }

        //imgHolder.imageView.setImageDrawable(myImage.getImage());
        imgHolder.statute_title.setText(Html.fromHtml(myImage.getTitle()));
    }

    @Override
    public int getItemCount()
    {
        return itemList.size();
    }
}



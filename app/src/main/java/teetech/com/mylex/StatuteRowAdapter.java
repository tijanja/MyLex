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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aKI on 29/01/2017.
 */

public class StatuteRowAdapter  extends RecyclerView.Adapter implements OnDataReady
{
    List<Object> itemList;
    Context context;
    StatuteRowAdapter statuteRowAdapter;

    public StatuteRowAdapter(Context c)
    {
        context = c;
        itemList = new ArrayList<Object>();
        statuteRowAdapter = this;

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
                    statuteRowAdapter.notifyDataSetChanged();

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
    public void onConnectionError(String error)
    {

    }

    public static class StatuteHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView statute_title,nsca;
        LinearLayout status_parent_box;

        public StatuteHolder(View itemView)
        {
            super(itemView);
            statute_title = (TextView) itemView.findViewById(R.id.statute_title);
            status_parent_box = (LinearLayout) itemView.findViewById(R.id.status_parent_box);
            nsca = (TextView) itemView.findViewById(R.id.nsca);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view2 = inflater.inflate(R.layout.image_horizontal_layout,parent,false);

        return new StatuteHolder(view2);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {

        StatuteHolder imgHolder = (StatuteHolder) holder;
        Statute myImage = (Statute) itemList.get(position);

        if(position == itemList.size()-1)
        {
            imgHolder.status_parent_box.setPadding(0,0,20,0);
        }

        //imgHolder.imageView.setImageDrawable(myImage.getImage());
        imgHolder.statute_title.setText(Html.fromHtml(myImage.getTitle()));
        imgHolder.nsca.setText("NSCA "+myImage.getYear()+" No. "+myImage.getNumber());
    }

    @Override
    public int getItemCount()
    {
        return itemList.size();
    }
}

package teetech.com.mylex;

import android.content.Context;
import android.widget.Toast;

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

public class Category implements OnDataReady
{
    private String categoryName,nextStep;
    Context context;
    List<Object> itemList;
    boolean isItemListSet = false;
    StatuteListAdapter statuteListAdapter;
    List<Object> item = new ArrayList<Object>();

    public Category(Context c, String cat)
    {
        categoryName = cat;
        context = c;
        itemList = getStatutes();
    }

    public Category(Context c, String cat, StatuteListAdapter s)
    {
        categoryName = cat;
        context = c;
        statuteListAdapter = s;

        Map<String, String> params = new HashMap<String, String>();
        params.put("controller", "merchant");
        params.put("action", "statuteList");
        params.put("search_query", "companies");

        new Connection(context,params,this);
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public List<Object> getStatutes()
    {
        return item;
    }

    public List<Object> getItemList()
    {
        return itemList;
    }

    public String getNext()
    {
        return nextStep;
    }

    @Override
    public void dataReady(JSONObject object)
    {
            try
            {
                JSONArray jsonArray = object.getJSONArray("data");
                if(object.getBoolean("success"))
                {
                    for(int i=0;i<jsonArray.length();i++)
                    {

                        JSONObject jsObj = jsonArray.getJSONObject(i);

                        Statute statute = new Statute(jsObj.getString("id"));
                        statute.setTitle(jsObj.getString("title"));
                        statute.setNextSet(jsObj.getString("nextSet"));
                        statute.setGloAnno(jsObj.getString("glo-anno"));
                        nextStep = jsObj.getString("title");
                        item.add(statute);

                        if(statuteListAdapter!=null)
                        {
                            statuteListAdapter.notifyDataSetChanged();
                        }
                    }

                   // itemList = getStatutes();
                }

            }
            catch (JSONException e) {
                Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
            }
    }

    @Override
    public void onConnectionError(String error)
    {
        Toast.makeText(context,error,Toast.LENGTH_LONG).show();
    }
}

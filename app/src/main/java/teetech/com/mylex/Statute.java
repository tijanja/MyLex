package teetech.com.mylex;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aKI on 26/01/2017.
 */

public class Statute implements OnDataReady
{
    private String title,number,year,commencementDate,statuteId,gloAnno,nextSet;

    List<Part> partList = new ArrayList<Part>();

    public Statute(String id)
    {
        statuteId = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCommencementDate() {
        return commencementDate;
    }

    public void setCommencementDate(String commencementDate) {
        this.commencementDate = commencementDate;
    }

    public String getGloAnno() {
        return gloAnno;
    }

    public String getNextSet()
    {
        return nextSet;
    }

    public void setNextSet(String s)
    {
        nextSet = s;
    }

    public void setGloAnno(String gloAnno) {
        this.gloAnno = gloAnno;
    }

    public void setStatuteId(String statuteId) {
        this.statuteId = statuteId;
    }

    public String getStatuteId() {
        return statuteId;
    }

    public List<Part> getPartList(Context context)
    {

        Map<String, String> params = new HashMap<String, String>();
        params.put("controller", "merchant");
        params.put("action", "partList");
        params.put("statuteid", this.getStatuteId());

        Connection con = new Connection(context,params,this);

        return partList;
    }

    @Override
    public void dataReady(JSONObject jsonObject)
    {
        try
        {

            JSONArray jsonArray = jsonObject.getJSONArray("data");
            if(jsonObject.getBoolean("success"))
            {
                for (int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsObj = jsonArray.getJSONObject(i);
                    Part part = new Part(this.getStatuteId());
                    part.setPartId(jsObj.getString("id"));
                    part.setPartName(jsObj.getString("partTitle"));
                    partList.add(part);
                }
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void onConnectionError(String error)
    {

    }
}

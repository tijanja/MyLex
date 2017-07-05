package teetech.com.mylex;

import android.content.Context;
import android.util.Log;
import android.widget.ProgressBar;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by aKI on 08/02/2017.
 */

public class Connection
{
    private final String URL = "http://gs1-nigeria.org/lexi/api/";
    private Map<String,String> params;
    private Context context;
    private JSONObject jsonObject;
    private boolean isValueReady=false;
    private OnDataReady onDataReady;

    private ProgressBar progressBar;

    public Connection(Context c, Map<String, String> param, OnDataReady ready)
    {

        context = c;
        params = param;
        onDataReady = ready;
        connect();

    }

    public Connection(Context c, Map<String, String> param, OnDataReady ready, ProgressBar progress)
    {

        context = c;
        params = param;
        onDataReady = ready;
        //progressBar = progress;
        connect();

    }

    public JSONObject getResponse()
    {
        isValueReady=false;
        return jsonObject;
    }


    public boolean isValueReady()
    {
        Log.e("is here","check 1");
        return isValueReady;
    }

    private void setResponse(JSONObject j)
    {
        Log.e("is here 3","check 3");
        jsonObject = j;
    }

    private void connect()
    {
       /* if(progressBar!=null)
        {
            progressBar.setVisibility(View.VISIBLE);
        }*/

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>()
        {

            @Override
            public void onResponse(String response)
            {
                try
                {

                    jsonObject = new JSONObject(response);
                    /*if(progressBar!=null && progressBar.isShown())
                    {
                        progressBar.setVisibility(View.GONE );
                    }*/
                    onDataReady.dataReady(jsonObject);


                }
                catch (JSONException e)
                {
                   // onDataReady.onConnectionError(e.getMessage());
                    Log.e("connection error", e.getMessage());
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                onDataReady.onConnectionError(new String(error.networkResponse.data));
            }
        }){
            @Override
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError
            {
                return params;
            }
        };

        request.setShouldCache(false);
        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MeVolley.getInstance().add(request);

    }
}

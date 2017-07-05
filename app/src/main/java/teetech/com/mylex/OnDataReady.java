package teetech.com.mylex;

import org.json.JSONObject;

/**
 * Created by aKI on 08/02/2017.
 */

public interface OnDataReady
{
    public void dataReady(JSONObject jsonObject);
    public void onConnectionError(String error);
}

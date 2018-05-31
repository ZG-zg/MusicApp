package zg.com.musicapp.netutils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ZG on 2018/5/13.
 */

public class ParseData {
    public static String parseString(String response,String key){
        String value = "";
        try {
            JSONObject object = new JSONObject(response);
            value = object.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static int parseInt(String response,String key){
        int value = -1;
        try {
            JSONObject object = new JSONObject(response);
            value = object.getInt(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static boolean parseBoolean(String response, String key) {
        Boolean value = false;
        try {
            JSONObject object = new JSONObject(response);
            value = object.optBoolean(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }
    public static JSONObject parseObject(String response, String key){
        JSONObject jobject = null;
        try {
            JSONObject object = new JSONObject(response);
            jobject = object.optJSONObject(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jobject;
    }
    public static JSONArray parseObjectArray(JSONObject object, String key){
        JSONArray array = null;
        try {
            array  = object.getJSONArray(key);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array;
    }
}

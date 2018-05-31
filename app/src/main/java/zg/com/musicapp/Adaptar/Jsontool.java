package zg.com.musicapp.Adaptar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by ZG on 2018/3/31.
 */

public class Jsontool {


    public static void getObserve1(String key, String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            // 返回json的数组
            JSONArray jsonArray = jsonObject.getJSONArray(key);
            String abs = jsonArray.getJSONObject(0).getString("kkk");
            System.out.println("值为:-----"+abs);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}

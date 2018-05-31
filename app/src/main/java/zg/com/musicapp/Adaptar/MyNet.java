package zg.com.musicapp.Adaptar;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by ZG on 2018/5/13.
 */

public class MyNet {
    private static String TAG = "mynet";
    public static void requestGet(Context context,String url,final Observe observe){
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        final StringRequest stringRequest = new StringRequest(Request.Method.GET,url,new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                Log.d(TAG,"onResponse"+response);
                observe.onSuccess(response);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError){
                Log.d(TAG,"onErrorResponse:"+volleyError.toString());
                observe.onError(volleyError);

            }
        });
        requestQueue.add(stringRequest);
        requestQueue.start();
    }
}

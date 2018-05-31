package zg.com.musicapp.Adaptar;

import com.android.volley.VolleyError;

/**
 * Created by ZG on 2018/5/13.
 */

public interface Observe {
    void onSuccess(String response);
    void onError(VolleyError error);
}

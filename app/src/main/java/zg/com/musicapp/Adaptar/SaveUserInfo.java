package zg.com.musicapp.Adaptar;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by ZG on 2018/5/6.
 */

public class SaveUserInfo {

    public static final String mysharedperences = "user_info";

    public static void setStringSharedperences(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(mysharedperences, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getStringSharedperences(Context context, String key, String defvalue) {
        SharedPreferences sp = context.getSharedPreferences("user_info", MODE_PRIVATE);
        String value = sp.getString(key, defvalue);
        return value;
    }

    public static void setIntSharedperences(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(mysharedperences, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int  getIntSharedperences(Context context, String key,int defvalue){
        SharedPreferences sp = context.getSharedPreferences("user_info",MODE_PRIVATE);
        int value = sp.getInt(key,defvalue);
        return value;
    }
    public static void setBooleanSharedperences(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(mysharedperences, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean  getBooleanSharedperences(Context context, String key,boolean defvalue){
        SharedPreferences sp = context.getSharedPreferences("user_info",MODE_PRIVATE);
        boolean value = sp.getBoolean(key,defvalue);
        return value;
    }
}

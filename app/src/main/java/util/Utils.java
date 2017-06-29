package util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Weather;

public class Utils {

    public static final String API_KEY ="&APPID=d500f33440132b8201dac3e032198c28";
    public static final String BASE_URL="http://api.openweathermap.org/data/2.5/weather?q=";
    public static final String ICON_URL="http://openweathermap.org/img/w/";
    public static final String FORECAST_URL="http://api.openweathermap.org/data/2.5/forecast?q=";

    public static final String APP_PREFERENCES_DATA = "mydatasettings";
    public static final String APP_PREFERENCES_FORECAST = "myforecastsettings";

    public static List<Weather> weatherList = new ArrayList<>();

    public static JSONObject getObject(String tagName, JSONObject jsonObject) throws JSONException {
        JSONObject jObj = jsonObject.getJSONObject(tagName);
        return  jObj;
    }

    public static String getString(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getString(tagName);
    }

    public static float getFloat(String tagName, JSONObject jsonObject) throws JSONException{
        return (float)jsonObject.getDouble(tagName);
    }

    public static double getDouble(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getDouble(tagName);
    }

    public static int getInt(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getInt(tagName);
    }

    public static String getDayOfTheWeek(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dayWeek = null;
        try {
            dayWeek = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("EEEE").format(dayWeek);
    }

    public static String getForecastDate(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date simpleDate = null;
        try {
            simpleDate = format.parse(date);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return new SimpleDateFormat("EEEE dd MMM").format(simpleDate);
    }

    public static boolean isOnline(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting())
        {
            return true;
        }
        return false;
    }
}

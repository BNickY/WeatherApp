package data;

import android.app.Activity;
import android.content.SharedPreferences;

import util.Utils;

public class ForecastPreference {
    SharedPreferences preferences;

    public ForecastPreference(Activity activity){
        preferences = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    public String getOfflineForecast(){
        return preferences.getString(Utils.APP_PREFERENCES_FORECAST,"null");
    }

    public void setOfflineForecast(String forecast){
        preferences.edit().putString(Utils.APP_PREFERENCES_FORECAST,forecast).commit();
    }
}

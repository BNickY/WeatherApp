package data;

import android.app.Activity;
import android.content.SharedPreferences;

import util.Utils;

public class DataPreference {
    SharedPreferences preferences;

    public DataPreference(Activity activity){
        preferences = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    public String getOfflineData(){
        return preferences.getString(Utils.APP_PREFERENCES_DATA,"null");
    }

    public void setOfflineData(String data){
        preferences.edit().putString(Utils.APP_PREFERENCES_DATA,data).commit();
    }
}
